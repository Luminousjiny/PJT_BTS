<template>
  <div id="computer">
    <Nav />
    <web-cam :location="location" :schoolName="schoolName" :userName="userName"/>
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
      schoolName : '',
      userName : '',
    }
  },
  created(){
    this.schoolName = this.$route.params.schoolName;
    this.userName = this.$route.params.userName;
  }
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