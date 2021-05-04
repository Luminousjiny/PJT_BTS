<template>
  <div class="join1">
    <div class="join_content">
      <div class="join_leftBox">
         <div class="join_input">
          <h1 class="join_header">회원가입</h1>
          <p class="text">회원정보를 입력하세요.</p>

          <p class="mid_join_header">아이디</p>
          <v-text-field style="padding-top:0px" class="join_input join_input_id" placeholder="최대 10자입니다. " single-line></v-text-field>
          <!-- 👨🏻 토글 예정  -->
          <!-- <v-btn
            class="id_check_btn"
            style="color:white;"
            depressed
            color="#04338C"
            >
            중복확인</v-btn> -->
        <v-btn
          class="join_id_check_btn"
          outlined
           color="#04338C"
          >중복확인</v-btn>

          <p class="mid_join_header">닉네임</p>
          <v-text-field style="padding-top:0px" class="join_input_c" placeholder="최대 6자입니다. " single-line></v-text-field>

          <p class="mid_join_header">비밀번호</p>
          <v-text-field style="padding-top:0px" class="join_input_c" placeholder="8자~11자입니다. " single-line></v-text-field>

          <p class="mid_join_header">비밀번호 확인</p>
          <v-text-field style="padding-top:0px" class="join_input_c" placeholder="비밀번호를 다시 입력해주세요. " single-line></v-text-field>

          <!-- <div class="text-center">
            <v-btn
              class="join_btn"
              rounded
              color="#04338C"
              dark
            >JOIN</v-btn>
          </div> -->
          <router-link class="move join_next" style="color:#04338C" to="/join/check">NEXT >></router-link>
        </div>
      </div>
      <div class="join_rightBox">
        <img class="join_img" src="../../../public/Image/join_img.png">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Join1",
  data () {
      return {

      }
    },
  components: {
  },
};
</script>

<style scoped>
@import '../../css/Join.css';
</style>
