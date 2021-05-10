<template>
  <div id="cook">
    <Nav />
    <web-cam :location="location"/>
  </div>  
</template>

<script>
import Nav from "@/common/Nav/Nav";
import WebCam from '@/common/WebCam/WebCam';
export default {
  name : "Cook",
  components : {
      Nav,
      WebCam,
  },
  data() {
    return {
      location : "cook"
    }
  },
}
</script>

<style scoped>
#cook{
  display: flex;
  height : 100vh;
  overflow: hidden;
  flex-direction: column;
}
</style>