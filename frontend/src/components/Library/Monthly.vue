<template>
    <div id="month">
        <v-app id="inspire">
            <v-row class="fill-height">
                <v-col>
                    <v-sheet height="64">
                    <div id="monthly-header">
                        <div id="month-prev" class="calendar-button" @click="$refs.calendar.prev()"><v-icon>fas fa-angle-left</v-icon></div>
                        <div id="month-title" class="inline-p">
                            <v-toolbar-title v-if="$refs.calendar">{{ $refs.calendar.title }}</v-toolbar-title>
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
                        @click:event="openEvent"
                        @click:date="openEvents"
                        @click:more="openEvents"
                        @change="updateRange"
                    >
                    <template v-slot:event="{event}">
                        <div :style="{'background-color' : event.color}" class="fill-height pl-2 event">{{event.name}}</div>
                    </template>
                    </v-calendar>
                    </v-sheet>
                </v-col>
            </v-row>
        </v-app>
        <v-dialog v-model="showEvents" width="500">
            <v-card>
                <v-card-title class="headline grey lighten-2">
                    년월일
                    <v-spacer></v-spacer>
                    <v-icon @click="showEvents = false">fas fa-times</v-icon>
                </v-card-title>
                <v-divider></v-divider>
                <div class="event-item">
                    <div>이벤트 이름</div>
                    <div>이벤트 시작일</div>
                    <div>이벤트 종료일</div>
                </div>
            </v-card>
        </v-dialog>
        <v-menu id="event-detail" v-model="showEvent" :close-on-content-click="false" max-width="350px" :activator="selectedElement" offset-y>
            <v-card color="grey lighten-4" min-width="350px" flat>
            <v-toolbar class="headline grey lighten-2 event-item-title" color="var(--color-grey-5)">
                <input type="text" v-model="selectedEventName" :disabled="!activeModify"/>
                <v-spacer></v-spacer>
                <v-icon class="btn-event" @click="activeModify=true;" v-if="!activeModify">fas fa-pen</v-icon>
                <v-icon class="btn-event" @click="activeDelete=true;">fas fa-trash-alt</v-icon>
                <v-icon class="btn-event" @click="closeEvent">fas fa-times</v-icon>
            </v-toolbar>
            <v-card-text>
                <div class="event-item">
                    <table id="event-item-detail">
                        <tr>
                            <td>일정 시작일</td>
                            <td><input id="event-endDay" type="date" v-model="selectedEventStartDay" :disabled="!activeModify"/></td>
                        </tr>
                        <tr>
                            <td>일정 종료일</td>
                            <td><input id="event-endDay" type="date" v-model="selectedEventEndDay" :min="selectedEventStartDay" :disabled="!activeModify"/></td>
                        </tr>
                        <tr>
                            <td>색상</td>
                            <td>
                                <div :id="index" :class="{'event-color':true,'active-color' : selectedEventColor==index}" v-for="(color, index) in colors" :key="index" :style="{'background' : color}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </v-card-text>
            <v-card-actions class="card-actions" v-if="activeModify">
                <button class="card-btn" @click="modifyEvent(selectedEvent.id)">확인</button>
                <button class="card-btn" @click="cancleModify">취소</button>
            </v-card-actions>
            </v-card>
            <v-dialog v-model="activeDelete" width="300">
                <v-card>
                    <v-card-title id="delete-message">
                        일정을 삭제하시겠습니까?
                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-actions class="card-actions">
                        <button class="card-btn" @click="activeDelete=false;">예</button>
                        <button class="card-btn" @click="activeDelete=false;">아니오</button>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-menu>
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
            monYear : 2021,
            monMonth : 4,
            eventList : [],
            userId : 'dovvn',
            eventStyle : "",
            showEvent : false,
            showEvents : false,
            activeModify : false,
            activeDelete : false,
            selectedEventName : '',
            selectedEventStartDay : undefined,
            selectedEventEndDay : undefined,
            selectedEventColor : 0,
            showStartDate : false,
        }
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
        openEvent ({ nativeEvent, event }) {
            const open = () => {
                this.selectedEvent = event;
                this.selectedElement = nativeEvent.target;
                this.selectedEventName = event.name;
                this.selectedEventStartDay = event.startDay;
                this.selectedEventEndDay = event.endDay;
                this.selectedEventColor = event.colorId;
                requestAnimationFrame(() => requestAnimationFrame(() => this.showEvent = true))
            }

            if (this.showEvent) {
                this.showEvent = false;
                requestAnimationFrame(() => requestAnimationFrame(() => open()))
            } else {
                open()
            }
            nativeEvent.stopPropagation()
        },
        closeEvent(){
            this.showEvent = false;
            this.activeModify = false;
            this.selectedEventName = '';
            this.selectedEventStartDay = undefined;
            this.selectedEventEndDay = undefined;
            this.selectedEventColor = 0;
        },
        modifyEvent(id){
            const event = {
                modId : id,
                userId : this.userId,
                monYear : '',
                monStartDate : this.selectEventStartDay,
                monEndDate : this.selectEventEndDay,
                monContent : this.selectedEventName,
                monColor : this.selectedEventColor,
            }
        },
        cancleModify(){
            this.selectedEventName = this.selectedEvent.name;
            this.selectEventStartDay = this.selectedEvent.startDay;
            this.selectEventEndDay = this.selectedEvent.endDay;
            this.activeModify = false;
        },
        deleteEvent(){
            
        },
        openEvents({date}){
            const open = () => {
                this.selectedEvent = event;
                this.selectedElement = nativeEvent.target;
                this.selectedEventName = event.name;
                this.selectEventStartDay = event.startDay;
                this.selectEventEndDay = event.endDay;
                requestAnimationFrame(() => requestAnimationFrame(() => this.showEvent = true))
            }

            if (this.showEvent) {
                this.showEvent = false
                this.selectedEventName = '',
                this.selectEventStartDay = undefined;
                this.selectEventEndDay = undefined;
                requestAnimationFrame(() => requestAnimationFrame(() => open()))
            } else {
                open()
            }
            nativeEvent.stopPropagation()
        },
        updateRange ({ start, end }) {
            http.get(`/api/v1/monthly/${this.userId}/${start.year}/${start.month}`).then(({data})=>{
                this.eventList = data.data;
                const events = [];
                const eventCount = this.eventList.length;
                for (let i = 0; i < eventCount; i++) {
                    const first = new Date(this.eventList[i].monStartDate);
                    const second = new Date(this.eventList[i].monEndDate);
                    events.push({
                        name: this.eventList[i].monContent,
                        start: first,
                        end: second,
                        color: this.colors[this.eventList[i].monColor],
                        colorId : this.eventList[i].monColor,
                        id : this.eventList[i].monId,
                        startDay : this.eventList[i].monStartDate,
                        endDay : this.eventList[i].monEndDate,
                    })
                }
                this.events = events
            });
        },
        isFutureDate(date){
            return date < this.selectedEventStartDay;
        },
    },
}
</script>

<style scoped src="../../css/Calendar.css">
</style>