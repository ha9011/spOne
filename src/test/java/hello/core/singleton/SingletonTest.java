package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
