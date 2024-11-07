package org.example;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer { // 톰캣 실행

    private static Logger log = LoggerFactory.getLogger(WebApplicationServer.class);
    // log 띄어주는 역할

    public static void main(String[] args) throws LifecycleException {
        String WebDisLocation = "webapps/";

        Tomcat tomcat = new Tomcat();
        // 톰캣 객체 생성

        tomcat.setPort(8080);
        // 톰캣 포트 설정

        tomcat.addWebapp("/", new File(WebDisLocation).getAbsolutePath());
        // 톰캣에 경로 추가

        log.info("configuring apps with started [{} ] : ", new File("./",WebDisLocation).getAbsolutePath());
        // start log 메서지 띄우기

        // 톰캣 시작 및 대기
        tomcat.start();
        tomcat.getServer().await();
    }
}