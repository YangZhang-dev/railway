package com.zzys.framework.starter.convention.page;

import lombok.Data;

/**
 * 分页请求
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 21:12
 */
@Data
public class PageRequest {

    /**
     * 当前页
     */
    private Long current = 1L;

    /**
     * 每页显示条数
     */
    private Long size = 10L;
}
