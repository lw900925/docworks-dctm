/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.core;

/**
 * DFC查询处理的回调接口。
 * @author liuwei
 * @version 1.0
 */
public interface QueryCallback<T> {

    /**
     * 查询处理回调方法
     * @return
     */
    T doInQuery();
}
