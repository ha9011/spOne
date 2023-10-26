package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정정보, 구성정보
public class AppConfig2 {

    @Bean // 스프링 컨테이너에 등록이됨
    public MemberService memberService2(){
        return new MemberServiceImpl(memberRepository2());
    }


    @Bean
    public OrderService orderService2(){
        return new OrderServiceImpl(memberRepository2(), discountPolicy2());
    }

    @Bean
    private static DiscountPolicy discountPolicy2() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    private static MemberRepository memberRepository2() {
        return new MemoryMemberRepository();
    }
}
