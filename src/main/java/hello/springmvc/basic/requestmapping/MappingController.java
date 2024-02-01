package hello.springmvc.basic.requestmapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping({"/hello-basic", "/hello-go"})
    public String HelloBasic() {
        log.info("helloBasic");
        return "ok";
    }


    /*
    최근 HTTP API는 리소스 경로에 식별자 넣는 방식을 선호한다.
    PathVariable 사용
    변수명이 같으면 생략 가능

    RequestMapping은 URL 경로를 템플릿화 할 수 있는데 ex) @GetMapping("/mapping/{userId}")
    @PathVariable 사용하면 매칭되는 부분을 편리하게 조회할 수 있다.

    @PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다.
    @PathVariable("userId") String userId -> @PathVariable userId
     */

//    @GetMapping("/mapping/{userId}")
//    public String mappingPath(@PathVariable String userId) {
//        log.info("mappingPath userId={}" + userId);
//        return "ok";
//    }

    // 변수명이랑 다르게 쓰고 싶으면 이렇게 쓰면 된다.
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}" + data);
        return "ok";
    }


    /**
     * PathVariable 사용 다중
     *
    */

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }


    /**
     * URL경로 뿐만 아니라
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }


    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }


    // 미디어 타입 조건 매핑 - HTTP 요청헤더의 Content-Type, consume
    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     *
     * consumes = "text/plain"
     * consumes = {"text/plain", "application/*"}
     * consumes = MediaType.TEXT_PLAIN_VALUE
     */

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }


    // 요청헤더릐 Accept 기반으로 매핑
    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
