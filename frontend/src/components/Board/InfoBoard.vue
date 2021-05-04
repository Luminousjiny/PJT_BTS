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
          v-for="(content,idx) in slides.slice(slideIdx*showCnt,slideIdx*showCnt+showCnt)"
          :id="idx"
          :key="idx"
          :color="colorList[idx%7]"
          :content="content"
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
export default {
  name:'InfoBoard',
  components:{
    Content,
    CreateEditor,
  },
  data : ()=>{
    return{
      routeName:'information',
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
    handleClickContent(id,content){
      this.$router.push({
        name:"InfoDetail",
        params:{
          content,
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
      while(this.slideIdx*this.showCnt>=this.slides.length)
        this.slideIdx--;
    },
    handleClickModal(){
      this.showModal = true;
      setTimeout(function(){
        const modal = document.querySelector('.scrollable-modal');
        modal.style.maxWidth='80%';
        const titlebar = document.querySelector('.vm-titlebar');
        titlebar.style.textAlign="center";
        titlebar.style.color="var(--color-grey-2)"
        const ProseMirror = document.querySelector('.ProseMirror');
        ProseMirror.style.height='360px';
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
      const mirror = document.querySelector('.ProseMirror');
      let html = mirror.innerHTML;
      while(true){
        const index=html.indexOf('<select data-v-38b2e751="" contenteditable="false"><option data-v-38b2e751="" value=""><p data-v-38b2e751="">auto</p></option><option data-v-38b2e751="" disabled="disabled"><p data-v-38b2e751=""> — </p></option><option data-v-38b2e751="" value="c"><p data-v-38b2e751="">c</p></option><option data-v-38b2e751="" value="cpp"><p data-v-38b2e751="">cpp</p></option><option data-v-38b2e751="" value="java"><p data-v-38b2e751="">java</p></option><option data-v-38b2e751="" value="javascript"><p data-v-38b2e751="">javascript</p></option><option data-v-38b2e751="" value="python"><p data-v-38b2e751="">python</p></option><option data-v-38b2e751="" value="sql"><p data-v-38b2e751="">sql</p></option></select>')
        if(index===-1) break;
        const length = '<select data-v-38b2e751="" contenteditable="false"><option data-v-38b2e751="" value=""><p data-v-38b2e751="">auto</p></option><option data-v-38b2e751="" disabled="disabled"><p data-v-38b2e751=""> — </p></option><option data-v-38b2e751="" value="c"><p data-v-38b2e751="">c</p></option><option data-v-38b2e751="" value="cpp"><p data-v-38b2e751="">cpp</p></option><option data-v-38b2e751="" value="java"><p data-v-38b2e751="">java</p></option><option data-v-38b2e751="" value="javascript"><p data-v-38b2e751="">javascript</p></option><option data-v-38b2e751="" value="python"><p data-v-38b2e751="">python</p></option><option data-v-38b2e751="" value="sql"><p data-v-38b2e751="">sql</p></option></select>'.length;
        html.replaceAll('<select data-v-38b2e751="" contenteditable="false"><option data-v-38b2e751="" value=""><p data-v-38b2e751="">auto</p></option><option data-v-38b2e751="" disabled="disabled"><p data-v-38b2e751=""> — </p></option><option data-v-38b2e751="" value="c"><p data-v-38b2e751="">c</p></option><option data-v-38b2e751="" value="cpp"><p data-v-38b2e751="">cpp</p></option><option data-v-38b2e751="" value="java"><p data-v-38b2e751="">java</p></option><option data-v-38b2e751="" value="javascript"><p data-v-38b2e751="">javascript</p></option><option data-v-38b2e751="" value="python"><p data-v-38b2e751="">python</p></option><option data-v-38b2e751="" value="sql"><p data-v-38b2e751="">sql</p></option></>','')
        html = html.slice(0,index)+html.slice(index+length);
      }
      const input = document.querySelector('.editor__title__input');      
      const date = new Date();
      const data = {
        'title' : input.value,
        'content' : html,
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
  border: 1px solid var(--color-mainBlue);
  padding: 0.25em;
  border-radius: 10px;
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
  padding: 0.5rem 3rem;
  border-radius: var(--font-size-12);
  background-color: var(--color-mainBlue);
  font-family: "AppleSDGothicNeoB";
  color: var(--color-white) !important;
}
</style>