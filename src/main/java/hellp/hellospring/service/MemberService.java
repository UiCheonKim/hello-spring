package hellp.hellospring.service;

import hellp.hellospring.domain.Member;
import hellp.hellospring.repository.MemberRepository;
import hellp.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *     서비스 - 회원 repository와 domain을 활용해서 실제 비즈니스 로직을 작성함
 *     서비스는 비즈니스적 용어도 쓰고 repository은 기계적 느낌 개발스럽게
 */
//@Service
@Transactional
public class MemberService {

    // 회원 서비스를 만들려면 회원 repository가 있어야함
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        // 같은 이름이 있는 중복 회원X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        // result.orElseGet() -> (?)
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        */
        try {
            validateDuplicateMember(member); // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMS = finish - start;
            System.out.println("join = " + timeMS + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원 입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
