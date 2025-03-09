package com.domsdevnest.springmvc.basic.request;

import com.domsdevnest.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 간단한 요청 파라미터 조회
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 이용
     * @ResponseBody를 사용하면 @RestController와 같이 viewname 직접 전달
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String RequestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
            ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * @RequestParam 변수 이름과 동일하게 매핑 가능 이용
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String RequestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam도 생략 가능 변수 이름과 동일하게 매핑 가능 이용
     * 과도한 생략도 주의가 필요
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String RequestParamV4(String username,int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam 으로 필수 값 체크
     * 주의 : 파라미터가 없는 경우 오류가 발생하나 "" 빈 값인 경우 정상 처리
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String RequestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * default 값
     * 빈 값인 경우도 default 값으로 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String RequestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * map 으로 전체 파라미터 조회 가능
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String RequestParamMap(
            @RequestParam Map<String,Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttirbute v1
     *
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /* @ModelAttribute를 사용하면 아래 생략 가능
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        */
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttirbute v2 생략
     *
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}
