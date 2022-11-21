# -DB-

## 요구사항 분석

### 관리자 요구사항
관리자는 데이터베이스를 초기화 할 수 있어야 함
관리자는 모든 테이블에 대한 입력 / 삭제 / 변경이 가능해야 함
관리자는 모든 테이블을 조회할 수 있어야 함
회원 요구사항
회원은 모든 영화에 대한 조회를 할 수 있어야 함
영화명으로 조회가 가능해야 함
감독명으로 조회가 가능해야 함
배우명으로 조회가 가능해야 함
장르로 조회가 가능해야 함
조회한 영화에 대해서 예매가 가능해야 함
회원이 예매한 영화에 대해서 영화명, 상영일, 상영관번호, 좌석번호 및 판매가격 정보들을 조회가 가능해야 함
회원이 예매한 정보를 클릭하면 해당 예매에 대하여 상영일정, 상영관, 티켓 정보들을 조회가 가능해야 함
회원이 예매한 영화에 대해서 조회 한 후 한 개 이상의 예매 정보를 삭제할 수 있어야 함
회원이 예매한 영화에 대해 조회 한 후 다른 영화로 예매를 변경할 수 있어야 함
회원이 예매한 영화에 대해 조회 한 후 다른 상영 일정으로 변경할 수 있어야 함





스키마 설계 구조
![image](https://user-images.githubusercontent.com/76419984/203111238-be60ad1a-8b63-42d2-a127-09d51b50debc.png)






프로그램 구조
![image](https://user-images.githubusercontent.com/76419984/203111385-f170a0f4-3d64-4d9e-bef9-4dfe69e48b87.png)





JC16010839M.java 파일에서 관리자와 회원을 선택 할 수 있음
Admin.java 파일은 관리자의 기능들이 담긴 파일
User.java 파일은 사용자의 기능들이 담긴 파일

![영화 샘플 데이터](https://user-images.githubusercontent.com/76419984/203111457-6495ab3b-666c-441e-9a51-2b0d7101252e.png)
영화 샘플 데이터

![회원 고객 샘플 데이터](https://user-images.githubusercontent.com/76419984/203111489-bbcebcc9-0b90-4007-a393-eff278735f81.png)
회원 고객 샘플 데이터

![예매정보 샘플 데이터](https://user-images.githubusercontent.com/76419984/203111505-08547f11-307d-458b-a41c-1bca1be60461.png)


모든 유저가 하나의 예매 정보를 가질 수 있도록 했다.
![상영일정 샘플데이터](https://user-images.githubusercontent.com/76419984/203111666-e5b8bdc4-4064-4e4c-a5ab-9cabec038b29.png)
상영일정 샘플데이터

본인이 예매한 영화에 대하여 조회하여 다른 상영 일정으로 변경하는 기능 은 제외하고 구현하였으며, 하나의 영화가 하나의 상영 일정만 가지는 것으로 가정하고 구현하였다.
본인이 예매한 영화에 대하여 조회하여 다른 영화로 예매를 변경하는 기능은 UI까지만 구현하였으며, sql문에서 실행되지 않는다.
 
좌석 샘플데이터, 
하나의 영화관이 좌석 3자리씩 가지도록 하였다.
![image](https://user-images.githubusercontent.com/76419984/203111715-f6512003-ca50-418a-9ae2-7c849c305484.png)












![image](https://user-images.githubusercontent.com/76419984/203111743-cf8ca583-0dd3-48db-8376-e9d555adac8b.png)
영화관 샘플 데이터, 모든 영화관의 각 좌석수는 총 3개이고 아래 0으로 보이는 것은 예매가 완료되어 좌석이 없는 영화관이다.
![image](https://user-images.githubusercontent.com/76419984/203111798-1700fb23-1d2c-4a51-a1a2-2a1336ade6fe.png)


티켓 샘플 데이터, 모든 유저가 하나의 ticket을 발급받았다.



*만약 정상 작동하지 않을 시, eclipse를 다시 실행을 해보면 정상 작동한다. *
*스키마 sql문과 초기화(drop, insert 등의 쿼리문)는 sql파일로 첨부 한다. *
*초기화 시, 해당 초기화 sql쿼리문을 사용한다.*
























테스팅

![image](https://user-images.githubusercontent.com/76419984/203111838-047f5b01-558d-4957-a9b1-12a3be5f3c1b.png)

프로그램을 시작하면 관리자와 회원을 선택할 수 있는 화면이 나옴.


두개의 버튼 중 관리자 버튼을 클릭했을 시 나오게 되는 화면
![image](https://user-images.githubusercontent.com/76419984/203112112-e3be3041-4ac1-4967-8911-232aadf857e8.png)

왼쪽 위 메뉴바를 클릭했을 시 각각 테이블을 조회할 수 있다.
![image](https://user-images.githubusercontent.com/76419984/203111901-f08c73ad-0ebb-469d-8c99-5e4784d162e9.png)


영화 테이블 조회 메뉴 클릭 시 영화번호, 영화명, 상영시간, 상영등급, 감독명, 배우명, 장르, 영화소개, 개봉일이 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111917-b7da9134-708f-4ccb-948b-44028ea56803.png)


상영일정 메뉴 클릭 시 상영일정번호, 영화번호, 상영관번호, 상영시작일, 상영요일, 상영회차, 상영시작시간이 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111927-6cdd62a3-5557-490e-ace5-bbe1fd3a8c12.png)


상영관 메뉴 클릭 시 상영관번호, 좌석수, 상영관 사용여부가 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111937-63802c18-5976-491d-a070-3a7bc910cd78.png)






티켓 메뉴 클릭 시 티켓번호, 상영일정번호, 상영관번호, 좌석번호, 예매번호, 발권여부, 표준가격, 판매가격이 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111956-2a88add7-ab5d-4291-853e-aa5a4ede5f01.png)


좌석 메뉴 클릭 시 좌석번호, 상영관번호, 좌석사용여부가 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111984-44e7d75e-c7e3-478c-8a70-72bb64e9779d.png)


회원 메뉴 클릭 시 회원 아이디, 고객명, 휴대폰번호, 전자메일주소가 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203111996-bcdf23d5-08b4-4c24-aad8-344c37f79303.png)


예매 번호 클릭 시 예매번호, 결제방법, 결제상태, 결제금액, 회원아이디, 결제일자가 조회된다.
![image](https://user-images.githubusercontent.com/76419984/203112004-021445a6-8b73-48c4-b970-52dc17e84076.png)



데이터베이스 초기화 메뉴 클릭 시 모든 테이블이 초기화 된다.

















데이터를 넣기 위해 데이터 삽입 쿼리를 실행 시킨다
![image](https://user-images.githubusercontent.com/76419984/203112221-6c260f17-562c-4811-9bd5-a4b96c9368e7.png)





프로그램 가운데 부분 메뉴바를 누르면 프로그램 최 하단에 입력창과 버튼이 등장한다.
![image](https://user-images.githubusercontent.com/76419984/203112237-4414651a-7a9d-4128-972c-41cc6c77547f.png)


입력창에 쿼리문을 입력하고 확인버튼을 누르면 데이터베이스에 입력/삭제/변경이 가능하다.
![image](https://user-images.githubusercontent.com/76419984/203112252-208e39dd-f2b9-4928-a21a-db1e15492693.png)







회원을 선택했을 시, 나오는 첫 화면이다. 
![image](https://user-images.githubusercontent.com/76419984/203112288-8030e234-c9f5-4b6a-9ee1-112eba69e463.png)

위의 콤보 박스에서 모든 영화를 조회할 것인지, 본인의 예매를 조회할 것인지 선택할 수 있다. 
![image](https://user-images.githubusercontent.com/76419984/203112319-4c30b389-160b-4381-8871-4313d0f70140.png)

본인의 예매 정보를 누르면 
이런 화면이 보이고 상단에 있는 ID:에 회원 id값을 하나 집어 넣고 확인 버튼을 누르면 
![image](https://user-images.githubusercontent.com/76419984/203112340-c443fd6e-88a4-42ed-9c79-4d1eac1fe318.png)
![image](https://user-images.githubusercontent.com/76419984/203112369-1610fec4-db27-4485-bfcc-682d21f2e94d.png)

모든 회원이 이렇게 하나의 영화가 예매되어 있는 것으로 보인다.
1,2,3번 회원은 다른 좌석번호를 가지고 모두 ‘그대가 조국’이라는 영화가 예매되어 있다.
4,5,6번 회원은 ‘몬스터 싱어’ 영화가 예매되어 있다.
7,8,9번 회원은 ‘퍼스트 러브’ 영화가 예매되어 있다.
10,11,12번 회원은 ‘스텔라’라는 영화가 예매되어 있다.



상세조회 선택 버튼을 누르면, 
![image](https://user-images.githubusercontent.com/76419984/203112611-5f7eea15-d664-4fca-9e15-7279f33c2d50.png)

이렇게 상영일정 정보, 상영관 정보, 티켓 정보가 보인다.
다른 회원의 예매정보를 보기 위해서는 다시 위의 콤보 박스에서 ‘본인의 예매정보’를 선택하면 이 화면으로 돌아온다.
![image](https://user-images.githubusercontent.com/76419984/203112631-e343c331-e25d-4cb7-884a-c0e2a09fe5ab.png)

그대가 조국이라는 영화의 삭제 버튼을 눌렀을 때, 해당 ui에서도 삭제가 되고, DB에도 반영이 된다.
다시 2번을 조회해서 영화변경에서 예매변경 버튼을 누르면, 
![image](https://user-images.githubusercontent.com/76419984/203112650-8a82e028-b06b-4619-96c8-907d40d16183.png)

![image](https://user-images.githubusercontent.com/76419984/203112677-be4ca113-03c9-405d-9e1b-423a849b13ef.png)
![image](https://user-images.githubusercontent.com/76419984/203112692-0ad06686-05c9-4d1c-b4c5-4a4769389430.png)

이렇게 ui 해당 창이 나와 애매 아이디(영화 번호)를 넣으면 변경이 가능하도록 구현하려고 하였으나, 이 부분은 완벽하게 구현하지 못했다. 상영일정 변경도 앞에서 말한 것처럼, 한 영화가 하나의 상영일정만을 갖는 것으로 가정하여 구현하지 못했다.

![image](https://user-images.githubusercontent.com/76419984/203112720-2950587c-222a-404c-a19d-40840c92d962.png)

‘모든 영화 조회’를 눌렀을 때의 창이다.
![image](https://user-images.githubusercontent.com/76419984/203112731-db5265a8-eb38-4672-a1af-1617df9de636.png)

먼저 아래 보이는 ID에 한 명의 user의 id값을 넣고 id저장 버튼을 누른다. 회원 아이디 1번을 저장했다.
그 후, 원하는 영화에 예매하기의 예매 버튼을 누른다. 영화 번호 2번의 ‘닥터 스트레인지’를 예매한다고 하자.
![image](https://user-images.githubusercontent.com/76419984/203112759-d9fe3923-df41-4433-a09a-3aeec860171b.png)
![image](https://user-images.githubusercontent.com/76419984/203112782-64203c96-1eab-4921-b719-aacbbfac6164.png)

예매 버튼을 눌렀을 때, 회원 아이디를 입력해달라는 창이 나온다. 다시한번 회원 아이디 1을 입력하고 버튼을 누르면 왼쪽 상단 쪽에 예매가 성공하였다는 문구가 나온다.
![image](https://user-images.githubusercontent.com/76419984/203112797-6b482943-e518-4a13-a28f-f14bdf9a513b.png)


다시 본인의 예매정보에서 id:1을 넣고 확인을 해보면 잘 예매가 된 것을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/76419984/203112808-d89207a1-f13f-4836-9ac6-bf96ae3827af.png)

만약 회원아이디 1이 좌석이 없는 영화관에 예매를 한다고 하면,  
![image](https://user-images.githubusercontent.com/76419984/203112811-7f2f30ef-886c-4fb2-9d9b-9bbc6c913209.png)
![image](https://user-images.githubusercontent.com/76419984/203112829-548d2601-79b8-464a-b817-d950e4f201f2.png)
![image](https://user-images.githubusercontent.com/76419984/203112858-33f01bcc-38e1-4dc7-9efa-dbb33abddadb.png)

현재 영화관 7,8,9,10은 좌석이 없고, 하나의 영화는 하나의 schedule만을 가지고 있기 때문에 movieId가 7,8,9,10인 영화는 예매를 할 수가 없다.










다시 예매하기 창으로 와서 다시 하단에 id=1을 넣고 저장한 후, 영화 번호 12번의 예매버튼을 누른다.
![image](https://user-images.githubusercontent.com/76419984/203112880-844fb99f-b14f-4feb-a9cb-d67d657d6850.png)

그 후, 여기에도 1을 넣어 입력 버튼을 누르면
이렇게 예매할 수 없다는 안내 창이 나온다.
![image](https://user-images.githubusercontent.com/76419984/203112915-5e8847dd-47cf-44ab-b89c-079f74fed23a.png)






다시 조회를 해보면 반영이 안된 것을 확인할 수 있다.
![Uploading image.png…]()
