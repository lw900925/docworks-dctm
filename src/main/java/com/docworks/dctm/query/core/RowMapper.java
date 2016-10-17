/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.core;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;

/**
 * RowMapper接口，被用于{@link DctmTemplate}类中处理{@link IDfCollection}结果集
 * 映射成特定对象。
 * <p>该接口的实现类由使用者提供，根据实际使用的映射关系进行{@link IDfCollection}对象与
 * {@link T}对象之间的转换。</p>
 * @author liuwei
 * @version 1.0
 */
public interface RowMapper<T> {

    /**
     * 将{@link IDfCollection}对象转换成{@link T}对象
     * @param coll {@link IDfCollection}对象
     * @param rowNum 行号
     * @return 转换完成后的对象对象
     * @throws DfException
     */
    T mapRow(IDfCollection coll, int rowNum) throws DfException;
}
