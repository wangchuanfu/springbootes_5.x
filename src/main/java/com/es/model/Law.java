package com.es.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Law implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String date;

    private String url;

    private String name;
    // 1 法律 2 行政法规 3 国务院发布的规范性文件 4 部门规章 5 部门发布的规范性文件
    private Integer type;

}
