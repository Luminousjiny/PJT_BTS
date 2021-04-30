<template>
  <div id="computer">
    <Nav />
    <div id="join" v-if="!session">
      <div id="join-form">
        <label>사용자 이름 : </label>
        <input type="text" v-model="userName" required />
        <label>  방 이름 : </label>
        <input type="text" v-model="roomName" required />
        <button @click="joinSession">입장</button>
      </div>
    </div>
    <div id="session" v-if="session">
        <Chat :user-name="userName" :session="session" :receive-message="receiveMessage" :participants="participants" v-on:sendMessage="send"/>
        <WebCam :share="share" :room-name="roomName" :OV="OV" :session="session" :publisher="publisher" :subscribers="subscribers" :setting="setting" :user-name="userName" 
                  v-on:leaveSession="leaveSession"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import Nav from "@/common/Nav/Nav"
import WebCam from '@/common/WebCam/WebCam';
import Chat from '@/common/WebCam/Chat';
axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "BACK_TO_SCHOOL";
export default {
    name : "Computer",
    components : {
      Nav,
      WebCam,
      Chat
    },
    data() {
      return {
        OV: undefined,
        session: undefined,
        mainStreamManager: undefined,
        publisher: undefined,
        subscribers: [],
        participants : 1,
        roomName: "room1",
        userName: "user1",
        setting: {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: false,
          publishVideo: false,
          resolution: "640x480",
          frameRate: 30,
          insertMode: "APPEND",
          mirror: false,
        },
        receiveMessage: [],
        share : {
          active : false,
          screen : undefined,
        },
      }
    },
    destroyed(){
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      this.receiveMessage = [];
    },
    methods: {
      joinSession() {
        this.OV = new OpenVidu();
        // this.OV.setAdvancedConfiguration({
        //   screenShareChromeExtension: "https://chrome.google.com/webstore/detail/YOUR_EXTENSION_NAME/YOUR_EXTENSION_ID",
        // });

        this.session = this.OV.initSession();

        this.session.on("streamCreated", ({ stream }) => {
          const subscriber = this.session.subscribe(stream);
          if(subscriber.stream.typeOfVideo == "SCREEN"){
            this.share.active = true;
            this.share.screen = subscriber;
          }
          this.subscribers.push(subscriber);
          this.participants = this.subscribers.length+1;
        });
        this.session.on("streamDestroyed", ({ stream }) => {
          const index = this.subscribers.indexOf(stream.streamManager, 0);
          if(stream.typeOfVideo == "SCREEN"){
            this.share.active = false;
            this.share.screen = undefined;
          }
          if (index >= 0) {
            this.subscribers.splice(index, 1);
          }
          this.participants = this.subscribers.length+1;
        });
        this.session.on("signal:my-chat", (event) => {
          this.receiveMessage.push({sender : JSON.parse(event.from.data), message : event.data});
          console.log(event.from);
        });

        this.session.on("publisherStartSpeaking", (event) => {
          console.log( "Publisher " + event.connection.connectionId + " start speaking" );
        });

        this.session.on("publisherStopSpeaking", (event) => {
          console.log( "Publisher " + event.connection.connectionId + " stop speaking" );
        });

        this.getToken(this.roomName).then((token) => {
          this.session
            .connect(token, { userName: this.userName })
            .then(() => {
              let publisher = this.OV.initPublisher(undefined, this.setting);

              this.mainStreamManager = publisher;
              this.publisher = publisher;

              this.session.publish(this.publisher);
            })
            .catch((error) => {
              console.log(
                "There was an error connecting to the session:",
                error.code,
                error.message
              );
            });
        });

        window.addEventListener("beforeunload", this.leaveSession);
      },
      leaveSession() {
        if (this.session) this.session.disconnect();

        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;
        this.receiveMessage = [];
        window.removeEventListener("beforeunload", this.leaveSession);
      },
      getToken(roomName) {
        return this.createSession(roomName).then((sessionId) =>
          this.createToken(sessionId)
        );
      },
      createSession(sessionId) {
        return new Promise((resolve, reject) => {
          axios
            .post(
              `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
              JSON.stringify({
                customSessionId: sessionId,
              }),
              {
                auth: {
                  username: "OPENVIDUAPP",
                  password: OPENVIDU_SERVER_SECRET,
                },
              }
            )
            .then((response) => response.data)
            .then((data) => resolve(data.id))
            .catch((error) => {
              if (error.response.status === 409) {
                resolve(sessionId);
              } else {
                console.warn(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
                );
                if (
                  window.confirm(
                    `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                  )
                ) {
                  location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
                }
                reject(error.response);
              }
            });
        });
      },
      createToken(sessionId) {
        return new Promise((resolve, reject) => {
          axios
            .post(
              `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
              {},
              {
                auth: {
                  username: "OPENVIDUAPP",
                  password: OPENVIDU_SERVER_SECRET,
                },
              }
            )
            .then((response) => response.data)
            .then((data) => resolve(data.token))
            .catch((error) => reject(error.response));
        });
      },
      send(sendMessage) {
        this.session.signal({
            data: sendMessage,
            to: [],
            type: "my-chat",
        }).then(() => {
            console.log("Message successfully sent");
        }).catch((error) => {
            console.error(error);
        });
      },
    },
}
</script>

<style scoped>
@import '../../css/style.css';
#computer {
    text-align: center;
    height : 100vh;
    overflow: hidden;
}
#session{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    width : 80%;
    height : 90%;
    margin : 0 auto;
    text-align: center;
}
</style>
