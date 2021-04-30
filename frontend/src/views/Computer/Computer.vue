<template>
  <div id="computer">
    <Nav />
    <div id="join" v-if="!data.session">
      <div id="join-form">
        <label>사용자 이름 : </label>
        <input type="text" v-model="data.userName" required />
        <label>  방 이름 : </label>
        <input type="text" v-model="data.roomName" required />
        <button @click="joinSession">입장</button>
      </div>
    </div>
    <div id="session" v-if="data.session">
        <Chat :data="data" v-on:sendMessage="send"/>
        <WebCam :data="data" v-on:leaveSession="leaveSession"/>
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
        data : {
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
        },
      }
    },
    destroyed(){
      this.data.session = undefined;
      this.data.mainStreamManager = undefined;
      this.data.publisher = undefined;
      this.data.subscribers = [];
      this.data.OV = undefined;
      this.data.receiveMessage = [];
    },
    methods: {
      joinSession() {
        this.data.OV = new OpenVidu();
        // this.OV.setAdvancedConfiguration({
        //   screenShareChromeExtension: "https://chrome.google.com/webstore/detail/YOUR_EXTENSION_NAME/YOUR_EXTENSION_ID",
        // });

        this.data.session = this.data.OV.initSession();

        this.data.session.on("streamCreated", ({ stream }) => {
          const subscriber = this.data.session.subscribe(stream);
          if(subscriber.stream.typeOfVideo == "SCREEN"){
            this.data.share.active = true;
            this.data.share.screen = subscriber;
          }
          this.data.subscribers.push(subscriber);
          this.data.participants = this.data.subscribers.length+1;
        });
        this.data.session.on("streamDestroyed", ({ stream }) => {
          const index = this.data.subscribers.indexOf(stream.streamManager, 0);
          if(stream.typeOfVideo == "SCREEN"){
            this.data.share.active = false;
            this.data.share.screen = undefined;
          }
          if (index >= 0) {
            this.data.subscribers.splice(index, 1);
          }
          this.data.participants = this.data.subscribers.length+1;
        });
        this.data.session.on("signal:my-chat", (event) => {
          this.data.receiveMessage.push({sender : JSON.parse(event.from.data), message : event.data});
          console.log(event.from);
        });

        this.data.session.on("publisherStartSpeaking", (event) => {
          console.log( "Publisher " + event.connection.connectionId + " start speaking" );
        });

        this.data.session.on("publisherStopSpeaking", (event) => {
          console.log( "Publisher " + event.connection.connectionId + " stop speaking" );
        });

        this.getToken(this.data.roomName).then((token) => {
          this.data.session
            .connect(token, { userName: this.data.userName })
            .then(() => {
              let publisher = this.data.OV.initPublisher(undefined, this.data.setting);

              this.data.mainStreamManager = publisher;
              this.data.publisher = publisher;

              this.data.session.publish(this.data.publisher);
            })
            .catch((error) => {
              console.log(
                "There was an error connecting to the session:",
                error.code,
                error.message
              );
            });
        });

        window.addEventListener("beforeunload", this.data.leaveSession);
      },
      leaveSession() {
        if (this.data.session) this.data.session.disconnect();

        this.data.session = undefined;
        this.data.mainStreamManager = undefined;
        this.data.publisher = undefined;
        this.data.subscribers = [];
        this.data.OV = undefined;
        this.data.receiveMessage = [];
        window.removeEventListener("beforeunload", this.data.leaveSession);
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
        this.data.session.signal({
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
