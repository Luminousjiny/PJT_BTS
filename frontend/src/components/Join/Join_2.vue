<template>
  <div class="join1">
    <div class="join_content">
      <div class="join_leftBox">
         <div class="join_input">
          <h1 class="join_header join2_head">회원가입</h1>
          <p class="text">본인인증을 해주세요.</p>

          <p class="mid_join_header">핸드폰 번호</p>
          <v-text-field style="padding-top:0px" class="input" placeholder="숫자로만 입력해주세요.(11자)" single-line></v-text-field>

          <p class="mid_join_header">인증번호</p>
          <v-text-field style="padding-top:0px" class="input" placeholder="문자로 안내온 인증번호를 입력해주세요." single-line></v-text-field>

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
