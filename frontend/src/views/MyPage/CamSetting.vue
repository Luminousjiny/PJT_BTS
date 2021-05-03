
<template>
  <!--배경-->
  <div class="camSetting">
    <!--가운데 내용-->
    <div class="camSetting_content">
      <div class="content_inner">
        <p class="h">마이페이지</p>
        <p class="p">카메라 환경설정을 해주세요</p>
        <div class="boxes">
          <div class="left_box"></div>
          <div class="right_box">
            <ul class="box_ul">
              <li>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="1">
                      <i class="fas fa-video icon fa-lg"></i>
                    </v-col>
                    <v-col cols="12" sm="11">
                      <v-select
                        :items="webcam"
                        label="카메라 설정"
                        filled
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </li>
              <li>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="1">
                      <i class="fas fa-microphone icon fa-lg"></i>
                    </v-col>
                    <v-col cols="12" sm="11">
                      <v-select
                        :items="mic"
                        label="마이크 설정"
                        filled
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </li>
              <li>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="1">
                      <i class="fas fa-volume-up icon fa-lg"></i>
                    </v-col>
                    <v-col cols="12" sm="11">
                      <v-select
                        :items="speaker"
                        label="스피커 설정"
                        filled
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </li>
            </ul>
          </div>
        </div>
        <v-btn class="modify_btn" rounded color="#04338C" dark>변경 완료</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CamSetting",
  data() {
    return {
      webcam: ["lifecam"],
      mic: ["realtek"],
      speaker: ["realtek"],
    };
  },
  components: {},
};
</script>


<style scoped>
@import "../../css/CamSetting.css";
</style>

