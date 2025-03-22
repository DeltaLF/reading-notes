package com.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

public class CounttheNumberofCompleteComponents {
    private int[] parent;
    private int[] depth;

    public int countCompleteComponents(int n, int[][] edges) {

        /*
         *
         * For a complete connected, every component should connect to n-1 nodes (n is
         * the connected nodes group size)
         *
         * 1. make a edge count array
         * 2. implement union find to divide nodes into connected groups
         * 3.
         *
         * the groupMap:
         * {
         * rootNodeIndex: [group size, qualified node count]
         * }
         *
         * 4-1
         * |
         * 3-2-0
         *
         *
         */
        int count = 0;
        this.parent = new int[n];
        this.depth = new int[n];
        int[] adjCountArray = new int[n];
        HashMap<Integer, int[]> groupMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            this.parent[i] = -1;
        }

        for (int[] edge : edges) {
            int n0 = edge[0];
            int n1 = edge[1];
            union(n0, n1);
            adjCountArray[n0]++;
            adjCountArray[n1]++;
        }

        // get the size of each group
        for (int i = 0; i < n; i++) {
            int root = find(i);
            int[] groupData = groupMap.get(root);
            if (groupData == null) {
                groupMap.put(root, new int[] { 1, 0 });
            } else {
                groupData[0]++;
            }
        }

        // System.out.print("adjCountArray");
        // System.out.print(Arrays.toString(adjCountArray));

        // check the qualification of each node:
        for (int i = 0; i < n; i++) {
            int root = this.parent[i];
            if (root == -1)
                root = i;
            int[] groupData = groupMap.get(root);
            int groupSize = groupData[0];
            int nodeAdjCount = adjCountArray[i];

            // System.out.print("\ni:" + i + "root:" + root + "groupSize:" + groupSize +
            // "nodeAdjCount:" + nodeAdjCount);
            if (nodeAdjCount == groupSize - 1) {
                groupData[1]++;
            }
        }

        // System.out.print("parent array");
        // System.out.print(Arrays.toString(this.parent));
        // get the qualified group count
        for (Map.Entry<Integer, int[]> entry : groupMap.entrySet()) {
            // key: group root node
            // value: group data
            int[] groupData = entry.getValue();

            // System.out.print("groupData");
            // System.out.print(Arrays.toString(groupData));
            int groupSize = groupData[0];
            int qualifiedNodeCount = groupData[1];
            if (groupSize == qualifiedNodeCount) {
                count++;
            }

        }

        return count;
    }

    private int find(int node) {
        // the root
        if (this.parent[node] == -1)
            return node;
        return parent[node] = find(parent[node]);
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        // already in the same group
        if (rootA == rootB)
            return;
        if (this.depth[rootA] < this.depth[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }
        // always make rootA the longer
        // and append the shorter to longer
        this.parent[rootB] = rootA;

        if (this.depth[rootA] == this.depth[rootB]) {
            // if rootA is longer, an extra appended list won't make it longer
            this.depth[rootA]++;
        }

    }
}
