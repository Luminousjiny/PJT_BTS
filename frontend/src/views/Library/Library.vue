<template>
    <div id="library">
        <Nav/>
        <div id="library-container">
            <router-view/>
        </div>
    </div>
</template>

<script>
import Nav from "@/common/Nav/Nav"
export default {
    name : 'Library',
    components : {
      Nav,
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