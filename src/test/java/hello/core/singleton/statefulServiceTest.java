package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class statefulServiceTest {

    @Test
    @DisplayName("")
    public void statefulServiceSingleton() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // threadA : A 유저가 10000원 주문
        statefulService1.order("userA", 100000);

        // threadB : B 유저가 20000원 주문
        statefulService2.order("userB", 200000);

        //when
        int price = statefulService1.getPrice();
        System.out.println("userA : " + price);
        assertThat(price).isNotEqualTo(20000); // 변경값이 그대로 공유되서 문제가 나타난다.
    }


    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}