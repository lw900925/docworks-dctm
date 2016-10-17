/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.extractor;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.docworks.dctm.util.DctmUtils;
import org.apache.commons.lang3.Validate;

/**
 * SingleColumn结果集抽取器的实现类。
 * <p></p>
 * @author liuwei
 * @version 1.0
 */
public class SingleColumnCollectionExtractor<T> implements CollectionExtractor<T> {

    private final String column;
    private final Class<?> requiredType;

    public SingleColumnCollectionExtractor(String column, Class<?> requiredType) {
        Validate.notBlank(column, "Argument 'column' cannot be null.");
        Validate.notNull(requiredType, "Argument 'requiredType' cannot be null.");

        this.column = column;
        this.requiredType = requiredType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T extractData(IDfCollection coll) throws DfException {
        Validate.notNull(coll, "Argument 'coll' cannot be null.");

        T value = null;
        while (coll.next()) {
            Object result = DctmUtils.getCollectionValue(coll, column, requiredType);
            if (result != null && requiredType != null && requiredType.isInstance(result)) {
                value = (T) result;
            }
        }

        return value;
    }
}
