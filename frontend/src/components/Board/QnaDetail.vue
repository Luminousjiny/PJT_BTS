<template>
  <div class="qna__wrap">
    <div class="qna__question__wrap">
      <div class="qna__question__header">
        <div class="qna__question__header__left">
          <button class="qna__question__header__listbtn" @click="handleClickList">
            <font-awesome-icon :icon="['fas', 'chevron-left']" size="1x"/>
            글 목록보기
          </button>
        </div>
        <div class="qna__question__header__right">        
          <button class="qna__question__header__editbtn" @click="handleClickEdit">
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

          <button class="qna__question__header__deletebtn" @click="handleClickDelete">
            <font-awesome-icon :icon="['far', 'trash-alt']" size="1x" />
            삭제
          </button>
        </div>
      </div>

      <div class="qna__question__profile">
        <div class="qna__question__profile__image"></div>
        <div>
          <div class="qna__question__profile__name">
            {{content.name}}
          </div>
          <div class="qna__question__profile__date">
            <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
            {{content.date}}
          </div>
        </div>
      </div>
      <div class="qna__question__title">
        {{content.title}}
      </div>
      <div class="qna__question__problem">
        {{content.problem}}
      </div>
      <div class="qna__question__btn__box">
        <button class="modal_btn" @click="handleClickCreate">답변 작성하기</button>
      </div>
    </div>
    <div class="qna__answer__wrap">
      <div class="qna__answer__count">
        답변 2개
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
      content:{
        name:'졍',
        date:'2021.04.21',
        title:'[5663] 피보나치 수열',
        problem:'피보나치 수는 0과 1로 시작',
        input:'3',
        output:'23',
      },
      showModal: false,
      codeList:[
        {
          name:'졍',
          memory: '1024',
          time:'0.153',
          status:'Pass',
          language:'Java',
          date: '2021.04.21',
        },
        {
          name:'졍',
          memory: '1024',
          time:'0.153',
          status:'Pass',
          language:'Java',
          date: '2021.04.21',
        },
        {
          name:'졍',
          memory: '1024',
          time:'0.153',
          status:'Pass',
          language:'Java',
          date: '2021.04.21',
        }
      ]
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
        name:'QnaBoard'
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
    handleClickCreate(){
      this.$router.push({
        name:'CreateCode',
      })
    },
    handleClickCode(){
      this.$router.push({
        name:'CodeDetail',
        params:{
          id:0,
          codeId:0,
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.qna__question{
  &__wrap{
    width: 80%;
    margin: auto;
  }
  &__header{
    display: flex;
    justify-content: space-between;
    padding: 1em 0;
    align-items: center;
  }
  &__header__left &__header__listbtn{
    font-size: var(--font-size-14);
    font-family: "AppleSDGothicNeoR";
    border: 1px solid var(--color-grey-2);
    padding: 0.25rem;
    color: var(--color-grey-2);
  }
  &__header__right &__header__editbtn{
    font-family: "AppleSDGothicNeoB";
    font-size: var(--font-size-14);
    padding: 0.25rem;
    color: var(--color-grey-2);
  }
  &__header__right &__header__deletebtn{
    font-family: "AppleSDGothicNeoB";
    font-size: var(--font-size-14);
    padding: 0.25rem;
    color: var(--color-pink);
  }
  &__profile{
    padding: 1em 0;
    display: flex;
    align-items: center;
  }
  &__profile__image{
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: var(--color-pink);
    display: inline-block;
  }
  &__profile__name{
    padding-left: 1rem;
    font-size: var(--font-size-20);
    font-family: "AppleSDGothicNeoB";
  }
  &__profile__date{
    padding-left: 1rem;
    font-family: "AppleSDGothicNeoB";
    font-size: var(--font-size-14);
    color: var(--color-grey-2);
  }
  &__title{
    padding: 1rem 0;
    font-family: "AppleSDGothicNeoB";
    font-size: var(--font-size-20);
  }
  &__problem{
    font-family: "AppleSDGothicNeoR";
    padding: 1rem 0;
    font-size: var(--font-size-14);
    color: var(--color-grey-2);
    // padding: 1rem 1.5rem;
    // border: 1px solid var(--color-grey-4);
  }
  &__btn__box{
    display: flex;
    justify-content: flex-end;
  }
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
</style>