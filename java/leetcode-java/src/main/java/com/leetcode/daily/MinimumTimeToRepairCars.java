package com.leetcode.daily;

public class MinimumTimeToRepairCars {
    public long repairCars(int[] ranks, int cars) {
        /*
         * [4,3,2,1] cars = 10
         * target: find min n
         * maximum n: worst rank *cars^2
         * min: 1
         * find a number between 1 and max
         */
        long l = 1;
        long r = Long.MAX_VALUE;
        // long r = Arrays.stream(ranks).max().getAsInt() * cars * cars;
        long m = (r + l) / 2;
        while (l < r) {
            if (isPossibleRepairInTime(ranks, cars, m)) {
                r = m;
            } else {
                l = m + 1;
            }
            m = (r + l) / 2;
        }
        return m;
    }

    private boolean isPossibleRepairInTime(int[] ranks, int cars, long time) {
        long totalRepairedCard = 0;
        // ranks * cars^2 = time
        // cars = root(time / ranks)
        for (int i = 0; i < ranks.length; i++) {
            totalRepairedCard += (long) (Math.sqrt(time / ranks[i]));
        }
        return totalRepairedCard >= cars;
    }
}
