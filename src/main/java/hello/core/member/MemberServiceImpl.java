package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements  MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    //MemberServiceImpl 입장에서는 무엇으로 초기화될지 모름
    // 오로지 책임자 역할인 AppConfig이 관리

    @Autowired // 자동 주입 // ac.getBean(MemberReposutory.class)와 비슷
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // test 용도임
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
