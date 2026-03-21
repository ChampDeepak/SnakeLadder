import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class BoardBuilder {
    private static Random randomNums = new Random();

    public static Board get(int boardLength) {
        int boardSize = boardLength * boardLength;
        final HashMap<Integer, Integer> snakeMap = new HashMap<>();
        final HashMap<Integer, Integer> ladderMap = new HashMap<>();
        HashSet<Integer> snakeEnd = new HashSet<>();
        buildSnakeMap(snakeMap, snakeEnd, boardSize);
        buildLadderMap(ladderMap, snakeMap, snakeEnd, boardSize);
        return new Board(boardLength, snakeMap, ladderMap);
    }

    private static void buildLadderMap(HashMap<Integer, Integer> ladderMap, HashMap<Integer, Integer> snakeMap,
            HashSet<Integer> snakeEnd, int boardSize) {
        int min = 3;
        int max = boardSize - 3;
        HashSet<Integer> ladderEnd = new HashSet<>();
        for (int i = 0; i < boardSize; i++) {
            int start = randomNums.nextInt((max - min) + 1) + min;
            while (ladderMap.containsKey(start) ||
                    snakeMap.containsKey(start) ||
                    ladderEnd.contains(start) ||
                    snakeEnd.contains(start)){
                start = randomNums.nextInt((max - min) + 1) + min;
            }
                
            int end = start + max*randomNums.nextInt((max - 1) + 1) + 1;
            while (ladderMap.containsKey(end) ||
                    snakeMap.containsKey(end) ||
                    ladderEnd.contains(end)){
                        end = randomNums.nextInt((max - min) + 1) + min;
                    }
                
            
            ladderEnd.add(end); 
            ladderMap.put(start, end); 
        }

    }

    private static void buildSnakeMap(HashMap<Integer, Integer> snakeMap, HashSet<Integer> snakeEnd, int boardSize) {
        int min = 3;
        int max = boardSize - 3;
        for (int i = 0; i < boardSize; i++){
            int end = randomNums.nextInt((max - min) + 1) + min;
            while(snakeMap.containsKey(end) ||
            snakeEnd.contains(end)) end = randomNums.nextInt((max - min) + 1) + min;
            int start = end + max*randomNums.nextInt((max - 1) + 1) + 1;
            while(snakeMap.containsKey(start) || 
            snakeEnd.contains(start)) start = end + max*randomNums.nextInt((max - 1) + 1) + 1;
            snakeEnd.add(end); 
            snakeMap.put(start, end); 
        }
    }

}
