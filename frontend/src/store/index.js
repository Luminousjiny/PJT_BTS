import Vue from "vue";
import Vuex from "vuex";
import http from "../util/http-common"; 
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],  
  state: {
    user:null,
    schoolId: null,
    schoolName: null,
    audio: null,
    video: null,
  },
  getters:{
    getUser(state){
      if(state.user===null) return null;
      return {...state.user};
    },
    getUserId(state){
      if(state.user===null) return null;
      return state.user.userId;
    },
    getSchoolId(state){
      if(state.schoolId===null) return null;
      return state.schoolId;
    },
    getSchoolName(state){
      if(state.schoolName===null) return null;
      return state.schoolName;
    },
    getAudio(state){
      return state.audio;
    },
    getVideo(state){
      return state.video;
    }    
  },
  mutations: {
    login(state,user){
      state.user = user;
    },
    setUser(state,user){
      state.user = user;
    },
    logout(state){
      state.user= null;
      state.schoolId= null;
      state.schoolName= null;
    },
    setSchoolId(state,schoolId){
      state.schoolId=schoolId;
    },
    setSchoolName(state,schoolName){
      state.schoolName=schoolName;
    },
    setVideo(state,selected){
      state.video=selected;
    },
    setAudio(state,selected){
      state.audio=selected;
    }
  },
  actions: {},
  modules: {},
});
