<template>
<div id="chat-container">
    <div id="chat-nav">
        <div class="icon">
            <img src="@/../public/Image/bts_favicon.png" id="bts-icon">
        </div>
        <div class="icon-bottom">
            <div>
                <v-icon id="participant-icon" class="active">fas fa-users</v-icon>
            </div>
            <div id="live-container">
                <div id="live-circle"></div>
                <div id="participant-counter">{{ users.length }}명</div>
            </div>
        </div>
    </div>
    <div id="chat-main">
        <div id="chat-title">
            <p>User</p>
        </div>
        <div id="participant-container">
            <div class="participant-info" v-for="(user, index) in users" :key="index">
                <v-icon color="var(--color-white)">fas fa-user</v-icon>
                <p class="inline-p">{{user}}</p>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name : "User",
    data() {
        return {
            participants : 1,
            users : [],
            usersName : "",
        }
    },
    props : {
        userName : String,
    },
    created() {
        setInterval(()=>{
            if(document.getElementById('unity-users-name').innerHTML != this.usersName){
                this.usersName = document.getElementById('unity-users-name').innerHTML;
                this.users = this.usersName.split(',');
                console.log(this.users);
            }
        },1000);
    },
    updated() {
        let container = this.$el.querySelector("#participant-container");
        if(container.scrollHeight != null){
            container.scrollTop = container.scrollHeight;
        }
    },
}
</script>

<style scoped src="../../css/Chat.css">
</style>