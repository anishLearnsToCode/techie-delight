package Metvy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem {
    public static void main(String[] args) {
        System.out.println(getSingleDigit(10));
    }

    private static int getSingleDigit(int number) {
        return (number - ((number - 1) / 9 ) * 9) % 10 ;
    }

    private static boolean checkIfIsTree(Character[][] tree) {
        Set<Character> connectedNodes = new HashSet<>();

        for (Character[] characters : tree) {
            if (connectedNodes.contains(characters[0]) && connectedNodes.contains(characters[1])) {
                return false;
            }

            connectedNodes.add(characters[0]);
            connectedNodes.add(characters[1]);
        }

        return true;
    }

    private static boolean cycleExistsInGraph(Character[][] nodes) {
        Set<Character> visitedNodes = new HashSet<>();
        Map<Character, Integer> characterIndices = getIndicesOf(nodes);
        return cycleExistsInGraph(nodes, 0, new HashSet<>(), getIndicesOf(nodes));
    }

    private static Map<Character, Integer> getIndicesOf(Character[][] nodes) {
        Map<Character, Integer> indicesMap = new HashMap<>();
        int index = 0;
        for (Character[] array : nodes) {
            indicesMap.put(array[0], index++);
        }
        return indicesMap;
    }

    private static boolean cycleExistsInGraph(Character[][] nodes, int index, Set<Character> visitedNodes, Map<Character, Integer> characterIndices) {
        for (int i = index ; i < nodes.length ; i++) {
            if (visitedNodes.contains(nodes[i][0])) {
                continue;
            }

            if (visitedNodes.contains(nodes[i][1])) {
                return false;
            }

            visitedNodes.add(nodes[i][0]);

            if (cycleExistsInGraph(nodes, characterIndices.get(nodes[index][1]), visitedNodes, characterIndices)) {
                return false;
            }
        }

        return true;
    }
}
