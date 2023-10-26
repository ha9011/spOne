package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 ")
    public void pureContainer() throws Exception{
        //given
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        //when
        /* 참조값이 다름
        * memberservice 1 : hello.core.member.MemberServiceImpl@793f29ff
          memberservice 2 : hello.core.member.MemberServiceImpl@74d1dc36
         * * */
        System.out.println("memberservice 1 : " + memberService1);
        System.out.println("memberservice 2 : " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() throws Exception{
        //given

        // 프라이벗 생성자로 만들었기 떄문에, 인스턴스화 불가
        //SingletonService singletonService = new SingletonService();

        // 싱글톤 패턴으로 생성했기에 상대주소가 같다.
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 : " + instance1);
        System.out.println("instance2 : " + instance2);

        assertThat(instance1).isSameAs(instance2);
        // isSameAs : ==
        // isEqualTo : equal

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() throws Exception{
        //given
        //AppConfig appConfig = new AppConfig(); <- 스프링으로 바꾸기 (아래 코드)
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        /* 상위 pureContainer TEXT와 비교해볼것 */

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //when
        /* 참조값이 값이 같음
        * memberservice 1 : hello.core.member.MemberServiceImpl@793f29ff
          memberservice 2 : hello.core.member.MemberServiceImpl@74d1dc36
         * * */
        System.out.println("memberservice 1 : " + memberService1);
        System.out.println("memberservice 2 : " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }
}
