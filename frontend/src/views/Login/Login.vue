<template>
  <div class="login">
    <div class="login_content">
      <div class="login_leftBox">
        <img class="login_img" src="../../../public/Image/login_img.png" />
      </div>
      <div class="login_rightBox">
        <div class="login_input">
          <h1 class="login_header">로그인</h1>
          <p class="text">환영합니다. 로그인 해주세요</p> 

          <form @submit="onSubmit" class="login-form">
            <p class="login_mid_header">아이디</p>
            <v-text-field
              id="user-id"
              v-model="id"
              class="login_input_c"
              placeholder="최대 10자입니다. "
              single-line
            ></v-text-field>

            <p class="login_mid_header">비밀번호</p>
            <v-text-field
              id="user-pw"
              v-model="password"
              class="login_input_c"
              placeholder="8자~11자입니다. "
              single-line
            ></v-text-field>

            <input type="checkbox" id="checkbox" v-model="checked" />
            <label class="id_remember" for="checkbox">아이디 기억하기</label>

            <div class="text-center">
              <v-btn class="login_btn" :class="classObject" rounded color="#04338C" dark>LOGIN</v-btn>
            </div>
          </form>
          <div class="moves">
            <router-link class="move" to="/join">회원가입</router-link> |
            <router-link class="move" to="/findpw">비밀번호 찾기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";

export default {
  name: "Login",
  data: () => {
    return {
      checked: false,
      id: "",
      password: "",
      passwordSchema: new PV(),
      component : this
    }
  },
  created(){
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(11)
      .has()
      .digits()
      .has()
      .letters();
  },
  computed: {
    classObject: function(){
      if(this.id.length>0 && this.password.length>0 )
        return "purple_btn login-form_btn active"
      return "purple_btn login-form_btn inactive"
    },
  },
  methods: {

  }
};
</script>

<style scoped src="../../css/Login.css">
</style>
