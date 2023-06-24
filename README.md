# Spring-Project 입문 주차 과제
## 1. 기능 요구 사항
![image](https://github.com/baekgomsuyeom/Spring-Project/assets/117441902/faea05ac-ff9e-4ab2-b334-ac7972e33895)

## 2. 유스케이스 다이어그램
![Spring 입문 주차 유스케이스 다이어그램](https://github.com/baekgomsuyeom/Spring-Project/assets/117441902/1afe5792-d3d0-4dee-8d68-70753e4d76a7)

## 3. Api 명세서
![Spring 입문 주차 API 명세서](https://github.com/baekgomsuyeom/Spring-Project/assets/117441902/1648bd40-b77b-47be-a44a-9b249428e951)

## 4. Q & A
### Q1) 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
#### A1. 
JSON을 반환하는 API를 사용하라는 조건이 있었기 때문에 body 를 사용했다.

### Q2) 어떤 상황에 어떤 방식의 request를 써야하나요?
#### A2.
param은 원하는 조건의 데이터들 혹은 하나의 데이터에 대한 정보를 받아올때 적절하다.
query는 필터, 분류, 검색 등의 경우에 조건을 줘서 정제된 결과물을 얻을 수 있다.
body는 데이터가 노출되지 않기 때문에 숨겨야할 데이터 처리시 적절하다.

### Q3) RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
#### A3.
RESTful한 API 설계 : URL에는 명사를 사용, CRUD 기능 POST로만 처리하지 않고 GET, PUT, POST, DELETE 으로 자원에 대한 행위를 표현, Client-Server구조
Stateless (무상태성),  Cacheable (캐시 가능), Self-descriptiveness (자체 표현 구조), 계층형 구조 같은 부분들은 고려되지 않았던 것 같다.

### Q4) 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
#### A4. 
Entity 는 board 객체 하나였는데, 한 객체를 기준으로 봤을 땐 잘 분리됐다고 생각한다.

### Q5) API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
#### A5. 
네
