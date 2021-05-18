<template>
  <div class="code__wrap">
    <div class="code__header">
      <div class="code__header__left">
        <button class="code__header__listbtn" @click="handleClickList">
          <font-awesome-icon :icon="['fas', 'chevron-left']" size="1x"/>
          제출 목록보기
        </button>
      </div>
    </div>

    <div class="code__profile">
      <div class="code__profile__image">
        <img :src="content.user.userImg" alt="" v-if="content.user && content.user.userImg!==''">
        <img src="../../assets/profile.png" alt="" v-else>
      </div>
      <div>
        <div class="code__profile__name">
          {{content.user.userNickname}}
        </div>
        <div class="code__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          {{$moment(content.proDate).format('YYYY-MM-DD')}}
        </div>
      </div>
    </div>
    <div class="code__title">
      {{content.proTitle}}
    </div>
    <div class="code__problem">
      {{content.proContent}}
    </div>

    <div class="code__example">
      <div class="code__example__box">
        <div class="code__example__title">
          예제 입력
        </div>
        <div class="code__example__input">
          <div v-for="(input,idx) in content.proInput.split('\n')" :key="idx">{{input}}</div>
        </div>
      </div>
      <div class="code__example__box">
        <div class="code__example__title">
          예제 출력
        </div>
        <div class="code__example__output" >
          <div v-for="(output,idx) in content.proOutput.split('\n')" :key="idx">{{output}}</div>
        </div>
      </div>
    </div>
    <div class="code__editor__box">
      <select name="code__languages" id="code__languages" v-model="mode" class="code__select">
        <option value="c">C</option>
        <option value="cpp">C++</option>
        <option value="java">JAVA</option>
        <option value="python">PYTHON</option>
      </select>
      <select name="code__languages" id="code__languages" v-model="theme" class="code__select">
        <option value="vs">VS</option>
        <option value="vs-dark">VS-DARK</option>
      </select>
      <m-monaco-editor v-model="code" :mode="mode" :theme="theme" @init="handleInit"></m-monaco-editor>
    </div>
    <div class="code__example">
      <div class="code__example__box">
        <div class="code__example__title">
          입력
        </div>
        <textarea name="" id="" cols="30" rows="10" class="code__example__input" v-model="input"></textarea>
      </div>
      <div class="code__example__box">
        <div class="code__example__title">
          출력
        </div>
        <textarea name="" id="" cols="30" rows="10" class="code__example__output" v-model="output" readonly></textarea>
      </div>
    </div>
    <div class="code__btn__box">
      <button class="code__btn code__btn__white" @click="handleCompile">컴파일</button>
      <button class="code__btn code__btn__blue" @click="handleSubmit">제출하기</button>      
    </div>
  </div>
</template>

<script>
import UpdateProblem from './UpdateProblem.vue';
import http from '@/util/http-common.js';
import Swal from 'sweetalert2'
import Loader from '../../common/Loader/Loader.vue';
export default {
  name:'CreateCode',
  components:{
    UpdateProblem,
    Loader,
    Swal,
  },
  data(){
    return{
      isLoading:true,
      content:{
        "proId": null,
        "user": {
          "userId": "",
          "userPw": "",
          "userNickname": "",
          "userPhone": "",
          "userImg": "",
          "userLank": "",
          "userPoint": null
        },
        "proTitle": "",
        "proContent": "",
        "proInput": "",
        "proOutput": "",
        "proDate": ""
      },
      code:"",
      memory:"",
      time:"",
      result:"",
      input:"",
      output:"",
      mode:"python",
      theme:"vs-dark",
    }
  },
  created(){
    http.get(`v1/pro/detail/${this.$route.params.id}`)
    .then((res)=>{
      if(res.status===200){
        this.content=res.data.data;
        for(let i=0; i<this.content.codeList.length; i++){
          console.log(this.content.codeList[i].codeId,this.$route.params.codeId)
          if(this.content.codeList[i].codeId===Number(this.$route.params.codeId)){
            if(this.$store.getters.getUserId!==this.content.codeList[i].user.userId){
              this.$router.push({
                name: 'CodeDetail',
                params:{
                  id: this.$route.params.id,
                  codeId: this.route.params.codeId,
                }
              })
            }
            this.code = this.content.codeList[i].codeContent;
            this.mode = this.content.codeList[i].codeLan;
            break;
          }
        }
      }
    })
    .catch((err)=>{
      console.error(err);
    })
  },
  computed:{
    wrapClass(){
      if(this.isLoading===false)
        return "code__wrap"
      return "code__wrap darkness"
    }
  },
  mounted(){

  },
  beforeDestroy(){
  },
  methods:{
    handleInit(editor,editorDom,monaco){
      this.editor=editor;
    },
    handleClickList(){
      this.$router.push({
        name:'ProblemDetail',
        params:{
          id:this.content.proId, 
        }
      });
    },
    handleSubmit(){
      const compiler={
        'c':11,
        'cpp':1,
        'java':10,
        'python':116
      };
      this.code=this.editor.getValue();
      var formdata = new FormData();
      formdata.append("source", this.code);
      formdata.append("compilerId", compiler[this.mode]);
      formdata.append("input", this.content.proInput);
      var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
      };
      this.isLoading=true;
      fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
        .then(response => response.json())
        .then(result => {
          const requestOptions = {
            method: 'GET',
            redirect: 'follow'
          };
          setTimeout(()=>{
            fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions/${result.id}?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
              .then(response2 => response2.json())
              .then(result2 => {
                console.log(result2);
                if(result2.result.status.code===15){
                  fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions/${result.id}/output?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
                    .then(response3 => response3.text())
                    .then(result3 => {
                      let output1=result3.replace(/(\r\n\t|\n|\r\t)/gm,"").replace(/(\s*)/g,"");
                      let output2=this.content.proOutput.replace(/(\r\n\t|\n|\r\t)/gm,"").replace(/(\s*)/g,"");
                      this.isLoading=false;
                      if(output1===output2){
                        this.result="성공";
                      } else{
                        this.result="실패";
                      }
                      this.memory = result2.result.memory;
                      this.time = result2.result.time;
                      const data = {
                        codeContent: this.code,
                        codeLan : this.mode,
                        codeMemory: `${this.memory}kB`,
                        codeTime: `${this.time}s`,
                        result: this.result,
                        roomId:1,
                        proId: this.content.proId,
                        userId: 'jihyeong'
                      };                      
                      http.post('v1/code', JSON.stringify(data))
                        .then(res=>{
                          if(res.status===200){
                            this.$router.push({
                              name: 'ProblemDetail',
                              params:{
                                id: this.proId,
                              }
                            })
                          }
                        })
                        .catch(err => {
                          console.error(err);
                        })                     
                    })
                    .catch(error3 => console.log('error', error3));
                } else{
                  switch (result2.result.status.code){
                    case 11:
                      this.result="컴파일에러";
                      break;
                    case 12:
                      this.result="런타임 에러"
                      break;
                    case 13:
                      this.result="시간 초과"
                      break;
                    case 17:
                      this.result="메모리 초과"
                      break;
                    default:
                      this.result="실패"
                  }
                  this.memory = result2.result.memory;
                  this.time = result2.result.time;
                  const data = {
                    codeContent: this.code,
                    codeLan : this.mode,
                    codeMemory: `${this.memory}kB`,
                    codeTime: `${this.time}s`,
                    result: this.result,
                    roomId:1,
                    proId: this.content.proId,
                    userId: 'jihyeong'
                  };
                  http.post('v1/code', JSON.stringify(data))
                    .then(res=>{
                      if(res.status===200){
                        this.$router.push({
                          name: 'ProblemDetail',
                          params:{
                            id: this.content.proId,
                          }
                        })
                      }
                    })
                    .catch(err => {
                      console.error(error);
                    }) 
                }
              })
              .catch(error2 => console.log('error', error2));
          },3000);
        })
        .catch(error => console.log('error', error));
    },
    handleCompile(){
      // 1 c++
      // 11 c
      // 10 java
      // 99 python
      const compiler={
        'c':11,
        'cpp':1,
        'java':10,
        'python':116
      };
      this.code=this.editor.getValue();
      var formdata = new FormData();
      formdata.append("source", this.code);
      formdata.append("compilerId", compiler[this.mode]);
      formdata.append("input", this.input);
      console.log(typeof this.input);
      var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
      };
      fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
        .then(response => response.json())
        .then(result => {
        const requestOptions = {
          method: 'GET',
          redirect: 'follow'
        };
        // 0 waiting
        // 1 compilation
        // 3 execution
        // 11 compilation error
        // 12 runtime error (ex. division zero)
        // 13 time limit exceded
        // 15 success
        // 17 memory limit exceeded
        this.output+='소스 코드를 컴파일 중 입니다...\n'
        setTimeout(()=>{
          fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions/${result.id}?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
            .then(response2 => response2.json())
            .then(result2 => {
              console.log(result2);
              if(result2.result.status.code===15){
                fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions/${result.id}/output?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
                  .then(response3 => response3.text())
                  .then(result3 => {
                    this.output+=`메모리 용량 : ${result2.result.memory}kB, 실행 시간 : ${result2.result.time}s\n`
                    this.output+=`${result3}\n`;
                  })
                  .catch(error3 => console.log('error', error3));
              } else{
                fetch(`https://${process.env.VUE_APP_ENDPOINT}.compilers.sphere-engine.com/api/v4/submissions/${result.id}/error?access_token=${process.env.VUE_APP_SPHERE_API_TOKEN}`, requestOptions)
                  .then(response3 => response3.text())
                  .then(result3 => {
                    this.output+=`error : ${result2.result.status.name}\n`;
                    this.output+=`${result3}\n`;
                  })
                  .catch(error3 => console.error('error', error3));
              }

                })
            .catch(error2 => console.error('error', error2));
        },3000);
        })
        .catch(error => console.error('error', error));
    },
  }
}
</script>
<style lang="scss">
.monaco-editor,
.overflow-guard{
  width: 100% !important;
}
// .minimap{
//   width:50% !important;
//   canvas{
//     width:50% !important;
//   }
//   &-slider{
//     width: 50% !important;
//     &-horizontal{
//       width: 50% !important;
//     }
//   }
// }
</style>
<style lang="scss" scoped>
.code__wrap{
  width: 80%;
  margin: auto;
}
.code__header{
  display: flex;
  justify-content: space-between;
  padding: 1em 0;
  align-items: center;
}
.code__header__left .code__header__listbtn{
  font-size: var(--font-size-14);
  font-family: "AppleSDGothicNeoR";
  border: 1px solid var(--color-grey-2);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.code__header__right .code__header__editbtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-grey-2);
}
.code__header__right .code__header__deletebtn{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.25rem;
  color: var(--color-pink);
}
.code__profile{
  padding: 1em 0;
  display: flex;
  align-items: center;
  
  &__image{
      width: 40px;
      height: 40px;
      border-radius: 50%;
      display: inline-block;
  }
  img{
    width: 40px;
    height: 40px;
  }
  &__name{
    padding-left: 1rem;
    font-size: var(--font-size-20);
    font-family: "AppleSDGothicNeoB";
  }
  &__date{
    padding-left: 1rem;
    font-family: "AppleSDGothicNeoB";
    font-size: var(--font-size-14);
    color: var(--color-grey-2);
  }
}
.code__title{
  padding: 1rem 0;
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-20);
}
.code__problem{
  font-family: "AppleSDGothicNeoB";
  padding: 1rem 0;
  // padding: 1rem 1.5rem;
  // border: 1px solid var(--color-grey-4);
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
.code__example{
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
    width: 100%;
    background-color: var(--color-grey-7);
    border: 1px solid var(--color-grey-8);
    div{
      padding: 0.25rem 0;
    }
  }
}
.code__editor{
  &__box{
    padding: 0.5rem;
    border: 1px solid var(--color-grey-8);
  }
}
.m-monaco-editor div{
  width: 100% !important;
}
.code__btn{
  font-family: "AppleSDGothicNeoB";
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  border: 2px solid var(--color-mainBlue);
  margin-left: 1rem;
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
.code__select{
  font-family: "AppleSDGothicNeoB";
  font-size: var(--font-size-14);
  padding: 0.5rem 1rem;
}
.code__block{
  width: 100%;
}
</style>