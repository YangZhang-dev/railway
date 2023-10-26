package com.zzys.railway.biz.orderservice;

import cn.crane4j.spring.boot.annotation.EnableCrane4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单启动类
 *
 * @author YangZhang
 * @createTime 2023/10/20/ 20:47
 */
@SpringBootApplication
@MapperScan("com.zzys.railway.biz.orderservice.dao.mapper")
@EnableFeignClients("com.zzys.railway.biz.orderservice.remote")
@EnableCrane4j(enumPackages = "com.zzys.railway.biz.orderservice.common.enums")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
