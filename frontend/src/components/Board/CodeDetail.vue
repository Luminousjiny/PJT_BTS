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

    <div class="code__editor__box">
      <select name="code__languages" id="code__languages" v-model="mode" class="code__select" disabled>
        <option value="c">C</option>
        <option value="cpp">C++</option>
        <option value="java">JAVA</option>
        <option value="python">PYTHON</option>
      </select>
      <select name="code__languages" id="code__languages" v-model="theme" class="code__select" disabled>
        <option value="vs">VS</option>
        <option value="vs-dark">VS-DARK</option>
      </select>
      <m-monaco-editor v-model="code" :mode="mode" :theme="theme" readOnly="true"></m-monaco-editor>
    </div>
    <div class="code__btn__box">
      <button class="code__btn code__btn__blue" @click="handleUpdate">수정하기</button>      
    </div>
  </div>
</template>

<script>
import UpdateProblem from './UpdateProblem.vue';
import http from '@/util/http-common.js';
export default {
  name:'CreateCode',
  components:{
    UpdateProblem,
  },
  data(){
    return{
      content:{
        name:'졍',
        date:'2021.04.21',
        title:'[5663] 피보나치 수열',
        problem:'피보나치 수는 0과 1로 시작',
        input:'3',
        output:'23\n24\n25',
      },
      code:"",
      mode:"python",
      theme:'vs-dark',
      input:'',
      output:'',
    }
  },
  created(){
  },
  mounted(){
  },
  methods:{
    handleClickUpdate(){
      this.$route.name({
        name:'UpdateCode',
        params:{
          id:0,
          codeId:0,
          userName:'a'
        }
      })
    }
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
.modal_btn_box{
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal_btn{
  padding: 1rem 2rem;
  border-radius: var(--font-size-12);
  background-color: var(--color-mainBlue);
  font-family: "AppleSDGothicNeoB";
  color: var(--color-white);
}
.code__editor{
  &__box{
    padding: 0.5rem;
    border: 1px solid var(--color-grey-8);
    margin: 1rem 0;
  }
}
.code__btn{
  font-family: "AppleSDGothicNeoB";
  padding: 1rem 3rem;
  border-radius: 0.25rem;
  border: 2px solid var(--color-mainBlue);
  margin-left: 1rem;
  &__box{
    display: flex;
    justify-content: flex-end;
    margin-bottom: 1rem;
  }
  &__blue{
    color: var(--color-white);
    background-color: var(--color-mainBlue);
  }
}
.code__select{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.5rem 1rem;
}
.code__block{
  width: 100%;
}
</style>