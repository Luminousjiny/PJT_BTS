<template>
  <div class="qna__wrap">
    <div class="qna__header">
      <button class="qna__btn qna__btn__white" @click="handleClickModal">
        <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x"/>
        질문하기
      </button>
      <Modal
        v-model="showModal"
        title="질문 작성"
        modal-class="scrollable-modal"
      >
        <div class="scrollable-content">
          <CreateQuestion/>
        </div>
        <div class="row scrollable-modal-footer">
          <div class="modal_btn_box">
            <button
              class="modal_btn submit"
              type="button"
              @click="handleSubmit"
            >
              글 작성하기
            </button>
          </div>
        </div>
      </Modal>
      <div class="qna__header__search">검색</div>    
    </div>
    <div class="qna__q__box">
      <Question
        v-for="question in qnaList"
        :key="question.qnaId"
        :question="question"
        @handleClickQustion="handleClickQuestion"
      />
    </div>
  </div>
</template>

<script>
import Question from './Question.vue'
import http from '../../util/http-common.js';
import CreateQuestion from '../../components/Board/CreateQuestion.vue';
export default {
  components: { 
    Question,
    CreateQuestion
  },
  name:'QnaBoard',
  data(){
    return{
      qnaList:[],
      showModal: false,
    }
  },
  created(){
    http.get('v1/qna/list')
    .then((res)=>{
      if(res.status===200){
        this.qnaList=res.data.data.reverse();
      }
    })
    .catch((err)=>{
      console.error(err);
    })
  },
  methods:{
    handleClickQuestion(id){
      this.$router.push({
        name:'QnaDetail',
        params:{
          id,
        }
      })
    },
    handleClickModal(){
      this.showModal = true;
    },
    handleSubmit(){
      const qnaTitle=document.querySelector('#question__title').value;
      const qnaContent=document.querySelector('#question__problem').value;
      const data = {
        qnaTitle,
        qnaContent,
        userId: 'jihyeong'
      };
      http.post('v1/qna', JSON.stringify(data))
      .then((res)=>{
        if(res.status===200){
          this.qnaList=res.data.data.reverse();
          this.showModal=false;
        }
      })
      .catch((err)=>{
        console.error(err);
        this.showModal=false;
      })
    },    
  }
}
</script>

<style lang="scss" scoped>
.qna__wrap{
  margin: auto;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  width: 80%;
}
.qna__header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5em;
  
  &__search{
    font-family: "AppleSDGothicNeoSB";
  }
}
.qna__btn{
  font-family: "AppleSDGothicNeoB";
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  border: 2px solid var(--color-mainBlue);
  &__box{
    display: flex;
    justify-content: flex-end;
    margin-bottom: 1rem;
  }
  &__white{
    color: var(--color-mainBlue);
    background-color: var(--color-white);
  }
  &__blue{
    color: var(--color-white);
    background-color: var(--color-mainBlue);
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
  color: var(--color-white) !important;
}
</style>