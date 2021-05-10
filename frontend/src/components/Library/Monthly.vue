<template>
    <div id="month">
        <v-app id="inspire">
            <v-row class="fill-height">
                <v-col>
                    <v-sheet height="64">
                    <div id="monthly-header">
                        <div id="month-prev" class="calendar-button" @click="$refs.calendar.prev()"><v-icon>fas fa-angle-left</v-icon></div>
                        <div id="month-title" class="inline-p">
                        <v-toolbar-title v-if="$refs.calendar">
                        {{ $refs.calendar.title }}
                        </v-toolbar-title>
                        </div>
                        <div id="month-next" class="calendar-button" @click="$refs.calendar.next()"><v-icon>fas fa-angle-right</v-icon></div>
                    </div>
                    </v-sheet>
                    <v-sheet height="800">
                    <v-calendar
                        ref="calendar"
                        v-model="focus"
                        color="var(--color-midBlue)"
                        :events="events"
                        :event-color="getEventColor"
                        type="month"
                        @click:event="showEvent"
                        @change="updateRange"
                    ></v-calendar>
                    </v-sheet>
                </v-col>
            </v-row>
        </v-app>
    </div>
</template>

<script>
import http from '@/util/http-common.js';
export default {
    name : "Month",
    data() {
        return {
            focus : '',
            selectedEvent : {},
            selectedElement : null,
            selectedOpen : false,
            events : [],
            colors: ['var(--color-yellow2)', 'var(--color-green3)', 'var(--color-blue4)', 'var(--color-blue5)', 'var(--color-puple6)', 'var(--color-grey7)'],
            names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
            monYear : 2021,
            monMonth : 4,
            eventList : [],
        }
    },
    created(){
        http.get(`/api/v1/monthly/${this.monYear}/${this.monMonth}`).then(({data})=>{
            
            this.eventList = data.data;
        });
    },
    mounted() {
        this.$refs.calendar.checkChange()
    },
    methods: {
        getEventColor (event) {
            return event.color
        },
        setToday () {
            this.focus = ''
        },
        showEvent ({ nativeEvent, event }) {
            const open = () => {
                this.selectedEvent = event
                this.selectedElement = nativeEvent.target
                requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
            }

            if (this.selectedOpen) {
                this.selectedOpen = false
                requestAnimationFrame(() => requestAnimationFrame(() => open()))
            } else {
                open()
            }

            nativeEvent.stopPropagation()
        },
        updateRange ({ start, end }) {
            // const events = []
            // const min = new Date(`${start.date}T00:00:00`)
            // const max = new Date(`${end.date}T23:59:59`)
            // const days = (max.getTime() - min.getTime()) / 86400000
            // const eventCount = this.rnd(days, days + 20)
            
            // for (let i = 0; i < eventCount; i++) {
            //     const allDay = this.rnd(0, 3) === 0
            //     const firstTimestamp = this.rnd(min.getTime(), max.getTime())
            //     const first = new Date(firstTimestamp - (firstTimestamp % 900000))
            //     const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
            //     const second = new Date(first.getTime() + secondTimestamp)

            //     events.push({
            //     name: this.names[this.rnd(0, this.names.length - 1)],
            //     start: first,
            //     end: second,
            //     color: this.colors[this.rnd(0, this.colors.length - 1)],
            //     timed: !allDay,
            //     light : true,
            //     })
            // }
            const events = [];
            const eventCount = this.eventList.length;
            for (let i = 0; i < list.length; i++) {
                const first = new Date(list[i].monStartDate);
                const second = new Date(list[i].monEndDate);
                events.push({
                    name: list[i].monContent,
                    start: first,
                    end: second,
                    color: this.colors[this.rnd(0, this.colors.length - 1)],
                    timed : true,
                })
            }

            this.events = events
        },
        rnd (a, b) {
            return Math.floor((b - a + 1) * Math.random()) + a
        },
    },
}
</script>

<style scoped src="../../css/Calendar.css">
</style>