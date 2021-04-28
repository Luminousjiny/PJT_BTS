<template>
<div id="chat-container">
    <div id="chat-nav">
        <div class="icon" @click="showChange(0)">Icon</div>
        <div @click="showChange(1)">
            <v-icon id="message-icon" :class="{active : showChatting}">fas fa-comments</v-icon>
        </div>
        <div @click="showChange(2)">
            <v-icon id="participant-icon" :class="{active : showUsers}">fas fa-users</v-icon>
        </div>
        <div id="live-container">
            <div id="live-circle"></div>
            <div id="participant-counter">{{ participants }}명</div>
        </div>
    </div>
    <div id="chat-main">
        <div id="chat-title">
            <p>Chat</p>
        </div>
        <div id="receive-container">
            <div class="message"  v-for="(message, index) in receiveMessage" :key="index">
            <table class="message-table" v-if="message.sender.userName !== userName">
                <tr class="user-profile">
                <td class="user-img" rowspan="2"></td>
                <td class="user-name">{{message.sender.userName}}</td>
                </tr>
                <tr>
                <td class="user-message">{{message.message}}</td>
                </tr>
            </table>
            <table class="message-table my-message" v-else>
                <tr class="user-profile">
                <td class="user-name">{{message.sender.userName}}</td>
                <td class="user-img" rowspan="2"></td>
                </tr>
                <tr>
                <td class="user-message">{{message.message}}</td>
                </tr>
            </table>
            </div>
        </div>
        <div id="input-container">
            <div id="input-form">
            <input id="sendMessage" class="input-message" type="text" v-model="sendMessage" @keyup.enter="send">
            <v-icon id="btnSendMessage" @click="send">fas fa-location-arrow</v-icon>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name : "Chat",
    data() {
        return {
            sendMessage : "",
            showChatting : false,
            showUsers : false,
        }
    },
    props :{
        session : Object,
        userName : String,
        receiveMessage : Array,
        participants : Number,
    },
    methods: {
        send(){
            this.$emit('sendMessage', this.sendMessage);
            this.sendMessage = '';
        },
        showChange(type){
            if(type == 1){
                this.showChatting = true;
                this.showUsers = false;
            }else if(type == 2){
                this.showChatting = false;
                this.showUsers = true;
            }else{
                this.showChatting = false;
                this.showUsers = false;
            }
        }
    },
}
</script>

<style scoped>
@import '../../css/Chat.css';
</style>