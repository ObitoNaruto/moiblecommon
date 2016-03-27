package com.android.mobile.utils.util;

import java.util.Map;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class MapUtils {
    /**
     * �ж�map�Ƿ�Ϊ��
     * @param sourceMap
     * @param <K>
     * @param <V>
     * @return if map is null or its size is 0, return true, else return false.
     */
    public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return (sourceMap == null || sourceMap.size() == 0);
    }

    /**
     * ��map�в���key��Ϊ�յ�key-value
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * ��map�в���key��value�㲻Ϊ�յ�key-value
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }

        map.put(key, value);
        return true;
    }


}
