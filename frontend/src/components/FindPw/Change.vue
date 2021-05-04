<template>
  <div class="confirm">
      <div class="find_content">
      <div class="find_leftBox">
         <div class="find_input">
          <h1 class="find_header">비밀번호 찾기</h1>
          <p class="text">변경할 비밀번호를 설정해주세요.</p>

          <p class="mid_find_header">비밀번호</p>
          <v-text-field style="padding-top:0px" class="find_input_c" placeholder="8자~11자입니다. " single-line></v-text-field>

          <p class="mid_find_header">비밀번호 확인</p>
          <v-text-field style="padding-top:0px" class="find_input_c" placeholder="비밀번호를 다시 입력해주세요. " single-line></v-text-field>
          
          <div class="text-center">
            <v-btn
              class="find_btn"
              rounded
              color="#04338C"
              dark
            >변경하기</v-btn>
          </div>
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