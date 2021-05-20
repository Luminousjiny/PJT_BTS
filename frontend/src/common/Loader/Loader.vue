<template>
  <div v-if="isLoading" class="loader__wrap">
    <div class="loader__box">
      <img class="loader__logo" src="@/../public/Image/bts_logo.png" alt="">
    </div>
  </div>
</template>

<script>
export default {
  name:'Loader',
  props:{
    isLoading: Boolean,
  },
  created(){

  },
  mounted(){
    const loader = document.querySelector('.loader__wrap');
    console.log(loader);
    loader.style.width = `${window.innerWidth}px`;
    loader.style.height = `${window.innerHeight}px`;

    console.log(window.innerWidth,window.innerHeight);
  }
}
</script>

<style lang="scss" scoped>
.loader{
  &__wrap{
    position: fixed;
    z-index: 2000;
    background-color: var(--color-grey-2);
    opacity: 0.5;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  &__logo{
    opacity: 1;
    animation: logo 1.5s ease-in Infinite;
  }
  @keyframes logo {
    0% {
      opacity: 1;
    }
    50% {
      opacity: 0.5;
    }
    to {
      opacity: 0;
    }
  }
}
</style>