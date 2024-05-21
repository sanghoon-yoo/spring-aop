package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import hello.aop.exam.aop.RetryAcpect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Slf4j
//@Import(TraceAspect.class)
@Import({TraceAspect.class, RetryAcpect.class})
@SpringBootTest
@Service
public class ExamTest {
    @Autowired
    ExamService examService;

    @Test
    @Trace
    void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ExamService examTest = (ExamService) applicationContext.getBean("examTest");
        System.out.println(examTest.getClass());
        for (int i = 0; i < 4; i++) {
            log.info("client request i={}", i);
            examService.request("data" + i);
        }
    }
}
