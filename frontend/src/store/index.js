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
    setSchool(state,schoolId,schoolName){
      state.schoolId=schoolId;
      state.schoolName=schoolName;
    }
  },
  actions: {},
  modules: {},
});
