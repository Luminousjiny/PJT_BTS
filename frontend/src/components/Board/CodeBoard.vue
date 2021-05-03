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
          <CreateCode/>
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
        />
      </div>
      <button type="button" class="code_borad_list_btn" @click="handleClickRight">
        <font-awesome-icon :icon="['fas', 'chevron-right']" size="2x" />
      </button>
    </div>
  </div>
</template>

<script>
import Content from '../../components/Board/Content.vue';
import CreateCode from '../../components/Board/CreateCode.vue';
export default {
  name:'InfoBoard',
  components:{
    Content,
    CreateCode,
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
          title:'오늘 수업내용',
          content:`
<div data-v-38b2e751="" data-node-view-wrapper="" class="code-block" style="white-space: normal;"><pre data-v-38b2e751=""><code data-v-38b2e751="" data-node-view-content="" style="white-space: pre-wrap;"><span class="hljs-keyword">for</span> i <span class="hljs-keyword">in</span> <span class="hljs-built_in">range</span>(<span class="hljs-number">10</span>):
    <span class="hljs-built_in">print</span>(i);</code></pre></div>
      `,
//           content:`        <p>
//           That’s a boring paragraph followed by a fenced code block:
//         </p>
//         <pre><code class="language-javascript">for (var i=1; i <= 20; i++)
// {
//   if (i % 15 == 0)
//     console.log("FizzBuzz");
//   else if (i % 3 == 0)
//     console.log("Fizz");
//   else if (i % 5 == 0)
//     console.log("Buzz");
//   else
//     console.log(i);
// }</code></pre>
//         <p>
//           Press Command/Ctrl + Enter to leave the fenced code block and continue typing in boring paragraphs.
//         </p>
//       `,      
          // content:`<p>afdsafdsa</p>`,
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

<style scoped>
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

.scrollable-modal {
  display: flex;
  flex-direction: column;
  height: calc(100% - 50px);
}
.scrollable-modal .vm-titlebar {
  flex-shrink: 0;
}
.scrollable-modal .vm-content {
  padding: 0;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  min-height: 0; 
}
.scrollable-modal .vm-content .scrollable-content {
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  max-height: 700px;
  flex-grow: 1;
}
.scrollable-modal .scrollable-modal-footer {
  padding: 15px 0px 15px 0px;
  border-top: 1px solid #e5e5e5;
  margin: auto;
  display: flex;
  justify-content: center;
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
  color: var(--color-white);
}
@media screen and (max-height: 800px) {
  .scrollable-modal .vm-content .scrollable-content{
    max-height: 500px;
  }
}
@media screen and (max-height: 670px) {
  .scrollable-modal .vm-content .scrollable-content{
    max-height: 400px;
  }
}
@media screen and (max-height: 570px) {
  .scrollable-modal .vm-content .scrollable-content{
    max-height: 300px;
  }
}
@media screen and (max-height: 470px) {
  .scrollable-modal .vm-content .scrollable-content{
    max-height: 250px;
  }
}
</style>
