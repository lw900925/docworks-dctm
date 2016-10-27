/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.core;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.docworks.dctm.query.extractor.CollectionExtractor;
import com.docworks.dctm.query.extractor.RowMapperCollectionExtractor;
import com.docworks.dctm.query.extractor.SingleColumnCollectionExtractor;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * {@link DctmOperations}接口的核心实现类，提供DFC查询数据的公共方法。
 * @author liuwei
 * @version 1.0
 */
public class DctmTemplate implements DctmOperations {

    private static final Logger logger = Logger.getLogger(DctmTemplate.class);

    private boolean showDql = false;

    /**
     * 是否显示执行的DQL语句
     * @param showDql true or false
     */
    public void setShowDql(boolean showDql) {
        this.showDql = showDql;
    }

    /** IDfClientX */
    private static final IDfClientX dfClientX = new DfClientX();

    /**
     * 执行指定的DQL查询语句，并使用{@link CollectionExtractor}对象处理查询的结果。
     * @param dql DQL语句
     * @param dfSession {@link IDfSession}对象
     * @param collExtractor {@link CollectionExtractor}对象或实现类
     * @param <T> 指定的泛型对象
     * @return
     */
    private <T> T query(String dql, IDfSession dfSession, CollectionExtractor<T> collExtractor) {
        Validate.notBlank(dql, "Argument 'dql' cannot be blank.");
        Validate.notNull(dfSession, "Argument 'dfSession' cannot be null.");
        Validate.notNull(collExtractor, "Argument 'collExtractor' cannot be null.");

        // 打印DQL语句
        if (showDql) {
            System.out.println(dql);
        }

        class SimpleQueryCallback implements QueryCallback<T> {

            @Override
            public T doInQuery() {
                IDfCollection coll = null;

                IDfQuery query = dfClientX.getQuery();
                query.setDQL(dql);
                try {
                    coll = query.execute(dfSession, IDfQuery.READ_QUERY);

                    return collExtractor.extractData(coll);
                } catch (DfException e) {
                    logger.error(e.getMessage(), e);
                    return null;
                } finally {
                    try {
                        if (coll != null) {
                            coll.close();
                        }
                    } catch (DfException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }

        return new SimpleQueryCallback().doInQuery();

    }

    @Override
    public <T> List<T> queryForList(String dql, IDfSession dfSession, RowMapper<T> rowMapper) {
        return query(dql, dfSession, new RowMapperCollectionExtractor<T>(rowMapper));
    }

    @Override
    public <T> T queryForObject(String dql, IDfSession dfSession, RowMapper<T> rowMapper) {
        T object = null;

        List<T> result = queryForList(dql, dfSession, rowMapper);
        if (result != null && result.size() > 0) {
            object = result.get(0);
        }
        return object;
    }

    @Override
    public <T> T queryForObject(String dql, IDfSession dfSession, String column, Class<T> requiredClass) {
        return query(dql, dfSession, new SingleColumnCollectionExtractor<T>(column, requiredClass));
    }
}
