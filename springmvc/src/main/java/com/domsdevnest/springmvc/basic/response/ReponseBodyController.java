package com.domsdevnest.springmvc.basic.response;

import com.domsdevnest.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody 모든 메소드에 적용
@RestController //@Controller , @ResponseBody 포함
public class ReponseBodyController {

    /**
     * 기본 서블릿 방식
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * ResponseEntity로 편리하게 사용, 응답 코드 설정 가능
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBody2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * HTTP 메세지 컨버터를 통해 HTTP 메세지를 직접 입력
     */
//    @ResponseBody
    @GetMapping("/response-body-string v3")
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * HTTP 메세지 컨버터를 통해 JSON 형식으로 변환되서 반환, 응답 코드 설정 가능
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용하면 응답코드 설정이 불가하여 @ResponseStatus를 이용해 응답 코드 설정
     * 응답 코드를 동적으로 적용은 불가 -> 이 경우 ResponseEntity를 이용
     */
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(29);

        return helloData;
    }


}
