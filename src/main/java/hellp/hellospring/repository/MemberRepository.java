package hellp.hellospring.repository;

import hellp.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional java8 기능으로 findById, findByName 가져오는데 없으면 Null이 반환되는데
    // Null 처리하는 방법에서 Optional 감쏴서 반환

    List<Member> findAll();

}
