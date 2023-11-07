# securityJwt

* Lombok 1.8.26 does not work with JDK21 (EA build 16) openjdk 20으로 실행

SPRING SECURITY 공부 1일차
* boot 기본설정

SPRING SECURITY 공부 2일차

* security 기본설정("login/logout url지정, 특정 url 권한지정")

SPRING SECURITY 공부 3일차

* security login , joinMemeber 설정
  
 (특이사항 : username값이 카멜표기로 인하여 오류발생) -> Jpa Repository를 상속받는 class의 Query Method의 값을 소문자로 표기하여 해결

SPRING SECURITY 공부 4일차

* security 권한설정

securedEnabled 
secured 어노테이션 활성화 -> 원하는 url에 권한설정역할

prePostEnabled 
PreAuthorize 어노테이션 활성화 -> 여러개의 권한을 hasRole('ROLE_MANAGE') or hasRole('ROLE_ADMIN') 과 같이 줄 수 있음

  
