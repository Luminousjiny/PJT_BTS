<template>
  <div class="login">
    <div class="content">
      <div class="leftBox">
        <img class="login_img" src="../../../public/Image/login_img.png"> 
      </div>
      <div class="rightBox">
        <div class="login_input">
          <h1 class="header">로그인</h1>
          <p class="text">환영합니다. 로그인 해주세요!</p>

          <p class="mid_header">아이디</p>
          <v-text-field class="input" placeholder="최대 10자입니다. " single-line></v-text-field>

          <p class="mid_header">비밀번호</p>
          <v-text-field class="input" placeholder="8자~11자입니다. " single-line></v-text-field>

          <input type="checkbox" id="checkbox" v-model="checked">
          <label class="id_remember" for="checkbox">아이디 기억하기</label>

          <div class="text-center">
            <v-btn
              class="login_btn"
              rounded
              color="#04338C"
              dark
            >LOGIN</v-btn>
          </div>
          <div class="moves">
            <router-link class="move" to="/join">회원가입</router-link> | 
            <router-link class="move" to="/findPw">비밀번호 찾기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data () {
      return {
        checked: false
      }
    },
  components: {
  },
};
</script>

<style scoped>
@import '../../css/Login.css';
</style>
