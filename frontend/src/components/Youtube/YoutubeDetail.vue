<template>
    <div id="youtube-detail">
        <div class="youtube-video flex-item">
            <iframe type="text/html" :src="'https://www.youtube.com/embed/'+videoDetail.id" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="youtube-info flex-item scroll">
            <p class="youtube-title" >{{videoDetail.snippet.title}}</p>
            <p class="youtube-channel">{{videoDetail.snippet.channelTitle}}</p>
            <div class="youtube-description">
                <br>
                <p class="btnShowDesc" v-if="!showDescription" @click="showDescription = true">더보기</p>
                <p v-if="showDescription">{{videoDetail.snippet.description}}</p>
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
}
</script>

<style scoped>
@import '../../css/style.css';
@import '../../css/WebCam.css';
@import '../../css/Youtube.css';
</style>