package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//restAPI, string 반환시 그냥 string값 반환됨

@Slf4j
@RestController
public class LogTestController {
    //롬복의 @Slf4j 선언하면 작성 안해도됨
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){

        String name = "Spring";

        //단순 메세지 출력
        System.out.println("name=" + name);

        //콘솔에 HTTP요청시 실행한 쓰레드, 컨트롤러이름, 메세지 출력
        //로그 출력시 레벨 설정가능
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //디버그(개발서버용)
        log.info("info log={}", name); //중요정보
        log.warn("warn log={}", name); //경고
        log.error("error log={}", name); //에러
        //로그는 콘솔말고 파일에 남길수도 있음

        // +로 사용하면 안됨. info는 사용해서 괜찮지만 출력안될 trace의경우 자바는 +를 먼저실행으로 연산이 일어남. 메모리와 CPU사용함.
        log.trace("info log={}" + name);

        return "ok";
    }
}
