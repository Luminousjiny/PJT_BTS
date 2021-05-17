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
                <div id="participant-counter">{{ participants }}명</div>
            </div>
        </div>
    </div>
    <div id="chat-main">
        <div id="chat-title">
            <p>User</p>
        </div>
        <div id="participant-container">
            <div class="participant-info my-info">
                <v-icon color="white">fas fa-smile</v-icon>
                <p class="inline-p">{{userName}} (me)</p>
            </div>
            <div class="participant-info" v-for="(user, index) in users" :key="index">
                <v-icon color="white">fas fa-smile</v-icon>
                <p class="inline-p">{{user.userName}}</p>
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
            users : []
        }
    },
    props : {
        userName : String,
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