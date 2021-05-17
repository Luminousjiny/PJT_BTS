<template>
    <div id="library">
        <Nav/>
        <stop-watch/>
        <div id="library-container">
            <router-view/>
        </div>
    </div>
</template>

<script>
import Nav from "@/common/Nav/Nav"
import StopWatch from '../../components/Library/StopWatch.vue'
export default {
    name : 'Library',
    components : {
      Nav,
      StopWatch,
    },
}
</script>

<style scoped>
#library{
  display: flex;
  height : 100vh;
  flex-direction: column;
}
#library-container{
    width : 80%;
    margin : auto;
    flex : 1;
}
</style>