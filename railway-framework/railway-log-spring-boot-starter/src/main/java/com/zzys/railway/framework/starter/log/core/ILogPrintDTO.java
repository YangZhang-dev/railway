package com.zzys.railway.framework.starter.log.core;

import lombok.Data;

/**
 * 日志打印实体
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 20:25
 */
@Data
public class ILogPrintDTO {

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 请求入参
     */
    private Object[] inputParams;

    /**
     * 返回参数
     */
    private Object outputParams;
}
