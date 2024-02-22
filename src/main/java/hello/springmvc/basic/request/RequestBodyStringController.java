package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    // 요청의 본문을 읽어오기 위해 InputStream 얻기
    // StreamUtils 클래스 사용하여 InputStream 에서 문자열로 변환된 요청 본문 읽어오기
    // response.getWriter().write("ok"); -> "ok" 문자열을 응답으로 반환
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody: {}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody: {}", messageBody);
        responseWriter.write("ok");
    }

    // HttpEntity : HTTP header, body 정보를 편리하게 조회
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody(); // HttpEntity에서 본문을 추출하여 문자열로 저장
        log.info("messageBody: {}", messageBody);

        return new HttpEntity<>("ok");
    }

    // @RequestBody 어노테이션은 메서드의 파라미터인 messageBody가 요청의 본문을 나타낸다는 것을 나타냄
    // Spring은 자동으로 요청 본문을 해당 파라미터에 매핑
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody: {}", messageBody);

        return "ok";
    }

}
