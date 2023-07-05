# MadCampWeek1
![KakaoTalk_20230705_022259500](https://github.com/mistercoin818/MadCampWeek1/assets/63841863/a9c72c29-44ec-4d70-b529-c1ee10e652b2)


## 👩‍👦‍👦Team Members
> 1분반

|  Name  |                    GitHub ID                    |          소속           |
| :----: | :---------------------------------------------: | :---------------------: |
| 민지연 |    [Odung2](https://github.com/Odung2)    | 한국과학기술원 전산학부 |
| 김재민 | [goodjm0698](https://github.com/goodjm0698) |    고려대학교 컴퓨터학과    |
| 김태훈 | [mistercoin818](https://github.com/mistercoin818) |    성균관대학교 소프트웨어학과    |


## 📱Application 개요
2023 KAIST 여름 몰입캠프 공통과제 1(2023.06.29. ~ 2023.07.05.)을 수행한 결과물입니다.


코로나 양성 두 명과 음성 한 명으로 이루어진 팀이라 Application 이름을 Helium+로 지었습니다.


총 3개의 탭으로 이루어져 있습니다.
1. 탭1: 나의 연락처
2. 탭2: 나만의 이미지 갤러리
3. 탭3: 위치 기반 날씨 정보


## 💣Development Environment
* OS : Android
* SDK 
```
minSdk: 26
targetSdk: 33
```
* Language : Kotlin
* IDE : Android Studio
* Target Device : Galaxy S7

## 🧭기능 설명


### Tab 1


### Tab 2


### Tab 3
**메인화면**<br/>
<img width="276" alt="image" src="https://github.com/mistercoin818/MadCampWeek1/assets/64831392/bb2939c1-e6f8-4122-8891-74efc1ab8612">

1. 위치
2. 기온
3. 날씨 아이콘
    1. 현재 날씨에 맞춰 아이콘으로 알려 준다. 아이콘 정보는 다음과 같다.

|  description  |                    Icon                    | 
| :----: | :---------------------------------------------: | 
|clear sky (day) |<img src="/app/src/main/res/drawable/icon_01d.png"  width="200" height="200"/> | 
|clear sky (night)|<img src="/app/src/main/res/drawable/icon_01n.png"  width="200" height="200"/> | 
|few clouds (day)|<img src="/app/src/main/res/drawable/icon_02d.png"  width="200" height="200"/> | 
|few clouds (night)|<img src="/app/src/main/res/drawable/icon_02n.png"  width="200" height="200"/> | 
|scattered clouds|<img src="/app/src/main/res/drawable/icon_03d.png"  width="200" height="200"/> | 
|broken clouds|<img src="/app/src/main/res/drawable/icon_04d.png"  width="200" height="200"/> | 
|shower rain|<img src="/app/src/main/res/drawable/icon_09d.png"  width="200" height="200"/> | 
|rain (day time)|<img src="/app/src/main/res/drawable/icon_10d.png"  width="200" height="200"/> | 
|rain (night time)|<img src="/app/src/main/res/drawable/icon_10n.png"  width="200" height="200"/> | 
|thunderstorm|<img src="/app/src/main/res/drawable/icon_11d.png"  width="200" height="200"/> | 
|snow|<img src="/app/src/main/res/drawable/icon_13d.png"  width="200" height="200"/> | 
|mist|<img src="/app/src/main/res/drawable/icon_50d.png"  width="200" height="200"/> | 
4. 미세먼지 정도
    1. 미세 먼지 정도에 따라 원의 색이 바뀐다. 정보는 다음과 같다.
   
|  Airquality  |                    Img                    | 
| :----: | :---------------------------------------------: | 
| good |    <img src="/app/src/main/res/drawable/bg_good.png"  width="200" height="200"/>   | 
| soso |<img src="/app/src/main/res/drawable/bg_soso.png"  width="200" height="200"/> | 
| bad | <img src="/app/src/main/res/drawable/bg_bad.png"  width="200" height="200"/> |
| worst | <img src="/app/src/main/res/drawable/bg_worst.png"  width="200" height="200"/> | 
5. 측정시간
6. 새로고침 버튼
    1. 새로고침 버튼을 누르면 디바이스 기준 현재 위치의 날씨 정보를 불러 온다. 
7. 사이드바 열림 버튼

**사이드바**<br/>
<img width="310" alt="image" src="https://github.com/mistercoin818/MadCampWeek1/assets/64831392/43f07c94-15ab-4b2b-8a9b-84cf470a4555">

1. 헤더
2. 대한민국 광역시 별 날씨 정보
    1. 대한민국 광역시의 날씨 정보를 불러올 수 있다. 도시 정보는 다음과 같다. 서울,부산,대전,대구,인천,광주,울산

 


