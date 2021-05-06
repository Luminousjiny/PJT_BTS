<template>
  <div id="nav">
      <div class="nav-container">
        <div class="logo"><img src="@/../public/Image/bts_logo.png" id="bts-icon"></div>
        <div id="user-container">
          <div id="user-img">
            <img src="@/../public/Image/user_profile.png" id="user-icon">
            <div id="user-level">
              <img src="@/../public/Image/level/level_bronze_1.png" id="level-icon">
            </div>
          </div>
          <div id="user-name"><p>장국님</p></div>
          <div id="menu-container">
            <div id="menu-dropdown" @click="showMenu = !showMenu">
              <v-icon v-if="!showMenu">fas fa-angle-down</v-icon>
              <v-icon v-if="showMenu">fas fa-angle-up</v-icon>
            </div>
            <ul id="menus" :class="{'menu-inactive': !showMenu,'menu-active' : showMenu}">
              <li><p>캐릭터 변경</p></li>
              <li><p>마이페이지</p></li>
              <li><p>로그아웃</p></li>
            </ul>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
    name : "Nav",
    data() {
      return {
        showMenu : false,
        menus : [
          {title : '캐릭터 변경'},
          {title : '마이페이지'},
          {title : '로그아웃'},
        ],
      }
    },
    mounted() {
      document.addEventListener("click", function(event) {
        if (event.target.closest("#menu-container")) return;
        this.showMenu = false;
      }.bind(this));
    },
    methods: {
      showMenus(){
        console.log(this.showMenu);
        this.showMenu = !this.showMenu;
      },
    },

}
</script>

<style scoped>
@import "../../css/style.css";
@import "../../css/Nav.css";
</style>