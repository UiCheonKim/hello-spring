package hellp.hellospring.domain;

import jakarta.persistence.*;

@Entity
// -> JPA는 객체와 ORM(Object, Relational, Mapping) 기술
// -> 객체 오브젝트와 릴레이션 데이터베이스 테이블을 매핑하는 기술
// 매핑을 @Entity로 한다 jpa가 관리하는 Entity가 되는것
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB에 값을 넣으면 DB가 자동으로 생성해주는 기술을 Identity 기술
    private Long id;    // 식별자
    @Column(name = "username")
    //DB에 있는 colume명과 매핑
    private String name; // 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
