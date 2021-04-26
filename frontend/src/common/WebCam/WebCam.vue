<template>
  <div id="webcam">
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
        <div id="chat-container">
            <div id="chat-nav">
                <div class="icon">Icon</div>
                <div class="icon icon-bottom">Icon</div>
                <div class="icon icon-bottom">Icon</div>
                <div>{{participants}}명</div>
            </div>
            <div id="chat-main">
                <div id="chat-title">
                  <p>Chat</p>
                </div>
                <div id="receive-container">
                  <div class="message"  v-for="(message, index) in receiveMessage" :key="index">
                    <table class="message-table" v-if="message.sender.userName !== userName">
                      <tr class="user-profile">
                        <td class="user-img" rowspan="2"></td>
                        <td class="user-name">{{message.sender.userName}}</td>
                      </tr>
                      <tr>
                        <td class="user-message">{{message.message}}</td>
                      </tr>
                    </table>
                    <table class="message-table my-message" v-else>
                      <tr class="user-profile">
                        <td class="user-name">{{message.sender.userName}}</td>
                        <td class="user-img" rowspan="2"></td>
                      </tr>
                      <tr>
                        <td class="user-message">{{message.message}}</td>
                      </tr>
                    </table>
                  </div>
                </div>
                <div id="input-container">
                  <div id="input-form">
                    <input id="sendMessage" class="input-message" type="text" v-model="sendMessage" @keyup.enter="send">
                    <button id="btnSendMessage" @click="send">입력</button>
                  </div>
                </div>
            </div>
        </div>
        <div id="webcam-container">
          <div id="webcam-title">
            <p>{{roomName}}</p>
          </div>
          <div id="video-container">
            <user-video class="publisher flex-item" :stream-manager="publisher"></user-video>
            <user-video class="subcribers flex-item" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"></user-video>
          </div>
          <div id="video-nav">
              <button id="btnSetvideo" @click="updateStream(0)" class="webcam-button">
                  <div v-if="setting.publishVideo">카메라 끄기</div>
                  <div v-else>카메라 켜기</div>
              </button>
              <button id="btnSetAudio" @click="updateStream(1)" class="webcam-button">
                  <div v-if="setting.publishAudio">오디오 끄기</div>
                  <div v-else>오디오 켜기</div>
              </button>
              <button id="btnShareScreen" @click="shareScreen" class="webcam-button">화면공유</button>
              <button id="btnLeaveSession" @click="leaveSession" class="webcam-button">나가기</button>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/WebCam/UserVideo";
import Nav from "@/common/Nav/Nav"
// import http from "@/util/http-common";
axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "BACK_TO_SCHOOL";
export default {
  name: "WebCam",
  components: {
    UserVideo,
    Nav,
  },
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      participants : 0,
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
      sendMessage: "",
      receiveMessage: [],
      screen: undefined,
    };
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
      this.OV.setAdvancedConfiguration({
        screenShareChromeExtension: "https://chrome.google.com/webstore/detail/YOUR_EXTENSION_NAME/YOUR_EXTENSION_ID",
      });

      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        this.participants = this.subscribers.length;
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
        this.participants = this.subscribers.length;
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

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
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
    settingOptions() {
      this.showSetting = !this.showSetting;
    },
    toggle(type) {
      if (type == 1) {
        this.setting.publishAudio = !this.setting.publishAudio;
      } else if (type == 0) {
        this.setting.publishVideo = !this.setting.publishVideo;
      } else {
        this.setting.mirror = !this.setting.mirror;
      }
    },
    updateStream(type) {
      if (type == 1) {
        this.setting.publishAudio = !this.setting.publishAudio;
        this.publisher.publishAudio(this.setting.publishAudio);
      } else {
        this.setting.publishVideo = !this.setting.publishVideo;
        this.publisher.publishVideo(this.setting.publishVideo);
      }
    },
    send() {
      this.session
        .signal({
          data: this.sendMessage,
          to: [],
          type: "my-chat",
        })
        .then(() => {
          console.log("Message successfully sent");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    shareScreen() {
      let screen = this.OV.initPublisher(undefined, {
        resolution: "1280x720",
        videoSource: "screen",
      });

      screen.once("accessAllowed", () => {
        screen.stream
          .getMediaStream()
          .getVideoTracks()[0]
          .addEventListener("ended", () => {
            console.log('User pressed the "Stop sharing" button');
            this.session.unpublish(screen);
            this.screen = undefined;
            this.session.publish(this.publisher);
          });
        this.session.unpublish(this.publisher);
        this.screen = screen;
        this.session.publish(this.screen);
      });

      screen.once("accessDenied", () => {
        console.warn("ScreenShare: Access Denied");
      });
    },
  },
};
</script>

<style>
@import '../../css/WebCam.css';
</style>