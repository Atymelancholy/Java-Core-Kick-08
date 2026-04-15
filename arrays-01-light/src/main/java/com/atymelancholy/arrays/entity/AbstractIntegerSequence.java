package com.atymelancholy.arrays.entity;

import java.util.Arrays;

public abstract class AbstractIntegerSequence {

    private final long id;
    private final String name;
    private final int[] storage;

    protected AbstractIntegerSequence(long id, String name, int[] source) {
        this.id = id;
        this.name = name;
        this.storage = Arrays.copyOf(source, source.length);
    }

    public final long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final int getLength() {
        return storage.length;
    }

    public final int getElement(int index) {
        ensureIndexInRange(index);
        return storage[index];
    }

    public final int[] getElements() {
        return Arrays.copyOf(storage, storage.length);
    }

    public final void setElement(int index, int value) {
        ensureIndexInRange(index);
        storage[index] = value;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractIntegerSequence other)) {
            return false;
        }
        return id == other.id
                && sameName(other.name)
                && Arrays.equals(storage, other.storage);
    }

    @Override
    public final int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + nameHashCode();
        result = 31 * result + Arrays.hashCode(storage);
        return result;
    }

    @Override
    public final String toString() {
        return "IntegerSequence{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", values=" + Arrays.toString(storage)
                + '}';
    }

    private void ensureIndexInRange(int index) {
        if (index < 0 || index >= storage.length) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of range [0.." + (storage.length - 1) + "]");
        }
    }

    private boolean sameName(String otherName) {
        if (name == null) {
            return otherName == null;
        }
        return name.equals(otherName);
    }

    private int nameHashCode() {
        if (name == null) {
            return 0;
        }
        return name.hashCode();
    }
}
