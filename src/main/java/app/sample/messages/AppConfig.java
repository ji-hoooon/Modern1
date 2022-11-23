package app.sample.messages;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//빈을 정의하는 설정파일이라는 어노테이션
@Configuration
//어노테이션을 스캔할 패키지 알려주는 어노테이션
@ComponentScan("app.sample.messages")
public class AppConfig {
//    //빈 어노테이션을 메소드에 추가함으로써 빈 생성 -> 메소드의 이름이 빈이름
//    @Bean
//    public MessageRepository messageRepository(){
//        return new MessageRepository();
//    }
//
//    @Bean
//    MessageService messageService(){
//        //리포지토리 인스턴스를 서비스 생성자에 전달
//        return new MessageService(messageRepository());
//    }

    //필터 등록하는 메서드
    @Bean
    //빈 등록을 통해 AuditingFilter에 대한 FilterRegistrationBean 생성
    public FilterRegistrationBean<AuditingFilter> auditingFilterRegistrationBean() {
        //필터 등록 과정
        // 1. Filter 인스턴스 생성
        FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<>();

        // 2. setFilter() 메소드로 Filter 설정
        AuditingFilter filter = new AuditingFilter();
        registration.setFilter(filter);

        // 3. setOrder() 메소드로 Filter를 체인 내에 배치 -> 순서는 값 오름차순로
        // -> AuditingFilter는 체인의 마지막에 위치
        registration.setOrder(Integer.MAX_VALUE);

        // 4. setUrlPatterns() 메소드로 Filter를 등록할 경로 지정 -> /messages/의 모든 요청 처리
        registration.setUrlPatterns(Arrays.asList("/messages/*"));

        return registration;

        //-> 출력 결과에 디버그 로그를 표시하기 위해 application.properties에 디버그 레벨 로그 설정
    }
}
