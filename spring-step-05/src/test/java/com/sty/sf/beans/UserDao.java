package com.sty.sf.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("100001", "西游记");
        map.put("100002", "水浒传");
        map.put("100003", "三国演义");
        map.put("100004", "红楼梦");
    }

    public String getName(String pid) {
        return map.get(pid);
    }
}
