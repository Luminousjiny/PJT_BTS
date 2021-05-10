<template>
  <div id="calendar">
    <div id="calendar-header">
      <img src="@/../public/Image/calendar_icon.png" id="calendar-icon">
      <p id="calendar-title">calendar</p>
    </div>
    <div id="calendar-nav">
      <router-link :to="{name : 'Weekly'}">
        <div :class="{'router-div' : true}">
          <v-icon id="weekly-icon">fas fa-tasks</v-icon>
          <p class="inline-p router-link">Week</p>
        </div>
      </router-link>
      <router-link :to="{name : 'Monthly'}" >
        <div :class="{'router-div' : true}">
          <v-icon id="monthly-icon">fas fa-th</v-icon>
          <p class="inline-p router-link">Month</p>
        </div>
      </router-link>
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

<style scoped src="../../css/Calendar.css">
</style>