/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.dctm.query.core;

import com.documentum.fc.client.IDfSession;

import java.util.List;

/**
 * 申明一组Document Query操作的基本操作接口。
 * 该接口的默认实现类由{@link DctmTemplate}提供，通常该接口不需要直接使用，
 * 你可以扩展该接口的功能，或者是提供其他的实现类。
 * @author liuwei
 * @version 1.0
 */
public interface DctmOperations {

    /**
     * 执行指定的DQL语句，使用一个{@link RowMapper}对象映射结果集。
     * @param dql DQL语句
     * @param dfSession {@link IDfSession}对象
     * @param rowMapper {@link RowMapper}接口的实现类
     * @param <T> 指定的泛型对象
     * @return 指定对象的结果集
     */
    <T> List<T> queryForList(String dql, IDfSession dfSession, RowMapper<T> rowMapper);

    /**
     * 执行指定的DQL语句，使用一个{@link RowMapper}对象映射结果。
     * <p>该方法用于处理查询单个对象结果的映射。</p>
     * @see #queryForList(String, IDfSession, RowMapper)
     * @param dql DQL语句
     * @param dfSession {@link IDfSession}对象
     * @param rowMapper {@link RowMapper}接口的实现类
     * @param <T> 指定的泛型对象
     * @return 指定对象
     */
    <T> T queryForObject(String dql, IDfSession dfSession, RowMapper<T> rowMapper);

    /**
     * 执行指定的DQL语句。
     * <p>该方法用于处理DQL查询单个Column值，需要提供一个查询的列名和一个查询结果的类型。</p>
     * @param dql DQL语句
     * @param dfSession {@link IDfSession}对象
     * @param column 指定的column名称或者别名
     * @param requiredClass 结果类型，支持Documentum的类型有:
     *                      <ul>
     *                          <li>{@link java.lang.String}</li>
     *                          <li>{@link java.lang.Boolean}</li>
     *                          <li>{@link java.lang.Integer}</li>
     *                          <li>{@link java.lang.Double}</li>
     *                          <li>{@link java.lang.Long}</li>
     *                          <li>{@link java.util.Date}</li>
     *                          <li>{@link com.documentum.fc.common.DfId}</li>
     *                      </ul>
     *
     * @return 指定column的值
     */
    <T> T queryForObject(String dql, IDfSession dfSession, String column, Class<T> requiredClass);
}
