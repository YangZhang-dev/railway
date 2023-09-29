package com.zzys.railway.framework.starter.web.Initialize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zzys.railway.framework.starter.web.config.WebAutoConfiguration.INITIALIZE_PATH;

/**
 * 初始化servlet
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 20:41
 */
@Slf4j(topic = "Initialize DispatcherServlet")
@RestController
public final class InitializeDispatcherServletController {

    @GetMapping(INITIALIZE_PATH)
    public void initializeDispatcherServlet() {
        log.info("Initialized the dispatcherServlet to improve the first response time of the interface...");
    }
}
