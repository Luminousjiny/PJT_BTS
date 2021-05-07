<template>
  <div class="infodetail__wrap">
    <div class="infodetail__header">
      <div class="infodetail__header__left">
        <button class="infodetail__header__listbtn" @click="handleClickList">
          <font-awesome-icon :icon="['fas', 'chevron-left']" size="1x"/>
          글 목록보기
        </button>
      </div>
      <div class="infodetail__header__right">        
        <button class="infodetail__header__editbtn" @click="handleClickEdit">
          <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x" />
          수정
        </button>

        <Modal
          v-model="showModal"
          title="글 수정"
          modal-class="scrollable-modal"
        >
          <div class="scrollable-content">
            <UpdateEditor :content="content"/>
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

        <button class="infodetail__header__deletebtn" @click="handleClickDelete">
          <font-awesome-icon :icon="['far', 'trash-alt']" size="1x" />
          삭제
        </button>
      </div>
    </div>

    <div class="infodetail__profile">
      <div class="infodetail__profile__image"></div>
      <div>
        <div class="infodetail__profile__name">{{content.name}}</div>
        <div class="infodetail__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          {{content.date}}
        </div>
      </div>
    </div>

    <div class="infodetail__title">
      {{content.title}}
    </div>
    <div class="infodetail__content">
        <div class="editor__content"></div>
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
        name:'InfoBoard'
      })
    },
    handleClickEdit(){
      this.showModal = true;
      const content=this.content.content;
      const title=this.content.title;
      setTimeout(function(){
        const modal = document.querySelector('.scrollable-modal');
        modal.style.maxWidth='80%';
        const titlebar = document.querySelector('.vm-titlebar');
        titlebar.style.textAlign="center";
        titlebar.style.color="var(--color-grey-2)"
        const ProseMirror = document.querySelector('.editor__content .ProseMirror');
        ProseMirror.style.height='360px';
        ProseMirror.innerHTML=content;
        console.log(document.querySelector('.editor__title__input'))
        document.querySelector('.editor__title__input').value=title;
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

<style lang="scss" scoped>
.infodetail__wrap{
  width: 80%;
  margin: auto;
}
.infodetail__header{
  display: flex;
  justify-content: space-between;
  padding: 1em 0;
  align-items: center;
}
.infodetail__header__left .infodetail__header__listbtn{
  font-size: var(--font-size-14);
  font-family: "AppleSDGothicNeoR";
  border: 1px solid var(--color-grey-2);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.infodetail__header__right .infodetail__header__editbtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.infodetail__header__right .infodetail__header__deletebtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-pink);
}
.infodetail__profile{
  padding: 1em 0;
  display: flex;
  align-items: center;
}
.infodetail__profile__image{
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: var(--color-pink);
  display: inline-block;
}
.infodetail__profile__name{
  padding-left: 1rem;
  font-size: var(--font-size-20);
  font-family: "AppleSDGothicNeoB";
}
.infodetail__profile__date{
  padding-left: 1rem;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  color: var(--color-grey-2);
}
.infodetail__title{
  padding: 1rem 0;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-20);
}
.infodetail__content{
  margin: 1rem 0;
  padding: 1rem 1.5rem;
  border: 1px solid var(--color-grey-4);
}
.modal_btn_box{
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal_btn{
  padding: 1rem 3rem;
  border-radius: var(--font-size-12);
  background-color: var(--color-mainBlue);
  font-family: "AppleSDGothicNeoB";
  color: var(--color-white) !important;
}
</style>