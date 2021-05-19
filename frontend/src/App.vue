<template>
  <div id="app" data-app>
    <router-view />
    <UnityGame v-if="$store.state.user!==null && showUnity"/>
  </div>
</template>
<script>
import UnityGame from '@/components/Unity/UnityGame';
export default {
  name : "App",
  data() {
    return {
      linked : false,
    }
  },
  components : {
    UnityGame,
  },
  computed : {
    showUnity : function(){
      if(this.$route.name === "MyPage" || this.$route.name === "CamSetting"){
        return false;
      }
      return true;
    }
  }
}
</script>
<style>
@import "./css/style.css";
* {
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
}
</style>
