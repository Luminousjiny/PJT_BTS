<template>
  <div class="confirm">
      <div class="find_content">
      <div class="find_leftBox">
         <div class="find_input">
          <h1 class="find_header">비밀번호 찾기</h1>
          <p class="text">본인인증을 해주세요.</p>

          <p  class="mid_find_header">아이디</p>
          <v-text-field style="padding-top:0px" class="find_input_c" placeholder="최대 10자입니다. " single-line></v-text-field>

          <p class="mid_find_header">핸드폰번호</p>
          <v-text-field style="padding-top:0px" class="find_input_c find_input_phone" placeholder="핸드폰번호를 입력해주세요. " single-line></v-text-field>
          <!-- 👨🏻 토글 예정  -->
          <!-- <v-btn
            class="join_id_check_btn"
            style="color:white;"
            depressed
            color="#04338C"
            >
            중복확인</v-btn> -->
        <v-btn
          class="find_id_check_btn"
          outlined
           color="#04338C"
          >전송</v-btn>

          <p class="mid_find_header">인증번호</p>
          <v-text-field style="padding-top:0px" class="find_input_c" placeholder="발송된 인증번호 6자리를 입력해주세요" single-line></v-text-field>

         <router-link class="move find_next" style="color:#04338C" to="/findpw/change">NEXT >></router-link>
          
        </div>
      </div>
      <div class="find_rightBox">
        <img class="find_img" src="../../../public/Image/pw_img.png">
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
@import '../../css/FindPw.css';
</style>