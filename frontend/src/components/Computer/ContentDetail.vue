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
            <UpdateEditor :content="content.content"/>
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
        <div class="detail__profile__name">{{content.name}}</div>
        <div class="detail__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          {{content.date}}
        </div>
      </div>
    </div>

    <div class="detail__title">
      {{content.title}}
    </div>
    <div class="detail__content">
      <!-- <div class="editor"> -->
        <div class="editor__content"></div>
      <!-- </div> -->
    </div>
    <!-- <div class="sec-widget" data-widget="0a6d29d3367a0672ae82d5a3477e189f"></div> -->
  </div>
</template>

<script>
// import CreateEditor from './CreateEditor.vue';
import UpdateEditor from './UpdateEditor.vue';
// var SEC_HTTPS = true;
// var SEC_BASE = "compilers.widgets.sphere-engine.com"; 
// (function(d, s, id){ SEC = window.SEC || (window.SEC = []);
//   var js, fjs = d.getElementsByTagName(s)[0];
//   if (d.getElementById(id)) return; js = d.createElement(s); js.id = id; 
//   js.src = (SEC_HTTPS ? "https" : "http") + "://" + SEC_BASE + "/static/sdk/sdk.js";
//   fjs.parentNode.insertBefore(js, fjs);   
// }(document, "script", "sphere-engine-compilers-jssdk"));
export default {
  name:'ContentDetail',
  components:{
    // CreateEditor,
    UpdateEditor,
  },
  data(){
    return{
      content:{},
      showModal: false,
    }
  },
  created(){
    this.content=this.$route.params.content;
  },
  mounted(){
    document.querySelector('.editor__content').innerHTML=this.content.content;
  },
  methods:{
    handleClickList(){
      this.$router.push({
        name:'Board'
      })
    },
    handleClickEdit(){
      this.showModal = true;
      const content=this.content.content;
      setTimeout(function(){
        const modal = document.querySelector('.scrollable-modal');
        modal.style.maxWidth='80%';
        modal.style.height='auto';
        const titlebar = document.querySelector('.vm-titlebar');
        titlebar.style.textAlign="center";
        const ProseMirror = document.querySelector('.editor__content .ProseMirror');
        ProseMirror.style.height='auto';
        ProseMirror.style.maxHeight="340px";
        // ProseMirror.append(content);
        ProseMirror.innerHTML=content;
        let idx=0;
        //  auto  —  c  cpp  java  javascript  python  sql 
        const a = document.querySelector('text');
        console.log(a);
      },1);
    },
    handleClickDelete(){
      
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
        'content' : mirror.innerHTML,
        'date' : `${date.getFullYear()}.${date.getMonth()+1}.${date.getDate()}`,
        'name' : '졍',
      };
      this.slides.unshift(data);
      this.showModal=false;
    },    
  }
}
</script>

<style lang="scss">
.detail__wrap{
  width: 80%;
  margin: auto;
  overflow-y: auto;
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
.detail__content{
  margin: 1rem 0;
  padding: 1rem 1.5rem;
  border: 1px solid var(--color-grey-4);
}
.scrollable-modal .scrollable-modal-footer {
  padding: 15px 0px 15px 0px;
  border-top: 1px solid #e5e5e5;
  margin: auto;
  display: flex;
  justify-content: center;
}
.vm-title{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-22) !important;
}
.modal_btn_box{
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal_btn{
  padding: 0.5rem 3rem;
  font-family: "AppleSDGothicNeoB";
  border-radius: var(--font-size-12);
  background-color: var(--color-mainBlue);
  color: var(--color-white) !important;
}
</style>
<style lang="scss">
/* Basic editor styles */
.detail__content {
  > * + * {
    margin-top: 0.75em;
  }

  ul,
  ol {
    padding: 0 1rem;
  }

  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    line-height: 1.1;
  }

  code {
    background-color: rgba(#616161, 0.1);
    color: #616161;
  }
  .code-block {
    select {
      display: none;
    }
  }
  pre {
    background: #0D0D0D;
    color: #FFF;
    font-family: 'JetBrainsMono', monospace;
    padding: 0.75rem 1rem;
    margin: 0.2rem 0;
    border-radius: 0.5rem;

    code {
      color: inherit;
      padding: 0;
      background: none;
      font-size: 0.8rem;
    }
    .hljs-comment,
    .hljs-quote {
      color: #616161;
    }

    .hljs-variable,
    .hljs-template-variable,
    .hljs-attribute,
    .hljs-tag,
    .hljs-name,
    .hljs-regexp,
    .hljs-link,
    .hljs-name,
    .hljs-selector-id,
    .hljs-selector-class {
      color: #F98181;
    }

    .hljs-number,
    .hljs-meta,
    .hljs-built_in,
    .hljs-builtin-name,
    .hljs-literal,
    .hljs-type,
    .hljs-params {
      color: #FBBC88;
    }

    .hljs-string,
    .hljs-symbol,
    .hljs-bullet {
      color: #B9F18D;
    }

    .hljs-title,
    .hljs-section {
      color: #FAF594;
    }

    .hljs-keyword,
    .hljs-selector-tag {
      color: #70CFF8;
    }

    .hljs-emphasis {
      font-style: italic;
    }

    .hljs-strong {
      font-weight: 700;
    }
  }

  mark {
    background-color: #FAF594;
  }

  img {
    max-width: 100%;
    height: auto;
  }

  hr {
    margin: 1rem 0;
  }

  blockquote {
    padding-left: 1rem;
    border-left: 2px solid rgba(#0D0D0D, 0.1);
  }

  hr {
    border: none;
    border-top: 2px solid rgba(#0D0D0D, 0.1);
    margin: 2rem 0;
  }

  ul[data-type="taskList"] {
    list-style: none;
    padding: 0;

    li {
      display: flex;
      align-items: center;

      > label {
        flex: 0 0 auto;
        margin-right: 0.5rem;
      }
    }
  }
}
.detail__content {
  table {
    border-collapse: collapse;
    table-layout: fixed;
    width: 100%;
    margin: 0;
    overflow: hidden;

    td,
    th {
      min-width: 1em;
      border: 2px solid #ced4da;
      padding: 3px 5px;
      vertical-align: top;
      box-sizing: border-box;
      position: relative;

      > * {
        margin-bottom: 0;
      }
    }

    th {
      font-weight: bold;
      text-align: left;
      background-color: #f1f3f5;
    }

    .selectedCell:after {
      z-index: 2;
      position: absolute;
      content: "";
      left: 0; right: 0; top: 0; bottom: 0;
      background: rgba(200, 200, 255, 0.4);
      pointer-events: none;
    }

    .column-resize-handle {
      position: absolute;
      right: -2px;
      top: 0;
      bottom: -2px;
      width: 4px;
      background-color: #adf;
      pointer-events: none;
    }
  }
}

.tableWrapper {
  overflow-x: auto;
}

.resize-cursor {
  cursor: ew-resize;
  cursor: col-resize;
}
</style>