package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
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
public class AppConfig {
    // MemberService -> new memberRepository()
    // OrderService ->  new memberRepository()
    // 둘다 빈으로 싱글톤을 이용하는게 아니라, 각각 인스턴스화를 한다.  즉 싱글톤을 깨져보인다.

    //AppConfig.memberService
    //AppConfig.memberRepository
    //AppConfig.orderService
    //AppConfig.memberRepository
    //AppConfig.memberRepository

    //==>
    // 실제 호출
    //AppConfig.memberRepository
    //AppConfig.memberService
    //AppConfig.orderService
    @Bean // 스프링 컨테이너에 등록이됨
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");

        return new MemberServiceImpl(memberRepository());
    }


    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public  DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean // 왜 private static 하면 객체가 다르게 될까.
    public  MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
}
