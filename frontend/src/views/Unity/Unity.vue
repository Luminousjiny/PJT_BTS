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
        <div id="data-name"></div>
        <button @click="test">연동</button>
    </div>
</template>
<script>
import Unity from 'vue-unity-webgl';
export default {
    data() {
        return {
            userName : 'dovvn',
        }
    },
    components : {Unity},
    methods : {
        test(){
            let temp = "";
            this.$refs.hookInstance.message('LobbyManager','initPlayerName',this.userName);
            setInterval(()=>{
                if(document.getElementById('data-name').innerHTML != temp){
                    temp = document.getElementById('data-name').innerHTML;
                    if(temp == "guestbook"){
                        this.$router.push('/library/calendar');
                    }
                    window.alert(temp);
                }
            },1000);

        },
    }
}
</script>
