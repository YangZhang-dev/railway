package com.zzys.railway.framework.starter.common.threadpool.build;

import com.zzys.railway.framework.starter.common.threadpool.support.eager.EagerThreadPoolExecutor;
import com.zzys.railway.framework.starter.common.threadpool.support.eager.TaskQueue;

import java.math.BigDecimal;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.zzys.framework.starter.designpattern.builder.Builder;
import com.zzys.railway.framework.starter.common.utils.Assert;

/**
 * 快速消费线程池构建者
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 12:36
 */
public class EagerThreadPoolBuilder implements Builder<EagerThreadPoolExecutor> {

    private int corePoolSize = calculateCoreNum();

    private int maximumPoolSize = corePoolSize + (corePoolSize >> 1);

    private long keepAliveTime = 30000L;

    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    private TaskQueue workQueue = new TaskQueue(4096);

    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    private boolean isDaemon = false;

    private String threadNamePrefix;

    private ThreadFactory threadFactory;

    private Integer calculateCoreNum() {
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        return new BigDecimal(cpuCoreNum).divide(new BigDecimal("0.2")).intValue();
    }

    public EagerThreadPoolBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public EagerThreadPoolBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public EagerThreadPoolBuilder maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        if (maximumPoolSize < this.corePoolSize) {
            this.corePoolSize = maximumPoolSize;
        }
        return this;
    }

    public EagerThreadPoolBuilder threadFactory(String threadNamePrefix, Boolean isDaemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.isDaemon = isDaemon;
        return this;
    }

    public EagerThreadPoolBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public EagerThreadPoolBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        return this;
    }

    public EagerThreadPoolBuilder rejected(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }

    public EagerThreadPoolBuilder workQueue(TaskQueue workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public static EagerThreadPoolBuilder builder() {
        return new EagerThreadPoolBuilder();
    }

    @Override
    public EagerThreadPoolExecutor build() {
        if (threadFactory == null) {
            Assert.notEmpty(threadNamePrefix, "The thread name prefix cannot be empty or an empty string.");
            threadFactory = ThreadFactoryBuilder.builder().prefix(threadNamePrefix).daemon(isDaemon).build();
        }
        EagerThreadPoolExecutor executorService;
        try {
            executorService = new EagerThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    timeUnit,
                    workQueue,
                    threadFactory,
                    rejectedExecutionHandler);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Error creating thread pool parameter.", ex);
        }
        return executorService;
    }
}
