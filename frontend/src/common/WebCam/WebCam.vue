<template>
  <div id="webcam-container">
    <div id="webcam-title">
      <div id="school-title">
        <img src="@/../public/Image/school_icon.png" id="school-icon">
        <p>{{roomName}}</p>
      </div>
    </div>
    <div id="webcam-main">
      <div id="share-container" v-if="share.active">
        <div id="share-screen" v-if="share.screen">
          <user-video class="flex-item screen-video" :stream-manager="share.screen"></user-video>
        </div>
        <div id="share-youtube" v-if="youtube">
          <div><p>야호호호</p></div>
        </div>
      </div>
      <div id="video-container" :class="{'flex-column': share.active, 'screen-share' : share.active}">
        <div id="prev">
          <button class="webcam-button page-button" @click="page -= 1;" v-if="prev">
            <v-icon v-if="!share.active">fas fa-chevron-left</v-icon>
            <v-icon v-else>fas fa-angle-up</v-icon>
          </button>
        </div>
        <div id="videos" :class="{'flex-column': share.active}">
          <user-video :class="{publisher : true, 'flex-item': true, 'width-40': setWidth40, 'width-30' : setWidth30}" :stream-manager="publisher" v-if="page == 0"></user-video>
          <user-video :class="{subscribers : true, 'flex-item': true, 'width-40': setWidth40, 'width-30' : setWidth30}" v-for="(sub, idx) in pageSub" :key="idx" :stream-manager="sub"></user-video>
        </div>
        <div id="next">
          <button class="webcam-button page-button" @click="page += 1;" v-if="next">
            <v-icon v-if="!share.active">fas fa-chevron-right</v-icon>
            <v-icon v-else>fas fa-angle-down</v-icon>
          </button>
        </div>
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
        <button id="btnShareScreen" @click="shareScreen" class="webcam-button">
          <div v-if="!sharing"><v-icon id="unpublish-screen">fas fa-upload</v-icon></div>
          <div v-else><v-icon id="publish-screen">fas fa-upload</v-icon></div>
        </button>
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
      page : 0,
      youtube : false,
      sharing : false,
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
    share : Object,
  },
  computed : {
    setWidth40 : function(){
      if(this.subscribers.length < 4 && !this.share.active){
        return true;
      }
      return false;
    },
    setWidth30 : function(){
      if(this.subscribers.length >= 4 && !this.share.active){
        return true;
      }
      return false;
    },
    next : function(){
      if((!this.share.active && this.subscribers.length+1 - (this.page+1)*6 > 0 ) || (this.share.active && this.subscribers.length+1 - (this.page+1)*5 > 0 )){
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
        if(!this.share.active){
          return this.subscribers.slice(0,5);
        }
        return this.subscribers.slice(0,4);
      }else{
        if(!this.share.active){
          return this.subscribers.slice(this.page*5, Math.min(this.page*5+6,this.subscribers.length));
        }
        return this.subscribers.slice(this.page*4, Math.min(this.page*4+5,this.subscribers.length));
      }
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
    },
    shareScreen() {
      let screen = this.OV.initPublisher(undefined, {
        resolution: "1280x720",
        videoSource: "screen",
        publishAudio : this.setting.publishAudio,
      });

      screen.once("accessAllowed", () => {
        screen.stream
          .getMediaStream()
          .getVideoTracks()[0]
          .addEventListener("ended", () => {
            console.log('User pressed the "Stop sharing" button');
            this.session.unpublish(screen);
            this.sharing = false;
            this.share.active = false;
            this.share.screen = undefined;
            this.session.publish(this.publisher);
          });
        
        this.session.unpublish(this.publisher);
        this.sharing = true;
        this.share.active = true;
        this.share.screen = screen;
        this.session.publish(this.share.screen);
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