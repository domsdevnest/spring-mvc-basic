package com.domsdevnest.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); //@Slf4J

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name); //로그 레벨과 상관없이 모두 출력

        log.trace("trace log = {}", name);
        log.debug("debug log = {}",name);
        log.info("info Log = {}",name);
        log.warn("warn log= {}", name);
        log.error("error log= {}",name);

        return "ok";
    }
}
