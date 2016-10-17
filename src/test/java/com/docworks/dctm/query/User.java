/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query;

/**
 * @author liuwei
 * @version 1.0
 */
public class User {
    private String objectId;
    private String userName;
    private String userAddress;

    public User() {
        super();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "objectId='" + objectId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
