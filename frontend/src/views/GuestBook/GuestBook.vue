<template>
  <div class="guestbook">
    <h1 class="h1">방명록</h1>
    <div class="gb_area">
      <div class="left_btn" v-on:click="goLeft">
        <i class="fas fa-chevron-left fa-2x"></i>
      </div>
      <div class="gb_book">
        <table class="gb_ul ul1">
          <tr v-for="(u, index) in users.slice(nowNum*24,(nowNum*24)+12)" :key="index">
            <td class="user_id">{{u.user.userId}}</td>
            <td class="user_nickname">{{u.user.userNickname}}</td>
            <td class="visit_date">{{u.visitDate}}</td>
          </tr>
        </table>

        <table class="gb_ul ul2">
          <tr v-for="(u, index) in users.slice((nowNum*24)+12,(nowNum*24)+24)" :key="index">
            <td class="user_id">{{u.user.userId}}</td>
            <td class="user_nickname">{{u.user.userNickname}}</td>
            <td class="visit_date">{{u.visitDate}}</td>
          </tr>
        </table>
      </div>
      <div class="right_btn" v-on:click="goRight">
        <i class="fas fa-chevron-right fa-2x"></i>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "GuestBook",
  components: {},
  data() {
    return {
      roomId: 1,
      userId: "dovvn",
      users: [],
      nowNum: 0,
    };
  },
  created() {
     http
      .post(`/v1/gb/${this.roomId}/${this.userId}`)
      .then((res) => {
        this.users = res.data.data;
      })
      .catch((err) => console.error(err));
  },
  methods: {
    goLeft(){
      this.nowNum--;
      if(this.nowNum<0)this.nowNum++;
      
    },
    goRight(){
      this.nowNum++;
      if(this.nowNum>this.users.length/24) this.nowNum--;
    },
  },
};
</script>

<style scoped src="../../css/GuestBook.css">
