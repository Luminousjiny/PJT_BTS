<template>
  <div id="app" data-app>
    <router-view/>
    <UnityGame/>
  </div>
</template>
<script>
import UnityGame from '@/components/Unity/UnityGame';
export default {
  name : "App",
  components : {
    UnityGame,
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
