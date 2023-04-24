package hellp.hellospring.repository;

import hellp.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    // JPA는 EntityManager로 모든게 동작
    // spring boot가 application.properties 입력데이터 및 데이터베이스 연결까지 다된 EntityManager라는 걸 자동으로 생성
    // 만들어진걸 injection 받으면 됨

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// 조회할 타입, 식별값(pk)
        return Optional.ofNullable(member);
    }

    /*
            pk 외에는 jpql를 작성해줘야한다.
     */

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        // Member.class로 조회
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
        // as 생략
        // jpql 쿼리 언어, 테이블 대상으로 쿼리를 날리는게 아니라 객체를 대상으로 날림
        // sql 번역
        // 정확히는 Entity를 대상으로 쿼리를 날림 멤버 엔티티를 조회해
        // select m -> entity자체를 select한다.
    }
}
