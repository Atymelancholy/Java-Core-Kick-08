package com.atymelancholy.arrays.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Warehouse {

    private static Warehouse instance;

    private final Map<Long, SequenceStatistics> data = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(long id, SequenceStatistics statistics) {
        data.put(id, statistics);
    }

    public Optional<SequenceStatistics> get(long id) {
        return Optional.ofNullable(data.get(id));
    }

    public void remove(long id) {
        data.remove(id);
    }
}
