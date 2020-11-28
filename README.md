# ✈️Traveler

## 🗒️ 프로젝트 개요

다양한 여행 플래너 플랫폼을 사용하면서 지원하지 않는 부분과 바라던 점을 차별화하여 **국내여행 특정 지역(제주도)지원, 여행에 대한 예산작성, 경유지 경로 최적화, 동행 모집**과 같은 기능을 가진 Spring Framework를 이용하여 제주도 여행 플래너 웹 애플리케이션을 제작하였습니다.

## 🚩 프로젝트 기간 & 팀원

- 2020년 3월 24일 - 2020년 11월 24일 (9개월)
- 맹준영(팀장), 김현홍(팀원), 권형준(팀원), 김호겸(팀원)

## ⚙️ 프로젝트 기술 스택

![logo](https://img.shields.io/badge/API-T_Map-red?style=flat) ![logo](https://img.shields.io/badge/API-Kakao_Login-FFCD00?logo=kakao&style=flat&logoColor=white) ![logo](https://img.shields.io/badge/API-국문_관광_정보-skyblue?style=flat) ![logo](https://img.shields.io/badge/Library-Google_Chart-4285F4?style=flat&logo=google&logoColor=white) ![logo](https://img.shields.io/badge/Library-Bootstrap-563D7C?style=flat&logo=bootstrap) ![logo](https://img.shields.io/badge/Language-Java_8-007396?style=flat&logo=java&logoColor=white) ![logo](https://img.shields.io/badge/Language-JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=white) ![logo](https://img.shields.io/badge/Language-JSP-orange?style=flat) ![logo](https://img.shields.io/badge/Language-JSTL-green?style=flat) ![logo](https://img.shields.io/badge/Language-jquery-0769AD?style=flat&logo=jquery&logoColor=white) ![logo](https://img.shields.io/badge/Database-Oracle-F80000?style=flat&logo=oracle&logoColor=white) ![logo](https://img.shields.io/badge/Database-Firebase-FFCA28?style=flat&logo=firebase&logoColor=white) ![logo](https://img.shields.io/badge/Server-ApacheTomcat_8.5v-D22128?style=flat&logo=apahce&logoColor=white) ![logo](https://img.shields.io/badge/Framework-SpringFramework_5.0.7-6DB33F?style=flat&logo=spring&logoColor=white) 

## ✈️기능 소개

<img src="https://user-images.githubusercontent.com/52314663/100517892-7c674280-31d1-11eb-8d8b-41105b8b2915.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100517900-82f5ba00-31d1-11eb-8bc6-64f30cf3871c.png" width="45%">
  
**Pic 1** 📷 : **여행지에 대한 조회수, 사용자가 입력한 키워드의 검색, 사용자의 북마크**, 이 4가지 방법으로 여행지의 검색 및 **마커와 인포윈도우를 이용해 위치 및 정보제공 (Tmap API 및 국문관광정보API 이용)**

**Pic 2** 📷 : 여행지의 위치를 간단한 드래그를 통해 옮겨 **경유지 경로 최적화**를 통해 이동 거리, 최적 경로 및 경과 시간 제공 **(Tmap API 경유지 최적화이용)**

<img src="https://user-images.githubusercontent.com/52314663/100517901-8426e700-31d1-11eb-9bcc-6d04aa9ef906.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100517902-85581400-31d1-11eb-9c87-39dcb8023075.png" width="45%">

**Pic 3 & 4** 📷 : DevExpress 라이브러리 중 Scheduler를 이용하여 **드래그 앤 드롭 방식**으로 **사용자가 저장한 여행 장소를 날짜 및 시간의 간편한 변경**이 가능하며, **더블 클릭**을 통해 **여행지에 대한 상세정보 추가 및 사용자가 원하는 여행지 추가 커스터마이징** 기능 제공 **(DevExpress Shceduler Library 사용)**

<img src="https://user-images.githubusercontent.com/52314663/100517903-8721d780-31d1-11eb-9a42-c97e23bdfe03.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100517905-87ba6e00-31d1-11eb-96dd-1ece5aab2bb1.png" width="45%">

**Pic 5 & 6** 📷 : 사용자의 여행 일차에 맞는 공개 예산을 **총액 기준 최대/최소 금액으로 추천**해 예산 작성 참고하도록 지원하였으며 Google Chart Library의 Bar Chart와 Pie Chart를 이용하여 **금액별, 카테고리 별 예산 추이**를 보여주는 기능 **(Google Chart Library 이용)**

<img src="https://user-images.githubusercontent.com/52314663/100519064-e3d4c080-31d8-11eb-9997-107809c5e4cb.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100519065-e6371a80-31d8-11eb-840b-9361dccd56df.png" width="45%">

**Pic 7 & 8** 📷 : **사용자의 여행 일정을 기반으로 예산을 제공**하도록 기본으로 여행 일정이 작성되어 있으며 보다 정확한 예산 작성을 지원하기 위해 **API가 제공하는 요금정보를 이용하고 추가 예산 작성**이 가능

<img src="https://user-images.githubusercontent.com/52314663/100519067-e7684780-31d8-11eb-8004-66419882e2c6.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100519068-e8997480-31d8-11eb-95ea-7d0d5d0f4ffd.png" width="45%">

**Pic 9 & 10** 📷 : 동행 모집은 사용자의 여행 일정의 여행지로 호스팅이 가능하며 호스팅하기 이전에 같은 여행지를 여행하는 **동행 게시글 중 사용자의 여행 날짜, 가까운 시간, 마감이 가까운 순으로 추천해주는 자체 알고리즘**을 이용해 게스트로 참가할 수 있는 방향성 제공 

<img src="https://user-images.githubusercontent.com/52314663/100519070-e9caa180-31d8-11eb-98bc-630c46a84a15.png" width="45%" alt="Pic 1"> <img src="https://user-images.githubusercontent.com/52314663/100519071-ea633800-31d8-11eb-82b6-285d1bf770d7.png" width="45%">

**Pic 11 & 12** 📷 : 동행 호스트 혹은 게스트로 참가했을 때 호스트와 게스트간의 여행 일정 및 여행 정보 공유를 위해 **Firebase의 Realtime Database를 이용한 실시간 채팅과 DB를 이용한 쪽지를 구현**하여 연락 수단 제공

## 🔗 프로젝트 깃허브 주소

👉🏻 [Traveler github](https://github.com/jymaeng95/Traveler)

## 🔗 Traveler 시연 영상

👉🏻 [Traveler 시연 영상](https://youtu.be/B48eBHVYirU)

## 💡Reference

- UI(HTML, CSS, Javascript)
    1. [https://codepen.io/ChynoDeluxe/pen/bdXeqQ](https://codepen.io/ChynoDeluxe/pen/bdXeqQ)
    2. [https://codepen.io/FlorinPop17/pen/vPKWjd](https://codepen.io/FlorinPop17/pen/vPKWjd)
    3. [https://codepen.io/GeorgePark/pen/VXrwOP](https://codepen.io/GeorgePark/pen/VXrwOP)
- Library & API
    1. [https://developers.kakao.com/docs/latest/ko/kakaologin/common](https://developers.kakao.com/docs/latest/ko/kakaologin/common)
    2. [http://tmapapi.sktelecom.com/main.html#](http://tmapapi.sktelecom.com/main.html#)
    3. [https://developers.google.com/chart/interactive/docs/gallery](https://developers.google.com/chart/interactive/docs/gallery)
    4. [https://www.data.go.kr/data/15057787/openapi.do](https://www.data.go.kr/data/15057787/openapi.do)
    5. [https://js.devexpress.com/Documentation/ApiReference/UI_Widgets/dxScheduler/](https://js.devexpress.com/Documentation/ApiReference/UI_Widgets/dxScheduler/)
