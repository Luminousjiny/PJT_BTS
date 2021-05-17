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
    roomId: null,
  },
  getters:{
    getUser(state){
      return {...state.user};
    },
    getRoomId(state){
      return state.roomId;
    }
  },
  mutations: {
    login(state,user){
      state.user = user;
    },
    logout(state){
      state.user=null;
      state.roomId= null;
    },
    setRoomId(state,roomId){
      state.roomId=roomId;
    }
  },
  actions: {},
  modules: {},
});
