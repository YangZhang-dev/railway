package com.zzys.railway.biz.payservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 支付服务启动类
 *
 * @author YangZhang
 * @createTime 2023/10/25/ 18:53
 */
@SpringBootApplication
@MapperScan("com.zzys.railway.biz.payservice.dao.mapper")
@EnableFeignClients("com.zzys.railway.biz.payservice.remote")
@EnableRetry
public class PayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }
}
