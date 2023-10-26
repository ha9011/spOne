package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("조회시 같은 타입이 둘 이상있으면 중복오류가 발생한다.")
    public void findBeanByTypeDuplication() throws Exception{
        //given
        assertThatThrownBy(()->ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);

    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    public void findBeanByName() throws Exception{
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    };

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    public void findAllBeanByType() throws Exception{
        //given
        Map<String, MemberRepository> beansType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansType.keySet()){
            System.out.println("key : " +  key +" , " + "value = " + beansType.get(key));
        }

        assertThat(beansType.size()).isEqualTo(2);

        //when

        //then
    }

    @Configuration
    static class SameBeanConfig{ // 스태틱 내부클래스를 만들었다는건, 기존 클래서에서만 쓰겠다는 것을 말함.
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
