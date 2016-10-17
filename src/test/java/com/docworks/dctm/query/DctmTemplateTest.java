/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;
import com.docworks.dctm.query.core.DctmTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
public class DctmTemplateTest {

    public static final String DF_USER = "liuwei";
    public static final String DF_PASSWORD = "123456";
    public static final String DF_REPO = "DMS";

    private IDfSession dfSession;

    private DctmTemplate dctmTemplate = new DctmTemplate();

    @BeforeTest
    public void initSessionMgr() throws DfException {
        IDfClientX dfClientX = new DfClientX();
        IDfClient dfClient = dfClientX.getLocalClient();

        IDfSessionManager sessionMgr = dfClient.newSessionManager();

        IDfLoginInfo loginInfo = dfClientX.getLoginInfo();
        loginInfo.setUser(DF_USER);
        loginInfo.setPassword(DF_PASSWORD);

        sessionMgr.setIdentity(DF_REPO, loginInfo);

        dfSession = sessionMgr.getSession(DF_REPO);
    }

    @Test
    public void queryForListTest() {
        String strDql = "SELECT r_object_id, user_name, user_address FROM dm_user";
        List<User> users = dctmTemplate.queryForList(strDql, dfSession, new UserMapper());

        Assert.assertNotNull(users, "Query result 'users' is null.");
        users.forEach(System.out::println);
    }

    @Test
    public void queryForSingleObjectTest() {
        String strDql = "SELECT r_object_id, user_name, user_address FROM dm_user WHERE user_name='dm_bof_registry'";
        User user = dctmTemplate.queryForObject(strDql, dfSession, new UserMapper());

        Assert.assertNotNull(user, "Query result 'user' is null.");
        System.out.println(user);
    }

    @Test
    public void queryForSingleColumnTest() {
        String strDql = "SELECT COUNT(*) AS dw_cnt FROM dm_user";
        int userCnt = dctmTemplate.queryForObject(strDql, dfSession, "dw_cnt", Integer.class);
        System.out.println(userCnt);
    }

    @AfterTest
    public void releaseSession() {
        if (dfSession != null) {
            IDfSessionManager sessionMgr = dfSession.getSessionManager();
            sessionMgr.release(dfSession);
        }
    }
}
