package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

//api,
@Slf4j
@Controller
public class RequestBodyController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();

        //스트림=바이트코드, 바이트코드 받을땐 문자지정
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    //HttpServletRe..생략, InputStream, Writer 바로받기.
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /*HttpEntity
    - HTTP 헤더와 바디정보 편하게 조회.
    - 요청 파라미터 조회기능과 상관없음(get또는 폼전송등) : @RequestParam X, @ModelAttribute X
    - 응답 사용가능
    - (자동) HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
    - 메시지 바디 정보 직접 반환(view 조회X)
    */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    //HttpEntity 하위 참조 RequestEntity
//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
//
//        String messageBody = httpEntity.getBody();
//        log.info("messageBody={}", messageBody);
//
//        return new ResponseEntity<>("ok", HttpStatus.CREATED);
//    }


    //@RequestBody - HTTP메세지 바디 요청
    //@RequestHeader
    @ResponseBody //응답
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "ok";
    }

}
