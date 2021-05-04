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
            <div id="participant-counter">{{ data.participants }}명</div>
        </div>
    </div>
    <div id="chat-main" v-if="showChatting || showUsers">
        <div id="chat-title">
            <p v-if="showChatting">Chat</p>
            <p v-if="showUsers">User</p>
        </div>
        <div id="receive-container" v-if="showChatting">
            <div class="message"  v-for="(message, index) in data.receiveMessage" :key="index">
            <table class="message-table" v-if="message.sender.userName !== data.userName">
                <tr class="user-profile">
                <td class="user-img" rowspan="2"><v-icon color="white">fas fa-smile</v-icon></td>
                <td class="user-name">{{message.sender.userName}}</td>
                </tr>
                <tr>
                <td class="user-message">{{message.message}}</td>
                </tr>
            </table>
            <table class="message-table my-message" v-else>
                <tr class="user-profile">
                <td class="user-name">{{message.sender.userName}}</td>
                <td class="user-img" rowspan="2"><v-icon color="white">fas fa-smile</v-icon></td>
                </tr>
                <tr>
                <td class="user-message">{{message.message}}</td>
                </tr>
            </table>
            </div>
        </div>
        <div id="participant-container" v-if="showUsers">
            <div class="participant-info my-info">
                <v-icon color="white">fas fa-smile</v-icon>
                {{JSON.parse(data.publisher.session.connection.data).userName}} (me)
            </div>
            <div class="participant-info" v-for="(sub, index) in data.subscribers" :key="index">
                <v-icon color="white">fas fa-smile</v-icon>
                {{JSON.parse(sub.stream.connection.data).userName}}
            </div>
        </div>
        <div id="input-container" v-if="showChatting">
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
        data : Object,
    },
    updated() {
        let container = this.$el.querySelector("#receive-container");
        container.scrollTop = container.scrollHeight;
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