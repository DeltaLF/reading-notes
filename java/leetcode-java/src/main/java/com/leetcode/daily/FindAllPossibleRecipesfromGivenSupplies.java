package com.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllPossibleRecipesfromGivenSupplies {
    private List<String> ans;
    private Set<String> suppliesSet;
    private HashMap<String, Integer> recipeMap;
    private HashMap<String, Integer> recipeIndex;
    private String[] recipes;
    private List<List<String>> ingredients;

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        /*
         * Try dft
         *
         * wrtie a dft helper function:
         * input: recipe index
         * return: Boolean (true for possible)
         *
         * Prepare recipes hashMap (0:not visted, 1: possible, -1: impossible, in
         * progress: 2)
         * Iterate through recipes
         * pass recipe index into DFT
         * (in the DFT) iterate through the ingredients
         * if ingredient in recipes: recuresively dft
         */

        this.recipes = recipes;
        this.ingredients = ingredients;
        this.suppliesSet = new HashSet<>(Arrays.asList(supplies));

        this.recipeMap = new HashMap<>();
        this.recipeIndex = new HashMap<>();
        this.ans = new ArrayList<>();
        Integer n = recipes.length;
        for (int i = 0; i < n; i++) {
            this.recipeMap.put(recipes[i], 0);
            this.recipeIndex.put(recipes[i], i);
        }

        for (int i = 0; i < n; i++) {
            dft(i);
        }
        return this.ans;

    }

    private Boolean dft(Integer index) {
        String recipeStr = this.recipes[index];
        Integer recipeStatus = this.recipeMap.get(recipeStr);
        // already visited
        if (recipeStatus != 0) {
            return recipeStatus == 1;
        }
        this.recipeMap.put(recipeStr, 2);
        // hasn't visited
        Integer matchedIngredientCount = 0;
        for (String ingredient : this.ingredients.get(index)) {
            if (this.recipeMap.get(ingredient) == null) {
                // the ingredient is not in recipe
                if (this.suppliesSet.contains(ingredient)) {
                    matchedIngredientCount++;
                }
                continue;
            }
            // the ingredient is in the recipe:
            Boolean hasRecipe = dft(this.recipeIndex.get(ingredient));
            if (hasRecipe) {
                matchedIngredientCount++;
            }
        }
        if (matchedIngredientCount == this.ingredients.get(index).size()) {
            this.ans.add(recipeStr);
            this.recipeMap.put(recipeStr, 1);
            return true;
        }
        this.recipeMap.put(recipeStr, -1);
        return false;
    }

    public List<String> findAllRecipesBurteForce(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        /*
         * for brute force:
         * first n: go thorugh recpie
         * second n:
         * if supplies changed go throgh recipe again
         * in the worest case recipe: [ing0, ing1, ing2... ing99]
         * the third m: go through ingred in each recipe
         * O(n*n*n)
         *
         * sol
         * 1. make supplies set
         * 2. go through recipe
         * 3. add to supplies set whenver it's possible
         * 4. if no new supply is added (no possible recipe) => stop
         * (in the worest scenario, it takes n time (+1 recipe each loop))
         */
        List<String> ans = new ArrayList<>();
        int n = recipes.length;
        Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
        boolean isNewSuppliesFound = true;
        while (isNewSuppliesFound) {
            isNewSuppliesFound = false;
            for (int i = 0; i < n; i++) {
                List<String> ingreds = ingredients.get(i);
                if (ingreds == null)
                    continue;
                int ingredMatced = 0;
                for (String ingred : ingreds) {
                    if (suppliesSet.contains(ingred)) {
                        ingredMatced++;
                    }
                }
                if (ingreds.size() == ingredMatced) {
                    suppliesSet.add(recipes[i]);
                    ans.add(recipes[i]);
                    isNewSuppliesFound = true;
                    ingredients.set(i, null);
                }

            }
        }

        return ans;

    }
}