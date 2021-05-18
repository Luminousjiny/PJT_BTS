<template>
  <div id="computer">
    <Nav />
    <web-cam :location="location"/>
  </div>  
</template>

<script>
import Nav from "@/common/Nav/Nav";
import WebCam from '@/common/WebCam/WebCam';
export default {
  name : "Computer",
  components : {
      Nav,
      WebCam,
  },
  data() {
    return {
      location : "computer",
    }
  },
}
</script>

<style scoped>
#computer{
  display: flex;
  height : 100vh;
  overflow: hidden;
  flex-direction: column;
}
</style>