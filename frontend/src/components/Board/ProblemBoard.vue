<template>
  <div class="code_board_wrap">
    <div class="code_board_title">
      풀리지 않는 문제가 있다면 친구들과 반례를 공유해보세요
    </div>
    <div class="code_board_nav">
      <button class="code_board_create_btn" @click="handleClickModal">
        <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x" />
        문제 올리기
      </button>
      <Modal
        v-model="showModal"
        title="문제 작성"
        modal-class="scrollable-modal"
      >
        <div class="scrollable-content">
          <CreateProblem/>
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
      <div class="code_board_search">검색</div>
    </div>
    <div class="code_board_list">
      <button type="button" class="code_borad_list_btn" @click="handleClickLeft">
        <font-awesome-icon :icon="['fas', 'chevron-left']" size="2x" />
      </button>
      <div class="code_board_contents">
        <Content 
          v-for="(content,idx) in slides.slice(slideIdx*showCnt,slideIdx*showCnt+showCnt)"
          :id="idx"
          :key="idx"
          :color="colorList[idx%7]"
          :content="content"
          @handleClickContent="handleClickContent"
        />
      </div>
      <button type="button" class="code_borad_list_btn" @click="handleClickRight">
        <font-awesome-icon :icon="['fas', 'chevron-right']" size="2x" />
      </button>
    </div>
  </div>
</template>

<script>
import Content from './Content.vue';
import CreateProblem from './CreateProblem.vue';
export default {
  name:'ProblemBoard',
  components:{
    Content,
    CreateProblem,
  },
  data : ()=>{
    return{
      windowSize:0,
      showModal: false,
      slideIdx:0,
      showCnt:0,
      colorList:['#FE9C9B','#FCB849','#69F5CE','#7A89FF','#60BDFF','#D06BF7','#F36B9D'],
      slides: [
        {
          name:'졍',
          title:'[5663] 피보나치 수열',
          problem: '피보나치 수는~~~',
          input: '3',
          output: '3',
          date: '2021.04.29'
        }
      ],
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
  },
  beforeDestroy(){
    window.removeEventListener('resize',this.handleWindowSize);
  },
  mounted(){
  },
  methods:{
    handleClickContent(id,content){
      this.$router.push({
        name:"ProblemDetail",
        params:{
          id,
          content,
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
      while(this.slideIdx*this.showCnt>=this.slides.length)
        this.slideIdx--;
    },
    handleClickModal(){
      this.showModal = true;
      setTimeout(function(){
        const modal = document.querySelector('.scrollable-modal');
        modal.style.maxWidth='80%';
        modal.style.height='auto';
        const titlebar = document.querySelector('.vm-titlebar');
        titlebar.style.textAlign="center";
        titlebar.style.color="var(--color-grey-2)"
      },1);

    },
    handleClickLeft() {
      if((this.slideIdx-1)*this.showCnt < 0) return;
      this.slideIdx--;
    },
    handleClickRight() {
      if((this.slideIdx+1)*this.showCnt >=this.slides.length) return;
      this.slideIdx++;
    },
    handleSubmit(){
      const title=document.querySelector('#code__title').value;
      const problem=document.querySelector('#code__problem').value;
      const input=document.querySelector('#code__input').value;
      const output=document.querySelector('#code__output').value;
      const date = new Date();
      const data = {
        title,
        problem,
        input,
        output,
        'date' : `${date.getFullYear()}.${date.getMonth()+1}.${date.getDate()}`,
        'name' : '졍',
      };
      this.slides.unshift(data);
      this.showModal=false;
    },
  },
};
</script>

<style lang="scss" scoped>
.code_board_wrap{
  margin: auto;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  width: 80%;
}
.code_board_title{
  font-size: var(--font-size-14);
  color: var(--color-grey-3);
  font-family: "AppleSDGothicNeoR";
  text-align: center;
  padding: 0.5em;
}
.code_board_nav{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5em;
}
.code_board_create_btn{
  color: var(--color-mainBlue);
  border: 1px solid var(--color-mainBlue);
  padding: 0.25em;
  border-radius: 10px;
  font-family: "AppleSDGothicNeoSB";
}
.code_board_search{
  font-family: "AppleSDGothicNeoSB";
}
.code_board_list{
  background-color: #333333;
  border: 10px solid #9E5E3F;
  width: 100%;
  min-height: 400px;
  padding: 1.5em 2em;
  display: flex;
  justify-content: center;
  align-items: center;
}
.code_board_contents{
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}
.content_list{

}
.code_borad_list_btn{
  color: var(--color-white);
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
</style>
