package com.example.TravelPlanner.service.login;

//서비스 인터페이스는 구현 세부 사항에 대한 의존성을 분리
//인터페이스에 정의된 메서드를 호출하는 곳은 실제로 어떤 구현이 사용되는지 신경 쓰지 않아도 ok
//유연성과 유지 관리성을 높여줌
public interface LoginService {
    boolean validateMember(String memberId, String password);
}
