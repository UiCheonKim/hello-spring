package hellp.hellospring.repository;

import hellp.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // spring data jpa가 JpaRepository를 받고 있으면 SpringDataJpaMemberRepository가 구현체를 자동으로 만들어준다.
    // 스프링 빈에 자동으로 등록


    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

}
