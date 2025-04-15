package com.leetcode.seventyFive.hashmap_set;

import java.util.HashMap;

public class EqualRowandColumnPairs {
    public int equalPairs(int[][] grid) {
        /*
         * 1 2 3
         * 4 5 2
         * 7 8 1
         */
        int n = grid.length;
        HashMap<String, Integer> colMap = new HashMap<>();
        HashMap<String, Integer> rowMap = new HashMap<>();

        String[] colArr = new String[n];
        String[] rowArr = new String[n];
        // make colMap
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                colArr[j] = String.valueOf(grid[i][j]);
                rowArr[j] = String.valueOf(grid[j][i]);
            }
            String colStr = String.join("_", colArr);
            String rowStr = String.join("_", rowArr);
            int colStrCount = colMap.getOrDefault(colStr, 0);
            colMap.put(colStr, colStrCount + 1);
            int rowStrCount = rowMap.getOrDefault(rowStr, 0);
            rowMap.put(rowStr, rowStrCount + 1);
        }

        int count = 0;
        for (HashMap.Entry<String, Integer> en : colMap.entrySet()) {
            String key = en.getKey();
            Integer val = en.getValue();
            if (rowMap.containsKey(key)) {
                Integer rowVal = rowMap.get(key);
                count += rowVal * val;
            }

        }
        return count;

    }

}
