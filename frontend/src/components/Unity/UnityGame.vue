<template>
    <div id="unity-game" :class="{'small-map' : !showMap}" @click="test">
        <div id="game-container">
            <unity
            src = "/unity/Build/webGL.json"
            unityLoader = "/unity/Build/UnityLoader.js"
            width = '100%'
            height = '100%'
            ref = "hookInstance"
            >
            </unity>
            <button id="link-btn" @click="getUnityHook" v-if="!linked">계정 연동</button>
        </div>
        <div id="unity-school-name" hidden></div>
        <div id="unity-object-name" hidden></div>
    </div>
</template>
<script>
import Unity from 'vue-unity-webgl';
export default {
    name : "UnityGame",
    data() {
        return {
            userName : 'dovvn',
            objectName : '',
            schoolName : '',
            linked : false,
        }
    },
    components : {Unity},
    computed : {
        showMap : function(){
            if(this.$route.name == 'Unity'){
                return true;
            }
            else false;
        }
    },
    methods : {
        getUnityHook(){
            this.$refs.hookInstance.message('LobbyManager','initPlayerName',this.userName);
            this.linked = true;
            this.objectName = "";
            setInterval(()=>{
                if(document.getElementById('unity-object-name').innerHTML != this.objectName){
                    this.objectName = document.getElementById('unity-object-name').innerHTML;
                    // this.width = '150';
                    // this.height = '100';
                    this.showMap = false;
                    switch (this.objectName) {
                        case "blackboard": // 정보공유/코드공유 - information
                            this.$router.push({name : 'InfoBoard'});
                            break;
                        case "noticeboard": // Q&A게시판 - qna
                            this.$router.push({name : 'QnaBoard'});
                            break;
                        case "desk": // 컴퓨터실 웹캠 - computer
                            this.$router.push({name : 'Computer', params : {schoolName : this.schoolName, userName : this.userName}});
                            break;
                        case "vendingmachine": // 휴게실 웹캠 - rest
                            this.$router.push({name : 'Rest', params : {schoolName : this.schoolName, userName : this.userName}});
                            break;
                        case "table": // 급식실 웹캠 - cook
                            this.$router.push({name : 'Cook', params : {schoolName : this.schoolName, userName : this.userName}});
                            break;
                        case "calendar": // 공부 플래너
                            this.$router.push({name : 'Calendar'});
                            break;
                        case "advice": // 쓴소리 영상 - youtube
                            this.$router.push({name : 'Youtube'});
                            break;
                        case "guestbook":
                            this.$router.push({name : 'GuestBook'});
                            break;
                        case "award":
                            this.$router.push({name : 'Award'});
                            break;
                        default:
                            break;
                    }
                }else if(document.getElementById('unity-school-name').innerHTML != this.schoolName){
                    this.schoolName = document.getElementById('unity-school-name').innerHTML;
                    //get 해서 방번호 저장하기
                }
            },1000);
        },
        test(){
            if(!this.showMap){
                this.showMap = true;
                this.$router.push({name : "Unity"});
            }
        }
    }
}
</script>
<style scoped>
#unity-game{
    position: absolute;
    right: 15%;
    bottom: 0;
    width : 80%;
}
#game-container{
    width: 100%;
}
.small-map{
    top: 85vh!important;
    left: 90vw!important;
    width : 10%;
    height: 10%;
    cursor: pointer;
}
#link-btn{
    position: absolute;
    bottom : 10%;
    left : 45%;
    background-color: var(--color-white);
    border : 1px solid var(--color-white);
    border-radius: 15px;
    padding: 10px;
    font-size : var(--font-size-18);
    font-family: 'AppleSDGothicNeoB';
    font-weight: var(--weight-regular);
    color : var(--color-grey-1);
}
</style>