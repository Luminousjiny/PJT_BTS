import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/src/views/Login/Login.vue";
import Character from "@/src/components/Login/Character.vue";
import Camera from "@/src/components/Login/Camere.vue";
import Join from "@/src/views/Join/Join.vue";
import FindPw from "@/src/views/FindPw/FindPw.vue";
import Change from "@/src/components/FindPw/Change.vue";
import Confirm from "@/src/components/FindPw/Confirm.vue";
import Computer from "@/src/views/Computer/Computer.vue";
import Board from "@/src/components/Computer/Board.vue";
import Cook from "@/src/views/Cook/Cook.vue";
import Library from "@/src/views/Library/Library.vue";
import Calender from "@/src/components/Library/Calender.vue";
import Youtube from "@/src/components/Library/Youtube.vue";
import Monthly from "@/src/components/Library/Monthly.vue";
import Weekly from "@/src/components/Library/Weekly.vue";
import Rest from "@/src/views/Library/Rest.vue";
import Play from "@/src/views/Library/Play.vue";
import Office from "@/src/views/Library/Office.vue";
import WebCam from "@/src/common/WebCam/WebCam.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Login',
    componenet: Login,
    children: [
      {
        path:"character",
        name: "Character",
        component: Character
      },
      {
        path:"camera",
        name: "Camera",
        component: Camera
      },
    ]
  },
  {
    path: '/join',
    name: 'Join',
    componenet: Join
  },
  {
    path: '/findpw',
    name: 'FindPw',
    componenet: FindPw,
    children: [
      {
        path:"confirm",
        name: "Confirm",
        component: Confirm
      },
      {
        path:"change",
        name: "Change",
        component: Change
      },
    ]
  },
  {
    path: '/mypage',
    name: 'MyPage',
    componenet: MyPage
  },
  // { // 게임 부분 수정 필요 - 잘모름
  //   path: '/school',
  //   name: 'School',
  //   componenet: School,
  //   children: [
  //     {
  //       path:"",
  //       name:"SchoolList",
  //       component: SchoolList
  //     },
  //   ]
  // },
  {
    path: '/computer',
    name: 'Computer',
    componenet: Computer,
    children: [
      {
        path:"webcam",
        name: "WebCam",
        component: WebCam
      },
      {
        path:"board",
        name: "Board",
        component: Board
      },
    ]
  },
  {
    path: '/cook',
    name: 'Cook',
    componenet: Cook,
    children: [
      {
        path:"webcam",
        name: "WebCam",
        component: WebCam
      },
    ]
  },
  {
    path: '/library',
    name: 'Library',
    componenet: Library,
    children: [
      {
        path:"youtube",
        name: "Youtube",
        component: Youtube
      },
      {
        path:"calender",
        name: "Calender",
        component: Calender,
        children: [
          {
            path:"monthly",
            name: "Monthly",
            component: Monthly
          },
          {
            path:"weekly",
            name: "Weekly",
            component: Weekly
          },
        ]
      },
    ]
  },
  {
    path: '/rest',
    name: 'Rest',
    componenet: Rest,
    children: [
      {
        path:"webcam",
        name: "WebCam",
        component: WebCam
      },
    ]
  },
  {
    path: '/play',
    name: 'Play',
    componenet : Play
  },
  {
    path: '/office',
    name: 'Office',
    componenet : Office
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
