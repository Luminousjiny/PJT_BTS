<template>
    <div id="unity-game" :class="{'small-map':!showMap}" @click="goUnity">
        <div id="game-container">
            <unity id="bts-unity"
            src = "./unity/Build/webGL.json"
            unityLoader = "./unity/Build/UnityLoader.js"
            ref = "hookInstance"
            :height = height
            :width = width
            :hideFooter="true"
            >
            </unity>
            <button id="link-btn" @click="getUnityHook" v-if="!linked">계정 연동</button>
        </div>
        <div id="unity-school-name" hidden></div>
        <div id="unity-object-name" hidden></div>
        <div id="unity-users-name" hidden></div>
    </div>
</template>
<script>
import Unity from 'vue-unity-webgl';
import http from '../../util/http-common.js';

export default {
    name : "UnityGame",
    data() {
        return {
            user: {},
            objectName : '',
            schoolName : '',
            linked : false,
            height : '700',
            width : '950',
            interval : '',
        }
    },
    components : {Unity},
    created(){
      if(this.$store.state.user===null){
        this.$router.push('/');
      }
      this.schoolName=this.$store.state.schoolName;
      this.user=this.$store.getters.getUser;
    },
    destroyed(){
        clearInterval(this.interval);
    },
    computed : {
        showMap : function(){
            if(this.$route.name == 'Unity'){
                this.width = '950';
                this.height = '700';
                return true;
            }else{
                this.width = '150';
                this.height = '100';
                return false;
            }
        }
    },
    methods : {
        getUnityHook(){
            this.$refs.hookInstance.message('LobbyManager','initPlayerName',this.user.userNickname);
            this.linked = true;
            this.objectName = "";
            this.interval = setInterval(()=>{
                if(document.getElementById('unity-object-name').innerHTML != this.objectName){
                    this.objectName = document.getElementById('unity-object-name').innerHTML;
                    switch (this.objectName) {
                        case "information": // 정보공유/코드공유 - blackboard
                            this.$router.push({name : 'InfoBoard'});
                            break;
                        case "qna": // Q&A게시판 - noticeboard
                            this.$router.push({name : 'QnaBoard'});
                            break;
                        case "computer": // 컴퓨터실 웹캠 - desk
                            this.$router.push({name : 'Computer'});
                            break;
                        case "rest": // 휴게실 웹캠 - vendingmachine
                            this.$router.push({name : 'Rest'});
                            break;
                        case "cook": // 급식실 웹캠 - table
                            this.$router.push({name : 'Cook'});
                            break;
                        case "calendar": // 공부 플래너
                            this.$router.push({name : 'Calendar'});
                            break;
                        case "youtube": // 쓴소리 영상 - advice
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
                    document.getElementById('unity-object-name').innerHTML = "";
                }else if(document.getElementById('unity-school-name').innerHTML!=="" && document.getElementById('unity-school-name').innerHTML !== this.schoolName){
                    this.schoolName = document.getElementById('unity-school-name').innerHTML;
                    //get 해서 방번호 저장하기
                    http.get(`v1/room/${this.schoolName}`)
                      .then(res=>{
                        console.log(res);
                        if(res.data.data==="존재하지 않는 방입니다."){
                          http.post(`v1/room/${this.schoolName}`)
                            .then(res2=>{
                              this.$store.commit('setSchoolId',res2.data.data);
                              this.$store.commit('setSchoolName',this.schoolName);
                              console.log('방 생성');
                              console.log(res2.data.data,this.schoolName);
                            })
                            .catch(err=>{
                              console.error(err);
                            })
                        } else{
                          this.$store.commit('setSchoolId',res.data.data);
                          this.$store.commit('setSchoolName',this.schoolName);
                          console.log('방 찾음');
                          console.log(res.data.data,this.schoolName);
                        }
                      })
                }
            },1000);
        },
        goUnity(){
            if(!this.showMap){
                this.height = '700';
                this.width = '950';
                this.$router.push({name : "Unity"});
            }
        }
    }
}
</script>
<style scoped>
#unity-game{
    width : 55%;
    height: max-content;
    position: absolute;
    bottom :20px;
    right : 10%;
}
#game-container{
    position: relative;
}
#link-btn{
    background-color: var(--color-white);
    padding : 10px;
    border : 1px solid var(--color-white);
    border-radius: 15px;
    position : absolute;
    bottom : 8%;
    left : 45%;
    font-size: var(--font-size-18);
    color: var(--color-grey-1);
    font-family: "AppleSDGothicNeoB";
}
.small-map{
    right : 0!important;
    width : 10%!important;
}
</style>