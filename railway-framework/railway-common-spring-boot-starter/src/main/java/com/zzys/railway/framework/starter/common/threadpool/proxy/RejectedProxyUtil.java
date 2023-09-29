package com.zzys.railway.framework.starter.common.threadpool.proxy;

import com.zzys.railway.framework.starter.common.threadpool.build.EagerThreadPoolBuilder;
import com.zzys.railway.framework.starter.common.threadpool.build.ThreadFactoryBuilder;
import com.zzys.railway.framework.starter.common.threadpool.support.eager.EagerThreadPoolExecutor;
import com.zzys.railway.framework.starter.common.threadpool.support.eager.TaskQueue;
import com.zzys.railway.framework.starter.common.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 拒绝策略工具
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 12:42
 */
@Slf4j
public class RejectedProxyUtil {
    /**
     * 创建拒绝策略代理类
     *
     * @param rejectedExecutionHandler 真正的线程池拒绝策略执行器
     * @param rejectedNum              拒绝策略执行统计器
     * @return 代理拒绝策略
     */
    public static RejectedExecutionHandler createProxy(RejectedExecutionHandler rejectedExecutionHandler, AtomicLong rejectedNum) {
        // 动态代理模式: 增强线程池拒绝策略，比如：拒绝任务报警或加入延迟队列重复放入等逻辑
        return (RejectedExecutionHandler) Proxy
                .newProxyInstance(
                        rejectedExecutionHandler.getClass().getClassLoader(),
                        new Class[]{RejectedExecutionHandler.class},
                        new RejectedProxyInvocationHandler(rejectedExecutionHandler, rejectedNum));
    }

    /**
     * 测试线程池拒绝策略动态代理程序
     */
    public static void main(String[] args) {
        // ----------------------- 测试快速消费线程池拒绝策略动态代理程序 -----------------------
        ThreadFactory factory = ThreadFactoryBuilder.builder()
                .prefix("zzys-")
                .build();
        TaskQueue<Runnable> q = new TaskQueue<>(1);
        EagerThreadPoolExecutor threadPoolExecutor = EagerThreadPoolBuilder.builder()
                .corePoolSize(1)
                .maximumPoolSize(3)
                .keepAliveTime(1024,TimeUnit.SECONDS)
                .workQueue(q)
                .threadFactory(factory)
                .build();
        q.setExecutor(threadPoolExecutor);
        // ----------------------- 测试普通线程池拒绝策略动态代理程序 -----------------------
//        ThreadPoolExecutor threadPoolExecutor =
//                new ThreadPoolExecutor(1, 3, 1024, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        // ---------------------------------------------------------------------------
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        AtomicLong rejectedNum = new AtomicLong();
        // 创建拒绝策略代理类, 代理普通的抛出异常策略
        RejectedExecutionHandler proxyRejectedExecutionHandler = RejectedProxyUtil.createProxy(abortPolicy, rejectedNum);
        threadPoolExecutor.setRejectedExecutionHandler(proxyRejectedExecutionHandler);

        for (int i = 0; i < 5; i++) {
            try {
                threadPoolExecutor.execute(() -> ThreadUtil.sleep(100000L));
                log.info("线程池中线程数目：{}，队列中等待执行的任务数目：{}",
                        threadPoolExecutor.getPoolSize(), threadPoolExecutor.getQueue().size());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        System.out.println("================ 线程池拒绝策略执行次数: " + rejectedNum.get());
    }
}
