<template>
  <div class="q__wrap">
    <div class="q__profile">
      <div class="q__profile__image"></div>
      <div>
        <div class="q__profile__name">졍이</div>
        <div class="q__profile__date">
          <font-awesome-icon :icon="['far', 'calendar-alt']" size="1x" />
          2021.05.07
        </div>
      </div>
    </div>
    <div class="q__question">
      <div class="q__question__title">
        Q. 중간고사 시험범위 어디까지??
      </div>
      <div class="q__question__content">
        중간고사 시험범위 어디까지냐 ㅅㅂ
      </div>
      <div class="q__question__answer">
        <font-awesome-icon :icon="['far', 'comment-dots']" size="1x" />
        2개
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'Question',
}
</script>

<style lang="scss" scoped>
.q__wrap{
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 80%;
  height: 220px;
  margin: 2rem auto;
  border-radius: 0.5rem;
  padding: 1.5rem;
  border: 1px solid var(--color-grey-8);
  cursor: pointer;
  .q__profile{
    // padding-bottom: 1em;
    display: flex;
    align-items: center;
    &__image{
      width: 30px;
      height: 30px;
      border-radius: 50%;
      background-color: var(--color-pink);
      display: inline-block;
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
  .q__question{
    &__title{
      padding: 0.5rem 0;
      font-family: "AppleSDGothicNeoB";
      font-size: var(--font-size-20);
      color: var(--color-grey-1);
    }
    &__content{
      padding: 0.5rem 0;
      font-family: "AppleSDGothicNeoR";
      font-size: var(--font-size-14);
      color: var(--color-grey-2);
    }
    &__answer{
      padding: 0.5rem 0;
      font-family: "AppleSDGothicNeoB";
      color: var(--color-green);
    }
  }
}

// @media screen and (max-height: 800px) {
//   .scrollable-modal .vm-content .scrollable-content{
//     max-height: 500px;
//   }
// }
// @media screen and (max-height: 670px) {
//   .scrollable-modal .vm-content .scrollable-content{
//     max-height: 400px;
//   }
// }
// @media screen and (max-height: 570px) {
//   .scrollable-modal .vm-content .scrollable-content{
//     max-height: 300px;
//   }
// }
// @media screen and (max-height: 470px) {
//   .scrollable-modal .vm-content .scrollable-content{
//     max-height: 250px;
//   }
// }
</style>