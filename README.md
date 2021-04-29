# 🏫 BTS(Back To School)
<div>
<p align="center">
  <img width="200px;" src="/uploads/921b91e4c2c9765b82866f4427043133/school.png" />
  <br/><b>10대의 코딩 학습 공유 플랫폼</b>
  <p align="center">
 <img src="https://img.shields.io/badge/JavaScript-ES6+-green?logo=javascript">
 <img src="https://img.shields.io/badge/Vue.js-v4.5.10-green?logo=vue.js">
 <img src="https://img.shields.io/badge/Vuetify-v3-green?logo=Vuetify">
 <img src="https://img.shields.io/badge/Java-v1.8-blue?logo=java">
 <img src="https://img.shields.io/badge/spring JPA-v3.9.15-blue?logo=spring">
 <img src="https://img.shields.io/badge/spring Boot-v3.9.15-blue?logo=spring">
 <img src="https://img.shields.io/badge/AWS%20RDS----blue?logo=amazonaws">
 <img src="https://img.shields.io/badge/MySQL-v8.0-blue?logo=mysql">
 <img src="https://img.shields.io/badge/Unity-v4.0-yellow?logo=unity">
 <img src="https://img.shields.io/badge/C-v4.0-yellow?logo=C">
 <img src="https://img.shields.io/badge/Photon2-v2.0-yellow?logo=Photon2">
  </p>
<br/>  
<br/> 
</p>    
</div>


> 서비스명: BTS       
> 팀명: 끄덕끄덕  
> 개발 기간: 2021.04.12 ~ 2021.05.28 (약 7주)       

## 📑 문서 
> [1️⃣SUB1_기획문서_1] https://www.notion.so/_SUB1-5fbe04ad315141db9f23d63805cd60dd        
> 
<br/>  
<br/> 

## 👨 팀원 역할   
| 이름   | 역할 | 내용                        |
| ------ | ---- | --------------------------- |
| 유진이 | 팀장 | 풀스택 개발, UI/UX디자인, 와이어프레임      |
| 김지형 | 팀원 | 프론트앤드 개발                        |
| 정다운 | 팀원 | 풀스택 개발, QA(Jira관리), UI/UX디자인 |
| 황호연 | 팀원 | 유니티 개발, QA(Jira관리), 영상제작    |
| 정혜지 | 팀원 | 프론트앤드 개발, 테스트케이스       |

<br/>  
<br/>    


## 📑 Gantt Chart     

```mermaid
gantt
    title BTS 진행상황
    dateFormat  YYYY-MM-DD
    section 프로젝트 준비
    프로젝트 기획 및 명세서제작 : done, 2021-04-12, 5d
    jira,Git 생성  :done, 2021-04-15, 6h
    UI/UX 다지인  : done,   2021-04-19, 2d
    로고제작 : active, 2021-05-01, 2d
    와이어프레임ppt : active, 2021-05-03, 1d
    사전학습  : done, 2021-04-14, 10d
    교보재 jpa 학습 : done, 2021-04-26, 2d

    section Frontend
    웹캠페이지 : crit, 2021-04-21, 8d
    채팅 : done, 2021-04-24, 3d
    유트브 : active, 2021-04-29, 2d
    로그인/회원가입/비번찾기 페이지 UI : crit, 2021-04-27, 4d
    로그인/회원가입/비번찾기 페이지 동적구현 : active, 2021-04-30, 3d
    마이페이지 UI : crit, 2021-04-26, 4d
    정보공유 페이지 : crit, 2021-04-26, 4d
    
    section Backend
    DB설계 : done, 2021-04-21, 2d
    매핑관계 설정 : done, 2021-04-28, 1d
    유저 CRUD : crit, 2021-04-29, 2d
    게시판 CRUD : crit, 2021-04-29, 3d
    
    section Game
	네트워크 동기화 : done, 2021-04-22, 3d
	로컬 리모트 및 호스트 : done, 2021-04-23 1d
	채팅 : done, 2021-04-24, 2d
	로비 구현 : done, 2021-04-24, 4d
    방 리스트 : done, 2021-04-24, 4d
    게임(운동장) : active, 2021-04-28,3d
    유니티 보완작업 : crit, 2021-04-28, 7d
    
    section 발표관리
    Sub1 ppt 제작 : done, 2021-04-22, 1d
    Sub1 발표준비 : done, 2021-04-22, 1d
```

<br/>  
<br/>     


## 📑 서비스 개요      
- 사회현상       



<br/>  
<br/>    


## 📑 주요 기능     

<br/>  
<br/> 


### 💻 주요 기능 미리보기   


<br/>  
<br/>     

## 📑 규칙     
### ✔ Branch 규칙  
```bash
develop - feature/기능명/fe or be       
```


<br/>

### ✔ Code Style
| FrontEnd   | BackEnd |
| ------ | ---- |
| 1. 폴더명: 첫글자 대문자<br/>→ ex) Feed/Add.vue <br/><br/>2. 파일명: 첫글자 대문자 <br/> → ex) Add.vue <br/><br/>3. 경로명: 소문자 → ex) /add | 1. 클래스명:  첫글자 대문자 + camel case <br/> ex) MainController.java <br/><br/> 2. 함수, 변수: 첫글자 소문자 + camel case <br/> ex) public void setUserName(); |
- ☑ 프론트앤드 규칙
    - 플러그인 : Vuetify 사용
    - css는 import해서 사용 -> style.css에서 공통속성 사용
    - 공통파일 수정 시 팀채널에 공유하기 
    - http파일 import해서 사용하기 


- ☑ 백앤드 명명규칙 
    - [참고] https://velog.io/@aidenshin/Java-%EC%9E%90%EB%B0%94-%EC%BD%94%EB%94%A9-%EA%B7%9C%EC%B9%99-Java-Code-Conventions#%EB%AA%85%EB%AA%85naming-%EA%B7%9C%EC%B9%99    



- if문
    - 한줄 일 때, Block 처리하기
    - else if / else /중괄호는 조건문 바로 옆에 붙이기

    ```java
    if(condition){
     statement;
    } else if(condition2){
     statement2;
    } else{
     statement3;
    }
    ```

- for
  
    - 단순 반복문은 iterator를 i,j,k,...,z순으로 명명하기
- 주석 상대방이 이해할 수 있도록 달기
    - /**/ 설명 여러줄 필요할 때 코드 위에 작성
    - // 간단한 주석 코드 옆에 작성


<br/>

### ✔ 포트번호    

```
프론트 : 8000
백 : 8080
게임 : 8081
DB : 3306
Jenkins : 9090
```

<br/>

### ✔ commit 규칙    

📌 하나의 기능 완성되면 한번에 커밋

```
git commit -m "{지라이슈번호} [작업분류] : {FE or BE} - 작업커밋내용"
git commit -m "S04P22B107-56 [feat] : BE_0415 - AWS RDS, S3 서버 및 기본환경 세팅"

**프론트** : "{지라이슈번호 하나}_[작업분류] : FE_날짜:완성한 기능(작업내용)" 
		ex) S04P22B107-56_ [feat] : FE_0118:피드작성 컴포넌트 추가/수정/삭제

**백** : "{지라이슈번호 하나}_[작업분류] : BE_날짜: 완성한 기능(작업내용)" 
		ex) S04P22B107-56__ [feat] : BE_0118: 피드작성 컴포넌트 추가/수정/삭제

**리드미** : "README_날짜: 수정한 부분" 
		ex) S04P22B107-56_ [feat] : README_0118: 와이어프레임 수정

```
```
[ 작업 분류 ]
- feat : 새로운 기능 추가
- fix : 버그 수정
- docs : 문서 수정
- style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- refactor : 코드 리펙토링
- test : 테스트 코드, 리펙토링 테스트 코드 추가
- chore : 빌드 업무 수정, 패키지 매니저 수정
```
<br />


## 📑산출물  
> 1. ER 다이어그램     
> 2. 와이어프레임  

### ✔ ER 다이어그램      



### ✔ 와이어프레임    



<br />



