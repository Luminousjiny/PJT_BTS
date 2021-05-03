<template>
  <div class="join1">
    <div class="join_content">
      <div class="join_leftBox">
         <div class="join_input">
          <h1 class="join_header join2_head">회원가입</h1>
          <p class="text">본인인증을 해주세요.</p>

          <p class="mid_join_header">핸드폰번호</p>
          <v-text-field style="padding-top:0px" class="join_input join_input_phone" placeholder="핸드폰번호를 입력해주세요. " single-line></v-text-field>
          <!-- 👨🏻 토글 예정  -->
          <!-- <v-btn
            class="join_id_check_btn"
            style="color:white;"
            depressed
            color="#04338C"
            >
            중복확인</v-btn> -->
        <v-btn
          class="join_id_check_btn"
          outlined
           color="#04338C"
          >전송</v-btn>

          <p class="mid_join_header">인증번호</p>
          <v-text-field style="padding-top:0px" class="join_input" placeholder="발송된 인증번호 6자리를 입력해주세요" single-line></v-text-field>

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
      <div class="join_rightBox">
        <img class="join_img" src="../../../public/Image/join_img.png">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Join2",
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
