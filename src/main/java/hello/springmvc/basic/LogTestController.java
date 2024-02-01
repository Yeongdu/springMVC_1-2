package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 그냥 @Controller는 view를 찾고 렌더링 되는데 @RestController는 반환값으로 view를 찾는게 아니라 http 메세지 바디에 바로 입력한다.
public class LogTestController {

    // @Slf4j 쓰면 이거 안써도 된다.
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name: " + name);

        log.trace("trace log={}" + name);
        log.debug("debug log={}" + name);
        log.info("info log={}" + name);
        log.warn("warn log={}" + name);
        log.error("error log={}" + name);

        return "ok";
    }
}
