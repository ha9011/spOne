package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConfigurationSingletonTest {

    @Test
    public void configurationTest() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderServiece = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository_m = memberService.getMemberRepository();
        MemberRepository memberRepository_o = orderServiece.getMemberRepository();

        System.out.println("memberRepository_m : " + memberRepository_m);
        System.out.println("memberRepository_o : " + memberRepository_o);
        System.out.println("memberRepository : " + memberRepository);

        // MemberService -> new memberRepository()
        // OrderService ->  new memberRepository()
        // new memberRepository()
        // 둘다 빈으로 싱글톤을 이용하는게 아니라, 각각 인스턴스화를 한다.  즉 싱글톤을 깨져보인다.
        // 근데도 상대주소가 같다??? 왜 그럴까??

        //when
        assertThat(memberRepository_m).isSameAs(memberRepository_o);

        //then
    }
}
