package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationConfigApplicationContext {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회")
    public void findBeanByName() throws Exception{
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("빈이름으로 조회 -> 없을 경우")
    public void findBeanByNameX() throws Exception{
        assertThatThrownBy(() -> ac.getBean("123", MemberService.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class)
                .hasMessage("No bean named '123' available");
    }

    @Test
    @DisplayName("이름없이 타입으로 조회")
    public void findBeanByType() throws Exception{
        //given
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("구체 타입으로 조회")
    public void findBeanByType1() throws Exception{
        //given
        MemberService memberService = ac.getBean( MemberServiceImpl.class); // 구체 타입으로 조회하는 건 좋치 않다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
