/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.extractor;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;

/**
 * DFC IDfCollection对象结果集抽取器
 * @author liuwei
 * @version 1.0
 */
public interface CollectionExtractor<T> {

    /**
     * 抽取{@link IDfCollection}结果集中的数据，并以指定的对象返回。
     * @param coll {@link IDfCollection}对象
     * @return 指定对象的结果
     * @throws DfException
     */
    T extractData(IDfCollection coll) throws DfException;
}
