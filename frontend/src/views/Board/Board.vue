<template>
  <div class="board__wrap">
    <Nav/>
    <div class="board__link__box">
      <router-link id="information" class="board__link" :to="{name:'InfoBoard'}">정보공유</router-link>
      <span class="board__link__divider">|</span>
      <router-link id="code" class="board__link" :to="{name:'CodeBoard'}">코드공유</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
import Nav from '../../common/Nav/Nav.vue'
export default {
  components: { Nav },
  name:'Board',
  created(){
    if(this.$route.name==="Board"){
      this.$router.push({
        name:'InfoBoard'
      })
    }
  }
}
</script>

<style>
.board__wrap{

}
.board__link__box{
  text-align: center;
  padding-top: 2rem;
}
.board__link{
  font-size: var(--font-size-22);
  font-family: "AppleSDGothicNeoEB";
  text-align: center;
  padding: 0.5em;
  color: var(--color-grey-3);
  text-decoration: none;
}
.board__link__divider{
  font-size: var(--font-size-22);
  font-family: "AppleSDGothicNeoEB";
  color: var(--color-black);
}
.router-link-active{
  color: var(--color-mainBlue);
}
</style>