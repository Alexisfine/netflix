package com.alex.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Web Log for controller
 */
@Data
@EqualsAndHashCode
public class WebLog {
    private String description;

    private String username;

    private Long startTime;

    private Integer spendTime;

    private String basePath;

    private String uri;

    private String url;

    private String method;

    private String ip;

    private Object parameter;

    private Object result;
}