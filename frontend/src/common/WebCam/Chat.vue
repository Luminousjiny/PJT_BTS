<template>
<div id="chat-container">
    <div id="chat-nav">
        <div class="icon">Icon</div>
        <div class="icon icon-bottom">Icon</div>
        <div class="icon icon-bottom">Icon</div>
        <div>{{ participants }}명</div>
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
            <button id="btnSendMessage" @click="send">입력</button>
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
        }
    },
}
</script>

<style scoped>
@import '../../css/Chat.css';
</style>