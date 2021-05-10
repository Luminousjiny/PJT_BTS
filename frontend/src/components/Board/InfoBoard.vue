<template>
  <div class="qna_board_wrap">
    <div class="qna_board_title">
      수업을 들으며 학습한 내용을 올려보세요
    </div>
    <div class="qna_board_nav">
      <button class="qna_board_create_btn" @click="handleClickModal">
        <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x" />
        글작성
      </button>
      <Modal
        v-model="showModal"
        title="글 작성"
        modal-class="scrollable-modal"
      >
        <div class="scrollable-content">
          <CreateEditor/>
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
      <div class="qna_board_search">검색</div>
    </div>
    <div class="qna_board_list">
      <button type="button" class="qna_borad_list_btn" @click="handleClickLeft">
        <font-awesome-icon :icon="['fas', 'chevron-left']" size="2x" />
      </button>
      <div class="qna_board_contents">
        <Content 
          v-for="(info,idx) in infoList.slice(infoIdx*showCnt,infoIdx*showCnt+showCnt)"
          :id="info.infoId"
          :key="info.infoId"
          :content="info"
          @handleClickContent="handleClickContent"
        />
      </div>
      <button type="button" class="qna_borad_list_btn" @click="handleClickRight">
        <font-awesome-icon :icon="['fas', 'chevron-right']" size="2x" />
      </button>
    </div>
  </div>
</template>

<script>
import Content from '../../components/Board/Content.vue';
import CreateEditor from '../../components/Board/CreateEditor.vue';
import http from '../../util/http-common.js';
export default {
  name:'InfoBoard',
  components:{
    Content,
    CreateEditor,
  },
  data : ()=>{
    return{
      windowSize:0,
      showModal: false,
      infoIdx:0,
      showCnt:0,
      infoList: [],
    }
  },
  created(){
    this.windowSize=window.innerWidth;
    if(this.windowSize>1024)
      this.showCnt=8;
    else if(this.windowSize>800)
      this.showCnt=6;
    else if(this.windowSize>500)
      this.showCnt=4;
    else
      this.showCnt=2;
    window.addEventListener('resize',this.handleWindowSize);
    http.get('v1/info/list')
    .then((res)=>{
      if(res.status===200){
        this.infoList=res.data.data.reverse();
      } else{
        console.error(res.data);
      }
    })
    .catch((err)=>{
      console.error(err);
    })
  },
  beforeDestroy(){
    window.removeEventListener('resize',this.handleWindowSize);
  },
  mounted(){
  },
  methods:{
    handleClickContent(id){
      this.$router.push({
        name:"InfoDetail",
        params:{
          id,
        }
      })
    },
    handleWindowSize(){
      this.windowSize=window.innerWidth;
      if(this.windowSize>1024)
        this.showCnt=8;
      else if(this.windowSize>800)
        this.showCnt=6;
      else if(this.windowSize>500)
        this.showCnt=4;
      else
        this.showCnt=2;
      while(this.infoIdx*this.showCnt>=this.infoList.length)
        this.infoIdx--;
    },
    handleClickModal(){
      this.showModal = true;
    },
    handleClickLeft() {
      if((this.infoIdx-1)*this.showCnt < 0) return;
      this.infoIdx--;
    },
    handleClickRight() {
      if((this.infoIdx+1)*this.showCnt >=this.infoList.length) return;
      this.infoIdx++;
    },
    handleSubmit(){
      const mirror = document.querySelector('.ProseMirror');
      let html = mirror.innerHTML;
      const input = document.querySelector('.editor__title__input');      
      const data = {
        infoTitle : input.value,
        infoContent : html,
        userId : 'jihyeong',
      };
      http.post('v1/info', JSON.stringify(data))
      .then((res)=>{
        console.log(res);
        if(res.status===200){
          this.infoList=res.data.data.reverse();
          this.showModal=false;
        }
      })
      .catch((err)=>{
        console.error(err);
        this.showModal=false;
      })
    },
  },
};
</script>

<style lang="scss" scoped>
.qna_board_wrap{
  margin: auto;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  width: 80%;
}
.qna_board_title{
  font-size: var(--font-size-14);
  color: var(--color-grey-3);
  font-family: "AppleSDGothicNeoR";
  text-align: center;
  padding: 0.5em;
}
.qna_board_nav{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5em;
}
.qna_board_create_btn{
  color: var(--color-mainBlue);
  background-color: var(--color-white);
  border: 2px solid var(--color-mainBlue);
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  font-family: "AppleSDGothicNeoSB";
}
.qna_board_search{
  font-family: "AppleSDGothicNeoSB";
}
.qna_board_list{
  background-color: #333333;
  border: 10px solid #9E5E3F;
  width: 100%;
  min-height: 400px;
  padding: 1.5em 2em;
  display: flex;
  justify-content: center;
  align-items: center;
}
.qna_board_contents{
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}
.content_list{

}
.qna_borad_list_btn{
  color: var(--color-white);
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