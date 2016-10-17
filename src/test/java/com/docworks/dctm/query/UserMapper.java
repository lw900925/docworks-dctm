package com.docworks.dctm.query;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.docworks.dctm.query.core.RowMapper;

/**
 * Created by liuwei on 2016/10/17.
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(IDfCollection coll, int rowNum) throws DfException {
        User user = new User();
        user.setObjectId(coll.getString("r_object_id"));
        user.setUserName(coll.getString("user_name"));
        user.setUserAddress(coll.getString("user_address"));
        return user;
    }
}
