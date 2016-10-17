# docworks-dctm
Documentum DFC 查询的简单封装。

# 如何使用

使用源代码编译：

`mvn install -DskipTests=true`

pom.xml文件中添加：

 ```
 <dependency>
     <groupId>com.docworks</groupId>
     <artifactId>docworks-dctm</artifactId>
     <version>1.0.0</version>
 </dependency>
 ```

 ### 整合Spring框架

 在Spring的applicationContext.xml配置文件中添加如下配置项：

```
<bean id="dctmTemplate" class="com.docworks.dctm.query.core.DctmTemplate" />
```

实体类：

```
public class User {
    private String objectId;
    private String userName;
    private String userAddress;

    // 省略Getter和Setter
}
```

#### 查询结果集

```
String strDql = "SELECT r_object_id, user_name, user_address FROM dm_user";
List<User> users = dctmTemplate.queryForList(strDql, dfSession, (coll, rowNum) -> {
    User user = new User();
    user.setObjectId(coll.getString("r_object_id"));
    user.setUserName(coll.getString("user_name"));
    user.setUserAddress(coll.getString("user_address"));
    return user;
});

users.forEach(System.out::println);
```

#### 查询单个对象

```
String strDql = "SELECT r_object_id, user_name, user_address FROM dm_user WHERE user_name='dm_bof_registry'";
User user = dctmTemplate.queryForObject(strDql, dfSession, (coll, rowNum) -> {
    User user = new User();
    user.setObjectId(coll.getString("r_object_id"));
    user.setUserName(coll.getString("user_name"));
    user.setUserAddress(coll.getString("user_address"));
    return user;
});
```

#### 查询单列值

```
String strDql = "SELECT COUNT(*) AS dw_cnt FROM dm_user";
int userCnt = dctmTemplate.queryForObject(strDql, dfSession, "dw_cnt", Integer.class);
System.out.println(userCnt);
```
