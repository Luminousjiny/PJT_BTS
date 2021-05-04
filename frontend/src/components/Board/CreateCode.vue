<template>
  <div class="code__wrap">
    <div class="code__header">
      <div class="code__header__left">
        <button class="code__header__listbtn" @click="handleClickList">
          <font-awesome-icon :icon="['fas', 'chevron-left']" size="1x"/>
          제출 목록보기
        </button>
      </div>
    </div>

    <div class="code__profile">
      <div class="code__profile__image"></div>
      <div>
        <div class="code__profile__name">
          {{content.name}}
        </div>
        <div class="code__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          {{content.date}}
        </div>
      </div>
    </div>
    <div class="code__title">
      {{content.title}}
    </div>
    <div class="code__problem">
      <!-- {{content.problem}} -->
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Repudiandae nostrum veniam, voluptatum architecto iusto recusandae? Magni sequi beatae quibusdam provident, suscipit porro rem commodi blanditiis nisi sed quaerat facere repudiandae.
    </div>

    <div class="code__example">
      <div class="code__example__box">
        <div class="code__example__title">
          예제 입력
        </div>
        <div class="code__example__input">
          {{content.input}}
        </div>
      </div>
      <div class="code__example__box">
        <div class="code__example__title">
          예제 출력
        </div>
        <div class="code__example__output">
          {{content.output}}
        </div>
      </div>
    </div>
    <div class="code__box">
      <!-- <div class="sec-widget" data-widget="90361833136e3cda71eda426db59f7ff"></div> -->
    </div>
    
  </div>
</template>

<script>
const SEC_HTTPS = true;
const SEC_BASE = "compilers.widgets.sphere-engine.com"; 
(function(d, s, id){ SEC = window.SEC || (window.SEC = []);
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return; js = d.createElement(s); js.id = id; 
  js.src = (SEC_HTTPS ? "https" : "http") + "://" + SEC_BASE + "/static/sdk/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);   
}(document, "script", "sphere-engine-compilers-jssdk"));
import UpdateProblem from './UpdateProblem.vue';
export default {
  name:'CreateCode',
  components:{
    UpdateProblem,
  },
  data(){
    return{
      content:{},
    }
  },
  created(){
    // this.content=this.$route.params.content;
    // location.reload();
  },
  mounted(){
    // document.querySelector('.editor__content').innerHTML=this.content.problem;
  },
  methods:{
    handleClickList(){
      this.$router.push({
        name:'ProblemDetail',
        params:{
          id:0,
        }
      });
    },
    handleSubmit(){
    },
  }
}
</script>
<style lang="scss" scoped>
.code__wrap{
  width: 80%;
  margin: auto;
}
.code__header{
  display: flex;
  justify-content: space-between;
  padding: 1em 0;
  align-items: center;
}
.code__header__left .code__header__listbtn{
  font-size: var(--font-size-14);
  font-family: "AppleSDGothicNeoR";
  border: 1px solid var(--color-grey-2);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.code__header__right .code__header__editbtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.code__header__right .code__header__deletebtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-pink);
}
.code__profile{
  padding: 1em 0;
  display: flex;
  align-items: center;
}
.code__profile__image{
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: var(--color-pink);
  display: inline-block;
}
.code__profile__name{
  padding-left: 1rem;
  font-size: var(--font-size-20);
  font-family: "AppleSDGothicNeoB";
}
.code__profile__date{
  padding-left: 1rem;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  color: var(--color-grey-2);
}
.code__title{
  padding: 1rem 0;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-20);
}
.code__problem{
  font-family: "AppleSDGothicNeoB";
  padding: 1rem 0;
  // padding: 1rem 1.5rem;
  // border: 1px solid var(--color-grey-4);
}
.modal_btn_box{
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal_btn{
  padding: 0.5rem 3rem;
  border-radius: var(--font-size-12);
  background-color: var(--color-mainBlue);
  font-family: "AppleSDGothicNeoB";
  color: var(--color-white);
}
.code__example{
  padding: 2rem 0;
  display: flex;
  justify-content: space-between;
  font-family: "AppleSDGothicNeoB";
  &__box{
    width: 48%;
  }
  &__title{
    padding: 1rem 0;
    font-size: var(--font-size-18);
  }
  &__input, &__output{
    padding: 1rem;
    background-color: var(--color-grey-7);
    border: 1px solid var(--color-grey-8);
  }
}
.code__box{
  width: 100%;
  height: 500px;
  margin-bottom: 200px;
}
</style>