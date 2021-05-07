<template>
  <div id="calendar">
    <div id="calendar-header">
      <img src="@/../public/Image/calendar_icon.png" id="calendar-icon">
      <p id="calendar-title">calendar</p>
    </div>
    <div id="calendar-nav">
      <div class="router-div active">
        <router-link :to="{name : 'Monthly'}" >
          <v-icon id="monthly-icon">fas fa-th</v-icon>
          <p class="inline-p router-link">Month</p>
        </router-link>
      </div>
      <div class="router-div">
        <router-link :to="{name : 'Weekly'}">
          <v-icon id="weekly-icon">fas fa-tasks</v-icon>
          <p class="inline-p router-link">Week</p>
        </router-link>
      </div>
    </div>
    <div id="calendar-container">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name : "Calendar",


}
</script>

<style scoped>
@import '../../css/style.css';
@import '../../css/Calendar.css';
</style>