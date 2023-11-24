# Security OAuth2.0 Login

# Authorization Code Grant Type 방식으로 구현

* Lombok 1.8.26 does not work with JDK21 (EA build 16) openjdk 20으로 실행

SPRING SECURITY 공부 1일차
* boot 기본설정

==================================================================

SPRING SECURITY 공부 2일차

* security 기본설정("login/logout url지정, 특정 url 권한지정")

==================================================================

SPRING SECURITY 공부 3일차

* security login , joinMemeber 설정
  
 (특이사항 : username값이 카멜표기로 인하여 오류발생) -> Jpa Repository를 상속받는 class의 Query Method의 값을 소문자로 표기하여 해결

==================================================================
 
SPRING SECURITY 공부 4일차

* security 권한설정

securedEnabled 
secured 어노테이션 활성화 -> 원하는 url에 권한설정역할

prePostEnabled 
PreAuthorize 어노테이션 활성화 -> 여러개의 권한을 hasRole('ROLE_MANAGE') or hasRole('ROLE_ADMIN') 과 같이 줄 수 있음


==================================================================

SPRING Oauth 2.0 Login

* spring security oauth 2.0 login 기본설정 및 security 설정추가

==================================================================

UserDetails 
security 로그인에 필요한 값을 지정하는 인터페이스

OAuth2User
OAuth2로그인에 필요한 값을 지정하는 인터페이스


* security session 에 Authentication 객체만 들어갈 수 있다.

Authentication 
로그인 인증정보를 받아올 수 있는 객체 

Authentication의 return값은 Object

일반로그인 시 

Atuentication (UserDetails) Type으로 반환가능

@AuthenticationPrincipal 
UserDetails를 상속받은 클래스로 인증정보를 받아올 수 있다.

위 두개는 같은 정보를 반환, google login시에는 값 이용불가


Oauth2 login시
 
Oauth2 login 정보를 받아오려면 (OAuth2User) Type으로 반환받아야한다.

@AuthenticationPrincipal OAuth2User OAuhth2user 로 값을 받아야한다.


로그인 시 구분을 하기 힘듬으로 

userDetails 와 Oauth2User 를 implement하는 class를 만들어 반환한다.

국내 로그인들은 provider에 등록해주어야한다.

UserDetailService or DefaultOAuth2UserService 상속받은 클래스에

재정의 된 loadUser or loadUserByUsername 함수 종료 시 

@AuthenticationPrincipal 어노테이션 생성


==================================================================

JWT (Json Web Token) 학습 전 

세션 생명주기

1. 브라우저 종료 시
2. 시간 초과 시
3. 서버 측에서 세션제거

서버에서 세션을 db에 저장하고 세션을 불러올 떄 마다 속도문제가 생긴다. 
이떄 캐싱을 통하여 한번 조회 된 캐시를 서버 접속없이 처리하여 빠르게 불러올 수 있다.
캐싱을 하는 대표적인 오픈소스는 Redis





  
