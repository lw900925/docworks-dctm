/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.util;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfTime;

import java.util.Date;

/**
 * @author liuwei
 * @version 1.0
 */
public class DctmUtils {

    public static Object getCollectionValue(IDfCollection coll, String column, Class<?> requiredType) throws DfException {
        Object value = null;

        if (String.class == requiredType) {
            value = coll.getString(column);
        }
        else if (boolean.class == requiredType || Boolean.class == requiredType) {
            value = coll.getBoolean(column);
        }
        else if (int.class == requiredType || Integer.class == requiredType) {
            value = coll.getInt(column);
        }
        else if (double.class == requiredType || Double.class == requiredType) {
            value = coll.getDouble(column);
        }
        else if (long.class == requiredType || Long.class == requiredType) {
            value = coll.getLong(column);
        }
        else if (Date.class == requiredType) {
            IDfTime timeValue = coll.getTime(column);
            value = ( timeValue != null ) ? timeValue.getDate() : null;
        }
        else if (DfId.class == requiredType) {
            value = coll.getId(column);
        }

        return value;
    }
}
