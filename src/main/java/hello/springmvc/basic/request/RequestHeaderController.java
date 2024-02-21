package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j // 로그 찍을수있음
@RestController // 응답값을 view를 찾는게 아니라 문자 그대로 HTTP응답에 넣어서 반환. 테스트할땐 view 쓸 필요없기 때문
public class RequestHeaderController {

    // 애노테이션 기반의 컨트롤러는 다양한 파라미터를 받아들일 수 있다.
    @RequestMapping("/headers")
    public String header(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpMethod httpMethod,
                         Locale locale,
                         @RequestHeader MultiValueMap<String, String> headerMap, // header 한번에 다 받고 싶을 때
                         @RequestHeader("host") String host, // 하나 받고싶을 때
                         @CookieValue(value = "mycookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
