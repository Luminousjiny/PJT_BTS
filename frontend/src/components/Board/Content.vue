<template>
  <div :id="id" class="content_wrap" @click="onClickContent">
    <div class="content_header"></div>
    <div class="content_title">
      {{content.title}}
    </div>
    <div class="content_footer">
      <div class="content_footer_date">
        {{content.date}}
      </div>
      <div class="content_footer_name">
        {{content.name}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'Content',
  props:{
    content: Object,
    color: String,
    id: Number,
  },
  created(){
  },
  mounted(){
    const wrap = document.getElementById(String(this.id));
    wrap.querySelector('.content_header').style.backgroundColor=this.color;
  },
  methods:{
    onClickContent(){
      this.$router.push({
        name:"ContentDetail",
        params:{
          'content':this.content,
        }
      })
    }
  }
}
</script>

<style>
.content_wrap{
  background-color: white;
  border-radius: 10px;
  width: 22%;
  
  /* max-height: 40%; */
  margin: 0.5em;
  cursor: pointer;
}
.content_header{
  height: 30px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.content_title{
  padding: 1em;
  font-size: var(font-size-22);
  font-family: "AppleSDGothicNeoB";
  font-weight: 400;
}
.content_footer{
  display: flex;
  justify-content: space-between;
  padding: 1em;
}
.content_footer_date{
  font-family: "AppleSDGothicNeoL";
  font-size: var(--font-size-14);
}
.content_footer_name{
  font-family: "AppleSDGothicNeoL";
  font-size: var(--font-size-14);
}
@media screen and (max-width: 1024px) {
  .content_wrap{
    width: 29%;
  }
}
@media screen and (max-width: 800px) {
  .content_wrap{
    width: 40%;
  }
}
@media screen and (max-width: 500px) {
  .content_wrap{
    width: 100%;
  }
}
</style>