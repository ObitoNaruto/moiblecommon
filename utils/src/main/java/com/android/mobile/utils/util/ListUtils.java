package com.android.mobile.utils.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class ListUtils {
    /**
     * 判断List是否为空
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        //if list is null or its size is 0, return true, else return false
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * 返回列表中元素个数
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> int getSize(List<V> sourceList) {
        //if list is null or empty, return 0, return List.size().
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * 比较两个list是否相等
     * @param actual
     * @param expected
     * @param <V>
     * @return
     */
    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }
        if (expected == null) {
            return false;
        }
        if (actual.size() != expected.size()) {
            return false;
        }

        for (int i = 0; i < actual.size(); i++) {
            if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将列表list内容转换成字符串String, 字符串中元素用分隔符隔开
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * @param list
     * @param separator
     * @return  join list to string with separator ,if list is empty, return ""
     */
    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    /**
     * 向列表中添加一个新数据（列表之前没有的）
     * @param sourceList
     * @param entry
     * @param <V>
     * @return if entry already exist in sourceList, return false, else add it and return true
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
    }

    /**向列表中添加一个非空的数据
     *
     * @param sourceList
     * @param value
     * @param <V>
     * @return
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList != null && value != null) ? sourceList.add(value) : false;
    }

    /**
     * 合并两个list（做交集）
     * @param sourceList
     * @param entryList
     * @param <V>
     * @return the count of entries be added
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    /**
     * 移除list中重复的元素
     * @param sourceList
     * @param <V>
     * @return the count of entries be removed
     */
    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
            }
        }
        return sourceCount - sourceList.size();
    }

    /**
     * 反转list
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }

}
