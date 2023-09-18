### 시작 2022-03-08
---


1. 프로젝트
```
인프런 김영한 JPA활용-1 편 기록
```

2. 개요
   + 명칭 : JpaShop
   + 개발자 : 조라도스
   + 개발기간 : 2022-03-08 ~ ing
   + 기능 : 회원기능,상품기능,주문기능
   + 사용언어 : JAVA 11
   + 개발환경 : SpringBoot 2.6.4 / jpa(Java Persistence API / gradle / Hibernate / Thymeleaf(view템플릿)
       + Thymeleaf(view템플릿) : html 태그에 속성을 추가해 페이지에 동적으로 값을 추가하거나 처리하여 화면을 구성할 수 있게 해줍니다.
       + Hibernate : 자바 언어를 위한 ORM 프레임워크입니다. JPA의 구현체로, JPA 인터페이스를 구현하며, 내부적으로
               JDBC API를 사용합니다. 객체 지향 도메인 모델을 관계형 데이터베이스로 매핑하기 위한 프레임워크를 제공다.
       + jpa : 자바 애플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스입니다.


3. 요구사항 분석(도메인 설계)
    + 회원 기능 -> 회원 등록 / 회원 조회
    + 상품 기능 -> 상품 등록 / 상품 수정 / 상품 조회
    + 주문 기능 -> 상품 주문 / 주문 내역 조회 / 주문 취소 
    + 기타 요구사항
       + 상품은 제고 관리가 필요하다.
       + 상품의 종류는 도서,음반,영화가 있다.
       + 상품을 카테고리로 구분할 수 있다.
       + 상품 주문시 배송 정보를 입력할 수 있다. 
                             
                             

### 최종 도메인 화면
---
![image](https://user-images.githubusercontent.com/100845256/159110882-f1a130ea-4ee8-4ed2-985b-cd028ac698a6.png)



### 공부기록
----

* [1주차 프로젝트생성 및 개발](https://github.com/Jorados/Myproject/blob/main/Progress/1%EC%A3%BC%EC%B0%A8.md)
* [2주차 API개발과 성능 최적화](https://github.com/Jorados/Myproject/blob/main/Progress/API%20%EA%B0%9C%EB%B0%9C%EA%B3%BC%20%EC%84%B1%EB%8A%A5%20%EC%B5%9C%EC%A0%81%ED%99%94.md)

