<template>
  <div class="login">
    <div class="content">
      <v-row>
        <v-col cols="12" sm="6" class="left">
          <img class="login_img" src="../../../public/Image/login_img.png"> 
        </v-col>
        <v-col cols="12" sm="6">
          <div>
            <h1>로그인</h1>
            <p>환영합니다. 로그인 해주세요!</p>

            <p>아이디</p>
            <v-text-field
              label="최대 10자 "
              single-line></v-text-field>

            <p>비밀번호</p>
            <v-text-field
              label="8자~11자 "
              single-line></v-text-field>

            <v-checkbox
                v-model="checkbox"
                :label="`Checkbox 1: ${checkbox.toString()}`"
                color ="red"
              ></v-checkbox>
          </div>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data () {
      return {
        checkbox: true,
      }
    },
  components: {
  },
};
</script>

<style scoped>
@import '../../css/Login.css';
</style>
