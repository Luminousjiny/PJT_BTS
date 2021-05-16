<template>
    <div id="weekly">
        <v-app id="inspire">
            <v-row class="fill-height">
            <v-col>
                <v-sheet :style="{height : '64px'}">
                <div class="calendar-header">
                    <div id="weekly-prev" class="calendar-button" @click="$refs.calendar.prev()"><v-icon>fas fa-angle-left</v-icon></div>
                    <div class="calendar-title inline-p">
                        <v-toolbar-title v-if="$refs.calendar">{{ $refs.calendar.title }}</v-toolbar-title>
                    </div>
                    <div id="weekly-next" class="calendar-button" @click="$refs.calendar.next()"><v-icon>fas fa-angle-right</v-icon></div>
                </div>
                </v-sheet>
                <v-sheet height="800">
                <v-calendar ref="calendar" v-model="start" :now="today.str" :end="end" color="var(--color-midBlue)" type="week" @change="getEventList">
                <template v-slot:day-body="{ date, week }">
                    <div
                        class="v-current-time"
                        :class="{ first: date === week[0].date }"
                        :style="{ top: nowY }"
                    ></div>
                </template>
                </v-calendar>
                </v-sheet>
            </v-col>
            </v-row>
        </v-app>
    </div>
</template>

<script>
import http from '@/util/http-common.js';
export default {
    name : "Weekly",
    data() {
        return {
            start : '',
            end : '',
            events : [],
            today : {
                date : undefined,
                str : ''
            },
        }
    },
    created() {
        const date = new Date();
        this.today.date = date;
        this.today.str = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    },
    mounted() {
        this.$refs.calendar.checkChange()
    },
    methods: {
        getEventList({start, end}){
            const weekYear = start.year;
            const weekMonth = start.month;
            const startDate = start.day;
            const endDate = end.day;
            if(start.month < end.month){
                
            }
            http.get(`/v1/weekly/${this.userId}/${weekYear}/${weekMonth}/${startDate}/${endDate}`).then(({data})=>{
                this.eventList = data.data;
                console.log(this.eventList);
                const events = [];
                const eventCount = this.eventList.length;
                for (let i = 0; i < eventCount; i++) {
                    const date = this.eventList[i].weekYear+'-'+this.eventList[i].weekMonth+'-'+this.eventList[i].weekDate;
                    const first = date+' '+this.eventList[i].weekStartTime;
                    const second = date+' '+this.eventList[i].weekEndTime;
                    events.push({
                        name: this.eventList[i].weekContent,
                        start: first,
                        end: second,
                        id : this.eventList[i].weekId,
                        startTime : this.eventList[i].weekStartTime,
                        endTime : this.eventList[i].weekEndTime,
                        day : date,
                    })
                }
                this.events = events
            });
        },
    },
    
}
</script>

<style scoped src="../../css/Calendar.css">
</style>