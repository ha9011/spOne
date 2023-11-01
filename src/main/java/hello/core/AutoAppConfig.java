package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  Configuration.class)
)// @Component를 모두 찾아 자동으로 스프링빈으로 등록
public class AutoAppConfig {

}
