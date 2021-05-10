<template>
    <div id="month">
        <div id="monthly-header">
            <div id="month-prev" class="calendar-button"><v-icon>fas fa-angle-left</v-icon></div>
            <div id="month-title" class="inline-p">
                <p>ABCDEFG</p>
                <p>0000</p>
            </div>
            <div id="month-next" class="calendar-button"><v-icon>fas fa-angle-right</v-icon></div>
        </div>
        <v-sheet>
            <v-calendar ref="calendar" 
                v-model="value" 
                type="month" 
                :events="events"
            ></v-calendar>
        </v-sheet>
    </div>
</template>

<script>
export default {
    name : "Month",
    data() {
        return {
            value : '',
        }
    },

}
</script>

<style scoped>
@import '../../css/Calendar.css';
</style>