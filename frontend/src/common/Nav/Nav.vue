<template>
  <div id="nav">
    <div class="nav-container">
      <div class="logo">
        <img src="@/../public/Image/bts_logo.png" id="bts-icon" />
      </div>
      <div id="user-container">
        <div v-if="user!==null" id="user-img">
          <img v-if="user.userImg===''" src="@/../public/Image/user_profile.png" id="user-icon" />
          <img v-else :src="user.userImg" id="user-icon" />
          <div id="user-level">
            <img
              v-if="user.userLank==='bronze1'"
              src="@/../public/Image/level/level_bronze1.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='bronze2'"
              src="@/../public/Image/level/level_bronze2.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='bronze3'"
              src="@/../public/Image/level/level_bronze3.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='silver1'"
              src="@/../public/Image/level/level_silver1.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='silver2'"
              src="@/../public/Image/level/level_silver2.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='silver3'"
              src="@/../public/Image/level/level_silver3.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='gold1'"
              src="@/../public/Image/level/level_gold1.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='gold2'"
              src="@/../public/Image/level/level_gold2.png"
              id="level-icon"
            />
            <img
              v-else-if="user.userLank==='gold3'"
              src="@/../public/Image/level/level_gold3.png"
              id="level-icon"
            />
          </div>
        </div>
        <div id="user-name"><p>{{user ? user.userNickname + '님' : '로그인이 필요합니다.'}}</p></div>
        <div id="menu-container">
          <div id="menu-dropdown" @click="showMenu = !showMenu">
            <v-icon v-if="!showMenu">fas fa-angle-down</v-icon>
            <v-icon v-if="showMenu">fas fa-angle-up</v-icon>
          </div>
          <ul
            id="menus"
            :class="{ 'menu-inactive': !showMenu, 'menu-active': showMenu }"
          >
            <li><button @click="handleMypage">마이페이지</button></li>
            <li><button @click="handleLogout">로그아웃</button></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Nav",
  data() {
    return {
      showMenu: false,
      menus: [
        { title: "마이페이지" },
        { title: "로그아웃" },
      ],
      user:{},
    };
  },
  created(){
    this.user=this.$store.getters.getUser;
    if(this.user===null){
      window.location.href="https://k4b107.p.ssafy.io:8000/"
    }
  },
  mounted() {
    document.addEventListener(
      "click",
      function (event) {
        if (event.target.closest("#menu-container")) return;
        this.showMenu = false;
      }.bind(this)
    );
  },
  methods: {
    showMenus() {
      this.showMenu = !this.showMenu;
    },
    handleMypage(){
      window.location.href="https://k4b107.p.ssafy.io:8000/mypage"
    },
    handleLogout(){
      this.$store.commit('logout');
      window.location.href="https://k4b107.p.ssafy.io:8000/"
    },
  },
};
</script>

<style scoped src="../../css/Nav.css">
</style>