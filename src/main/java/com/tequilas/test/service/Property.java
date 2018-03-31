package com.tequilas.test.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public final class Property {

    private static String name;
    private static int age;
    private static long code;
    private static boolean status;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream fInputStream = new FileInputStream("prop.properties");
            properties.load(fInputStream);
            fInputStream.close();

            String fieldName = "";
            String fieldType = "";
            for (Field field : Property.class.getDeclaredFields()) {
                fieldName = field.getName();
                fieldType = field.getType().getSimpleName();

                if (fieldType.equals("String")) {
                    field.set(null, properties.getProperty(fieldName));
                } else if (fieldType.equals("int")) {
                    field.set(null, Integer.parseInt(properties.getProperty(fieldName)));
                } else if (fieldType.equals("long")) {
                    field.set(null, Long.parseLong(properties.getProperty(fieldName)));
                } else if (fieldType.equals("boolean")) {
                    field.set(null, Boolean.parseBoolean(properties.getProperty(fieldName)));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Property() {
    }

    public static String getName() {
        return name;
    }

    public static int getAge() {
        return age;
    }

    public static long getCode() {
        return code;
    }

    public static boolean isStatus() {
        return status;
    }

}
