package hello.hello_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Member 클래스는 회원 정보를 담는 객체로, id와 name을 속성으로 가집니다.
@Entity
public class Member {

    // id는 회원의 고유 번호를 저장하는 변수입니다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name은 회원의 이름을 저장하는 변수입니다.
    private String name;

    // getId() 메서드는 id 값을 반환합니다.
    public Long getId() {
        return id;
    }

    // setId() 메서드는 id 값을 설정합니다.
    public void setId(Long id) {
        this.id = id;
    }

    // getName() 메서드는 name 값을 반환합니다.
    public String getName() {
        return name;
    }

    // setName() 메서드는 name 값을 설정합니다.
    public void setName(String name) {
        this.name = name;
    }
}
