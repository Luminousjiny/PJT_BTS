<template>
    <div id="unity">
        <unity
            src = "/unity/Build/webGL.json"
            width ="900"
            height = "670"
            unityLoader = "/unity/Build/UnityLoader.js"
            ref = "hookInstance"
        >
        </unity>
        <div id="unity-school-name" hidden></div>
        <div id="unity-object-name" hidden></div>
        <button @click="getUnityHook">연동</button>
    </div>
</template>
<script>
import Unity from 'vue-unity-webgl';
export default {
    data() {
        return {
            userName : 'dovvn',
            schoolName : '',
        }
    },
    components : {Unity},
    methods : {
        getUnityHook(){
            this.$refs.hookInstance.message('LobbyManager','initPlayerName',this.userName);
            let objectName = "";
            setInterval(()=>{
                if(document.getElementById('unity-object-name').innerHTML != objectName){
                    objectName = document.getElementById('unity-object-name').innerHTML;
                    switch (objectName) {
                        case "information": // 정보공유/코드공유
                            this.$router.push({name : 'InfoBoard'});
                            break;
                        case "qna": // Q&A게시판
                            this.$router.push('/board/qna');
                            break;
                        case "computer": // 컴퓨터실 웹캠
                            this.$router.push({name : 'Computer', params : {schoolName : this.schoolName}});
                            break;
                        case "rest": // 휴게실 웹캠
                            this.$router.push({name : 'Rest', params : {schoolName : this.schoolName}});
                            break;
                        case "cook": // 급식실 웹캠
                            this.$router.push({name : 'Cook', params : {schoolName : this.schoolName}});
                            break;
                        case "calendar": // 공부 플래너
                            this.$router.push('/library/calendar');
                            break;
                        case "youtube": // 쓴소리 영상
                            this.$router.push('/library/youtube');
                            break;
                        case "questbook":
                            this.$router.push('/gb');
                            break;
                        case "desk":
                            this.$router.push({name : 'Computer', params : {schoolName : this.schoolName, userName : this.userName}});
                            break;
                        default:
                            break;
                    }
                }
            },1000);

            setInterval(()=>{
                if(document.getElementById('unity-school-name').innerHTML != ''){
                    this.schoolName = document.getElementById('unity-school-name').innerHTML;
                }
            },1000);

        },
    }
}
</script>
