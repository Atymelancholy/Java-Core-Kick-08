package com.atymelancholy.arrays.warehouse;

public final class SequenceStatistics {

    private final int min;
    private final int max;
    private final long sum;
    private final double average;
    private final int count;

    public SequenceStatistics(int min, int max, long sum, double average, int count) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.average = average;
        this.count = count;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public long getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public int getCount() {
        return count;
    }
}
