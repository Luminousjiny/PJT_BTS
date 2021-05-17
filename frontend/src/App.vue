<template>
  <div id="app" data-app>
    <router-view v-on:showMap="showMap"/>
    <UnityGame v-if="login"/>
  </div>
</template>
<script>
import UnityGame from '@/components/Unity/UnityGame';
export default {
  name : "App",
  components : {
    UnityGame,
  },
  data() {
    return {
      login : true,
    }
  },
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
