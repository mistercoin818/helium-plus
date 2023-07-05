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


### Tab 1 : 전화번호부
핸드폰의 전화 앱으로부터 연락처를 불러와서 연락처를 띄워줍니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/1e7a3382-1b74-421c-b3f0-a66ccee90b4a"  width="200" height="400"/>


오른쪽 하단의 + 버튼을 누르면 전화 앱으로 연동되어 연락처를 추가할 수 있습니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/1d67ef4e-04e0-4ca8-acee-ef60260401cb"  width="200" height="400"/>


"+" 버튼 위의 새로고침 버튼을 누르면 새로고침 버튼이 빙글빙글 돌아가며 전화앱으로부터 최신 연락처를 업데이트해옵니다.

  
<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/26bece19-17f4-49dd-b057-4ec60b7e05c6" width="200" height="400"/>


맨 위의 돋보기 버튼을 누르면 전화번호든 이름이든 일치하는 연락처를 나타내어 줍니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/faee7c37-57cd-45a2-bf57-24e1e916de35" width="200" height="400"/>


원하는 사람의 연락처를 터치하면 그 사람에게 전화를 걸 수 있도록 전화 앱으로 이동됩니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/27d87246-5913-4a7a-9218-4f713a94edfe" width="200" height="400"/>


삭제하고 싶은 연락처를 길게 누르면 삭제할 지 물어보는 alert가 뜹니다. Yes를 누르면 삭제됩니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/e3b0487d-6092-4890-b24b-093914acc845" width="200" height="400"/>



### Tab 2 : 갤러리


갤러리 메인 화면입니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/338d9ecc-f21e-467f-adeb-99d8403f7e56" width="200" height="400"/>


원하는 사진을 클릭하면 크게 볼 수 있습니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/9ca1a15d-2147-4726-ba85-c8aaac40a41e" width="200" height="400"/>


사진첩 열기를 통해서 핸드폰 갤러리에 있는 사진을 추가할 수 있습니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/1737ef16-6e39-4708-9a0e-83a42f986b5b" width="200" height="400"/>


원하는 사진이 추가되면 view 맨 아래에 사진이 추가된 모습을 볼 수 있습니다.


<img src="https://github.com/mistercoin818/MadCampWeek1/assets/63841863/afe56f5a-9088-4dea-85cf-4ed96c01f322" width="200" height="400"/>


### Tab 3 : 날씨
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

 


