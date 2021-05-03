
<template>
  <!--배경-->
  <div class="mypage">
    <!--가운데 내용-->
    <div class="mypage_content">
      <div class="content_top">
        <div class="top_leftBox">
          <p class="left_h">마이페이지</p>
          <p class="left_p">정보를 변경해주세요</p>
          <p class="left_name">장국 님</p>
          <div class="left_img">
            <img
              class="user_img"
              src="../../../public/Image/user_profile.png"
            />
          </div>
          <ul class="left_ul">
            <li>수정</li>
            <li>|</li>
            <li>삭제</li>
          </ul>
          <p class="mid_header">닉네임</p>
          <v-text-field class="input" single-line></v-text-field>
        </div>
        <div class="top_rightBox">
          <router-link class="goCamera" to="/camSetting"
            >카메라 설정 변경 >></router-link
          >

          <p class="mid_header">비밀번호</p>
          <v-text-field class="input" single-line></v-text-field>

          <p class="mid_header">비밀번호 확인</p>
          <v-text-field class="input" single-line></v-text-field>

          <p class="mid_header">핸드폰번호</p>

          <v-container style="width: 78%; margin-left: -0.000001vw">
            <v-row>
              <v-col cols="12" sm="4">
                <v-select :items="newAgency" label="통신사" solo></v-select>
              </v-col>
              <v-col cols="12" sm="6" >
                <v-text-field label="01071231815" solo></v-text-field>
              </v-col>
              <v-col cols="12" sm="2">
                <v-btn class="send_msg" color="#04338C" dark>중복확인</v-btn>
              </v-col>
            </v-row>
          </v-container>

          <p class="mid_header">인증번호</p>
          <v-row>
            <v-col cols="12" sm="8">
              <v-text-field
                class="input input_certify"
                single-line
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="4">
              <v-btn class="certifyBtn" color="#04338C" dark>인증</v-btn>
            </v-col>
          </v-row>
        </div>
      </div>
      <!--완료-->
      <div class="content_btm">
        <v-btn class="modify_btn" rounded color="#04338C" dark>완료</v-btn>
        <a href="#" class="goDelete">탈퇴하기</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyPage",
  data() {
    return {
      newAgency: ["SKT", "KT", "LG U+"],
    };
  },
  components: {},
};
</script>


<style scoped>
@import "../../css/MyPage.css";
</style>

