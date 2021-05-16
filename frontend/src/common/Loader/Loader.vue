<template>
  <div v-if="isLoading" class="loader__wrap">
    <img class="loader__logo" src="@/../public/Image/bts_logo.png" alt="">
    <div class="loader__text">코드를 채점 중 입니다.</div>
  </div>
</template>

<script>
export default {
  name:'Loader',

  props:{
    isLoading: Boolean,
  }
}
</script>

<style lang="scss" scoped>
.loader{
  &__wrap{
    z-index: 1000;
    width:100vw;
    height:100vh;
    filter: brightness(0.5);
  }
}
</style>