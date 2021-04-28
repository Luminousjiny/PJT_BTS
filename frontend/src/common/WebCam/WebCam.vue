<template>
  <div id="webcam-container">
    <div id="webcam-title">
      <div id="school-title">
        <img src="@/../public/Image/school_icon.png" id="school-icon">
        <p>{{roomName}}</p>
      </div>
    </div>
    <div id="video-container">
      <div id="prev">
        <button class="webcam-button page-button" @click="page -= 1;" v-if="prev"><v-icon>fas fa-chevron-left</v-icon></button>
      </div>
      <div id="videos">
        <user-video :class="{publisher : true, 'flex-item': true, 'width-40': !setWidth, 'width-30' : setWidth}" :stream-manager="publisher" v-if="page == 0"></user-video>
        <user-video :class="{subscribers : true, 'flex-item': true, 'width-40': !setWidth, 'width-30' : setWidth}" v-for="sub in pageSub" :key="sub.stream.connection.connectionId" :stream-manager="sub"></user-video>
      </div>
      <div id="next">
        <button class="webcam-button page-button" @click="page += 1;" v-if="next"><v-icon>fas fa-chevron-right</v-icon></button>
      </div>
    </div>
    <div id="webcam-nav">
        
        <button id="btnSetvideo" @click="updateStream(0)" class="webcam-button">
            <div v-if="!setting.publishVideo"><v-icon id="unpublish-video">fas fa-video-slash</v-icon></div>
            <div v-else><v-icon id="publish-video">fas fa-video</v-icon></div>
        </button>
        <button id="btnSetAudio" @click="updateStream(1)" class="webcam-button">
            <div v-if="!setting.publishAudio"><v-icon id="unpublish-audio">fas fa-microphone-slash</v-icon></div>
            <div v-else><v-icon id="publish-audio">fas fa-microphone</v-icon></div>
        </button>
        <button id="btnShareScreen" @click="shareScreen" class="webcam-button"><v-icon>fas fa-upload</v-icon></button>
        <button id="btnLeaveSession" @click="leaveSession" class="webcam-button"><v-icon id="leave-session">fas fa-phone-alt</v-icon></button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import UserVideo from "@/components/WebCam/UserVideo";
axios.defaults.headers.post["Content-Type"] = "application/json";

export default {
  name: "WebCam",
  components: {
    UserVideo,
  },
  data() {
    return {
      screen : undefined,
      page : 0,
    }
  },
  props :{
    OV : Object,
    roomName : String,
    session : Object,
    publisher : Object,
    subscribers : Array,
    setting : Object,
    userName : String,
  },
  computed : {
    setWidth : function(){
      if(this.subscribers.length >= 4){
        return true;
      }
      return false;
    },
    next : function(){
      if(this.subscribers.length+1 - (this.page+1)*6 > 0 ){
        return true;
      }
      return false;
    },
    prev : function(){
      if(this.page > 0){
        return true;
      }
      return false;
    },
    totalPage : function(){
      let remain = (this.subscribers.length+1)%6;
      if(remain != 0){
        return (this.subscribers.length+1)/6+1;
      }
      return (this.subscribers.length+1)/6;
    },
    pageSub : function(){
      if(this.page == 0){
        return this.subscribers.slice(0,5);
      }
      return this.subscribers.slice(this.page*5, Math.min(this.page*5+6,this.subscribers.length));
    }
  },
  methods: {
    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },
    updateStream(type) {
      if (type == 1) {
        this.setting.publishAudio = !this.setting.publishAudio;
        this.publisher.publishAudio(this.setting.publishAudio);
      } else {
        this.setting.publishVideo = !this.setting.publishVideo;
        this.publisher.publishVideo(this.setting.publishVideo);
      }
      this.$emit('updatet:publisher', this.publisher);
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
    leaveSession() {
        this.$emit('leaveSession');
    },
  },
};
</script>

<style scoped>
@import '../../css/WebCam.css';
</style>