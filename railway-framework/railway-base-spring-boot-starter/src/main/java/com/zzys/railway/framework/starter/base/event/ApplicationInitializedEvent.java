package com.zzys.railway.framework.starter.base.event;

import org.springframework.context.ApplicationEvent;

/**
 * 程序初始化完成事件
 *
 * @author YangZhang
 * @createTime 2023/09/16/ 21:17
 */
public class ApplicationInitializedEvent extends ApplicationEvent {
    public ApplicationInitializedEvent(Object source) {
        super(source);
    }
}
