<template>
  <div class="qna__wrap">
    <div class="qna__header">
      <button class="qna__btn qna__btn__white">
        <font-awesome-icon :icon="['fas', 'pencil-alt']" size="1x" />
        질문하기
      </button>
    </div>
    <div class="qna__q__box">
      <Question/>
    </div>
  </div>
</template>

<script>
import Question from './Question.vue'
export default {
  components: { Question },
  name:'QnaBoard',
}
</script>

<style lang="scss" scoped>
.qna__wrap{
  margin: auto;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  width: 80%;
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
</style>