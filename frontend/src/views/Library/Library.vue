<template>
    <div id="library">
        <Nav/>
        <div class="tooltip">
            <audio autoplay controls loop>
                <source src="../../../public/music/noise-extra-loop.mp3">
            </audio>
            <span class="tooltiptext">백색소음으로 공부에 집중해보세요.</span>
        </div>
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
.tooltip {
    margin-left: 70%;
    margin-top: 10px;
    height: 0px;
  position: relative;
  display: inline-block;
}
.tooltip .tooltiptext {
  visibility: hidden;
  width: 200px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -60px;
  opacity: 0;
  transition: opacity 0.3s;
  font-size: var(--font-size-14);
    font-family: "AppleSDGothicNeoB";
}

.tooltip .tooltiptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}
</style>