<template>
  <div class="detail__wrap">
    <div class="detail__header">
      <div class="detail__header__left">
        <button class="detail__header__listbtn" @click="handleClickList">
          <font-awesome-icon :icon="['fas', 'chevron-left']" size="1x"/>
          글 목록보기
        </button>
      </div>
      <div class="detail__header__right">        
        <button class="detail__header__editbtn" @click="handleClickEdit">
          <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x" />
          수정
        </button>
        <Modal
          v-model="showModal"
          title="글 수정"
          modal-class="scrollable-modal"
        >
          <div class="scrollable-content">
            <UpdateProblem :content="content"/>
          </div>
          <div class="row scrollable-modal-footer">
            <div class="modal_btn_box">
              <button
                class="modal_btn"
                type="button"
                @click="handleSubmit"
              >
                글 수정하기
              </button>            
            </div>
          </div>
        </Modal>

        <button class="detail__header__deletebtn" @click="handleClickDelete">
          <font-awesome-icon :icon="['far', 'trash-alt']" size="1x" />
          삭제
        </button>
      </div>
    </div>

    <div class="detail__profile">
      <div class="detail__profile__image"></div>
      <div>
        <div class="detail__profile__name">
          {{content.name}}
        </div>
        <div class="detail__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          {{content.date}}
        </div>
      </div>
    </div>
    <div class="detail__title">
      {{content.title}}
    </div>
    <div class="detail__problem">
      {{content.problem}}
    </div>
    <div class="detail__btn__box">
      <button class="modal_btn" @click="handleClickCode">코드 작성하기</button>
    </div>
    <div class="detail__example">
      <div class="detail__example__box">
        <div class="detail__example__title">
          예제 입력
        </div>
        <div class="detail__example__input">
          {{content.input}}
        </div>
      </div>
      <div class="detail__example__box">
      <div class="detail__example__title">
          예제 출력
        </div>
        <div class="detail__example__output">
          {{content.output}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UpdateProblem from './UpdateProblem.vue';

export default {
  name:'ProblemDetail',
  components:{
    UpdateProblem,
  },
  data(){
    return{
      content:{},
      showModal: false,
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
        name:'ProblemBoard'
      });
    },
    handleClickEdit(){
      this.showModal = true;
      setTimeout(function(){
        const modal = document.querySelector('.scrollable-modal');
        modal.style.maxWidth='80%';
        modal.style.height='auto';
      },1);
    },
    handleClickDelete(){
      
    },
    handleSubmit(){
      // const title=document.querySelector('#code__title').value;
      // const problem=document.querySelector('#code__problem').value;
      // const input=document.querySelector('#code__input').value;
      // const output=document.querySelector('#code__output').value;
      // const date = new Date();
      // const data = {
      //   title,
      //   problem,
      //   input,
      //   output,
      //   'date' : `${date.getFullYear()}.${date.getMonth()+1}.${date.getDate()}`,
      //   'name' : '졍',
      // }
      // this.content=data;
      this.showModal=false;
    },
    handleClickCode(){
      location.href="http://localhost:8000/board/problem/0/code";
    },
  }
}
</script>

<style lang="scss" scoped>
.detail__wrap{
  width: 80%;
  margin: auto;
}
.detail__header{
  display: flex;
  justify-content: space-between;
  padding: 1em 0;
  align-items: center;
}
.detail__header__left .detail__header__listbtn{
  font-size: var(--font-size-14);
  font-family: "AppleSDGothicNeoR";
  border: 1px solid var(--color-grey-2);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.detail__header__right .detail__header__editbtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.detail__header__right .detail__header__deletebtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-pink);
}
.detail__profile{
  padding: 1em 0;
  display: flex;
  align-items: center;
}
.detail__profile__image{
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: var(--color-pink);
  display: inline-block;
}
.detail__profile__name{
  padding-left: 1rem;
  font-size: var(--font-size-20);
  font-family: "AppleSDGothicNeoB";
}
.detail__profile__date{
  padding-left: 1rem;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  color: var(--color-grey-2);
}
.detail__title{
  padding: 1rem 0;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-20);
}
.detail__problem{
  font-family: "AppleSDGothicNeoB";
  padding: 1rem 0;
  // padding: 1rem 1.5rem;
  // border: 1px solid var(--color-grey-4);
}
.detail__btn__box{
  display: flex;
  justify-content: flex-end;
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
.detail__example{
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
</style>