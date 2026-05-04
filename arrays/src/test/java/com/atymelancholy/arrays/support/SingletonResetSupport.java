package com.atymelancholy.arrays.support;

import com.atymelancholy.arrays.repository.IntegerSequenceRepository;
import com.atymelancholy.arrays.warehouse.Warehouse;
import java.lang.reflect.Field;

public final class SingletonResetSupport {

    private SingletonResetSupport() {
    }

    public static void resetRepositorySingleton() {
        resetSingletonField(IntegerSequenceRepository.class, "instance");
    }

    public static void resetWarehouseSingleton() {
        resetSingletonField(Warehouse.class, "instance");
    }

    public static void resetAll() {
        resetRepositorySingleton();
        resetWarehouseSingleton();
    }

    private static void resetSingletonField(Class<?> type, String fieldName) {
        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new IllegalStateException("Could not reset singleton for " + type.getName(), ex);
        }
    }
}
