package com.zzys.railway.framework.starter.common.threadpool.support.eager;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快速消费线程池
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 12:12
 */
public class EagerThreadPoolExecutor extends ThreadPoolExecutor {


    // 记录正在工作的任务数
    private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

    public EagerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, TaskQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        workQueue.setExecutor(this);
    }

    // 获取正在工作的任务数
    public int getSubmittedTaskCount() {
        return submittedTaskCount.get();
    }
    // 任务执行完成后，将正在工作的任务数减一
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        submittedTaskCount.decrementAndGet();
    }
    @Override
    public void execute(Runnable command) {
        submittedTaskCount.incrementAndGet();
        try {
            super.execute(command);
        }catch (RejectedExecutionException rex) {
            // 如果触发了拒绝策略，在执行拒绝策略之前，再尝试将任务放入队列一次
            TaskQueue queue = (TaskQueue) getQueue();
            try {
                if (!queue.retryOffer(command, 0, TimeUnit.MILLISECONDS)) {
                    submittedTaskCount.decrementAndGet();
                    throw new RejectedExecutionException("Queue capacity is full.", rex);
                }
            } catch (InterruptedException iex) {
                submittedTaskCount.decrementAndGet();
                throw new RejectedExecutionException(iex);
            }
        }
        catch (Exception ex) {
            submittedTaskCount.decrementAndGet();
            throw ex;
        }
    }
}
