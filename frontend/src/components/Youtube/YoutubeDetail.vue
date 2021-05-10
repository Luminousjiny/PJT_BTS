<template>
    <div id="youtube-detail">
        <div class="youtube-video flex-item">
            <iframe type="text/html" :src="'https://www.youtube.com/embed/'+videoID" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="youtube-info flex-item scroll">
            <p class="youtube-title" v-html="videoDetail.snippet.title"/>
            <p class="youtube-channel" v-html="videoDetail.snippet.channelTitle"/>
            <div class="youtube-description">
                <br>
                <p class="btnShowDesc" v-if="!showDescription" @click="showDescription = true">더보기</p>
                <p v-if="showDescription" v-html="videoDetail.snippet.description"/>
                <br>
                <p v-if="showDescription" class="btnShowDesc" @click="showDescription = false">간략히</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name : "YoutubeDetail",
    data() {
        return {
            showDescription : false,
        }
    },
    props : {
        videoDetail : Object,
    },
    computed : {
        videoID : function(){
            if(typeof(this.videoDetail.id) != "object"){
                return this.videoDetail.id;
            }else{
                return this.videoDetail.id.videoId;
            }
        }
    }
}
</script>

<style scoped scr="../../css/Youtube.css">
</style>