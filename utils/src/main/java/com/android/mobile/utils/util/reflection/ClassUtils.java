package com.android.mobile.utils.util.reflection;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Date;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class ClassUtils {
    /**
     * �ж����Ƿ��ǻ�����������
     * Ŀǰ֧��11��
     *
     * @param clazz
     * @return
     */
    public static boolean isBaseDataType(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
                || clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Float.class)
                || clazz.equals(Double.class) || clazz.equals(Byte.class) || clazz.equals(Character.class)
                || clazz.equals(Short.class) || clazz.equals(Date.class) || clazz.equals(byte[].class)
                || clazz.equals(Byte[].class);
    }

    /**
     * �������ȡ���󣺲��ٱ���һ���޲ι���
     *
     * @param claxx
     * @return
     * @throws Exception
     */
    public static <T> T newInstance(Class<T> claxx) throws Exception {
        Constructor<?>[] cons = claxx.getDeclaredConstructors();
        for (Constructor<?> c : cons) {
            Class[] cls = c.getParameterTypes();
            if (cls.length == 0) {
                c.setAccessible(true);
                return (T) c.newInstance();
            } else {
                Object[] objs = new Object[cls.length];
                for (int i = 0; i < cls.length; i++) {
                    objs[i] = getDefaultPrimiticeValue(cls[i]);
                }
                c.setAccessible(true);
                return (T) c.newInstance(objs);
            }
        }
        return null;
    }

    public static Object getDefaultPrimiticeValue(Class clazz) {
        if (clazz.isPrimitive()) {
            return clazz == boolean.class ? false : 0;
        }
        return null;
    }

    /**
     * �ж��Ƿ��Ǽ��϶���
     * @param claxx
     * @return
     */
    public static boolean isCollection(Class claxx) {
        return Collection.class.isAssignableFrom(claxx);
    }

    /**
     * �ж��Ƿ�������
     * @param claxx
     * @return
     */
    public static boolean isArray(Class claxx) {
        return claxx.isArray();
    }
}
