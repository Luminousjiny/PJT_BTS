<template>
    <div id="youtube-main">
        <div id="youtube-videos" class="scroll">
            <div @click="showVideoDetail(video)" :class="{youtube : true, 'flex-item':true,'share-youtube' : true}" v-for="(video, index) in videoList" :key="index">
                <img class="youtube-thumbnails" :src="video.snippet.thumbnails.medium.url">
                <div class="youtube-play"><v-icon class="play">fas fa-play</v-icon></div>
                <p class="youtube-title" >{{video.snippet.title}}</p>
                <p class="youtube-channel">{{video.snippet.channelTitle}}</p>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
const YOUTUBE_KEY = 'AIzaSyAtjCXrIEz4k2kainW4AWnqwaeiX-LV7cw';

export default {
    name : "YoutubeList",
    data() {
        return {
            videoList : [],
        }
    },
    props : {
        youtubeShare : Object,
    },
    created() {
        this.getYoutubeVideos();
    },
    methods: {
        getYoutubeVideos(){
            let http = 'https://www.googleapis.com/youtube/v3/videos?';
            http += `key=${YOUTUBE_KEY}&part=snippet&chart=mostPopular&regionCode=kr&maxResults=50`

            axios.get(http).then(( data ) => {
                this.videoList = data.data.items;
                console.log(data);
            }).catch((error) => {
                console.log(error);
            })
        },
        showVideoDetail(video){
            this.$emit('showVideoDetail', video);
        },
    },

}
</script>

<style scoped>
@import '../../css/style.css';
@import '../../css/WebCam.css';
@import '../../css/Youtube.css';
</style>