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

    @Test
    public void configurationDeep() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // <-AppConfig.class 얘도 같이 등록이됨

        AppConfig bean = ac.getBean(AppConfig.class);


        System.out.println("bean = " + bean.getClass());

        // -> 순수한 클레스라면 bean = class hello.core.AppConfig 이렇게만 나와야한다.
        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$d4b41347
        // => 위 처럼 바이트코드를 조작한 다른 클래스가 임의로 생성되며, 해당 클래스를 대신한다.
        // 변경된 클래스는 매우 복잡한 코드이며, 간단하게는
        // 이미 컨테이너에 등록 빈이 있다면, 빈을 호출하고, 없다면 새로 생성해서 호출한다
        // 이러한 소스 때문에 인스턴스화를 계속 하지않고 계속 같은 싱글톤 대로 호출하게 된다.

        // @Configuration 을 안붙이면 빈은 등록되지만 싱글톤은 되지 않는다.
    }
}
