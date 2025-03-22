package com.leetcode.daily;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class FindAllPossibleRecipesfromGivenSuppliesTest {
    @Test
    public void testFindAllPossibleRecipesfromGivenSupplies() {
        FindAllPossibleRecipesfromGivenSupplies solution = new FindAllPossibleRecipesfromGivenSupplies();
        String[] expected = { "a", "b", "c", "d", "e" };
        String[] actual = solution.findAllRecipes(
                new String[] { "a", "b", "c", "d", "e" },
                Arrays.stream(new String[][] { { "b" }, { "c" }, { "d" }, { "e" }, { "z" } })
                        .map(Arrays::asList)
                        .collect(Collectors.toList()),
                new String[] { "z" }).toArray(new String[0]); // Convert List<String> to String[]

        Arrays.sort(expected);
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
        String[] expectedII = { "ju", "fzjnm", "q" };

        String[] actualII = solution.findAllRecipes(
                new String[] { "ju", "fzjnm", "x", "e", "zpmcz", "h", "q" }, // Convert input list to array
                Arrays.stream(new String[][] {
                        { "d" },
                        { "hveml", "f", "cpivl" },
                        { "cpivl", "zpmcz", "h", "e", "fzjnm", "ju" },
                        { "cpivl", "hveml", "zpmcz", "ju", "h" },
                        { "h", "fzjnm", "e", "q", "x" },
                        { "d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x" },
                        { "f", "hveml", "cpivl" }
                }).map(Arrays::asList)
                        .collect(Collectors.toList()), // Convert 2D array to List<List<String>>
                new String[] { "f", "hveml", "cpivl", "d" } // Convert last list to array
        ).toArray(new String[0]); // Convert List<String> to String[]
        Arrays.sort(expectedII);
        Arrays.sort(actualII);
        assertArrayEquals(expectedII, actualII); // Assert arrays are equal

    }
}
