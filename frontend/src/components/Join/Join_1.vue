<template>
  <div class="join">
    <div class="join_content">
      <div class="leftBox">
         <div class="login_input">
          <h1 class="header">회원가입</h1>
          <p class="text">회원정보를 입력하세요.</p>

          <p class="mid_header">아이디</p>
          <v-text-field class="input" placeholder="최대 10자입니다. " single-line></v-text-field>
          <!-- <v-btn
            depressed
            color="#04338C"
            >
            중복확인</v-btn> -->
        <v-btn
        elevation="2"
        outlined
        >중복확인</v-btn>

          <p class="mid_header">닉네임</p>
          <v-text-field class="input" placeholder="최대 6자입니다. " single-line></v-text-field>

          <p class="mid_header">비밀번호</p>
          <v-text-field class="input" placeholder="8자~11자입니다. " single-line></v-text-field>

          <p class="mid_header">비밀번호 확인</p>
          <v-text-field class="input" placeholder="비밀번호를 다시 입력해주세요. " single-line></v-text-field>

          <div class="text-center">
            <v-btn
              class="join_btn"
              rounded
              color="#04338C"
              dark
            >JOIN</v-btn>
          </div>
        </div>
      </div>
      <div class="rightBox">
        <img class="login_img" src="../../../public/Image/join_img.png">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Join",
  data () {
      return {

      }
    },
  components: {
  },
};
</script>

<style scoped>
@import '../../css/Join1.css';
</style>
