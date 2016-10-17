/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.extractor;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.docworks.dctm.query.core.RowMapper;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * RowMapper结果集抽取器的实现类。
 * <p>该类实现{@link CollectionExtractor}接口，将{@link IDfCollection}结果集中
 * 的数据抽取出来，并以{@link List}方式返回。</p>
 * @author liuwei
 * @version 1.0
 */
public class RowMapperCollectionExtractor<T> implements CollectionExtractor<List<T>> {

    /** RowMapper, provided by initialize this Object */
    private final RowMapper<T> rowMapper;

    /**
     * 初始化该类必须提供一个{@link RowMapper}对象
     * @param rowMapper {@link RowMapper}对象
     */
    public RowMapperCollectionExtractor(RowMapper<T> rowMapper) {
        Validate.notNull(rowMapper, "Argument 'rowMapper' cannot be null.");
        this.rowMapper = rowMapper;
    }

    @Override
    public List<T> extractData(IDfCollection coll) throws DfException {
        Validate.notNull(coll, "Argument 'coll' cannot be null.");

        List<T> results = new ArrayList<T>();
        int rowNum = 0;
        while (coll.next()) {
            results.add(this.rowMapper.mapRow(coll, rowNum++));
        }
        return results;
    }
}
