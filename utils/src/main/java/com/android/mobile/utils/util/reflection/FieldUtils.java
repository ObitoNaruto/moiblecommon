package com.android.mobile.utils.util.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class FieldUtils {
    /**
     * �ж��Ƿ����л�
     *
     * @param f
     * @return
     */
    public static boolean isSerializable(Field f) {
        Class<?>[] cls = f.getType().getInterfaces();
        for (Class<?> c : cls) {
            if (Serializable.class == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * �������ֵ
     *
     * @param f
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object set(Field f, Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        f.set(obj, value);
        return f.get(obj);
    }

    /**
     * ��ȡ���ֵ
     *
     * @param f
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object get(Field f, Object obj) throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        return f.get(obj);
    }

    public static boolean isLong(Field field) {
        return field.getType() == long.class || field.getType() == Long.class;
    }

    public static boolean isInteger(Field field) {
        return field.getType() == int.class || field.getType() != Integer.class;
    }

    /**
     * ��ȡ��ķ������ͣ�����������ͷ���null
     *
     * @param f
     * @return
     */
    public static Class<?> getGenericType(Field f) {
        Type type = f.getGenericType();
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type instanceof Class<?>) return (Class<?>) type;
        } else if (type instanceof Class<?>) return (Class<?>) type;
        return null;
    }

    /**
     * ��ȡ���������
     *
     * @param f
     * @return
     */
    public static Class<?> getComponentType(Field f) {
        return f.getType().getComponentType();
    }

    /**
     * ��ȡȫ��Field����������
     *
     * @param claxx
     * @return
     */
    public static List<Field> getAllDeclaredFields(Class<?> claxx) {
        // find all field.
        LinkedList<Field> fieldList = new LinkedList<Field>();
        while (claxx != null && claxx != Object.class) {
            Field[] fs = claxx.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                if (!isInvalid(f)) {
                    fieldList.addLast(f);
                }
            }
            claxx = claxx.getSuperclass();
        }
        return fieldList;
    }

    /**
     * �Ǿ�̬���������ڲ��ṹ����
     *
     * @param f
     * @return
     */
    public static boolean isInvalid(Field f) {
        return (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) || f.isSynthetic();
    }
}
