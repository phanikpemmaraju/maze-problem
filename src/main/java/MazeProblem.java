
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class MazeProblem {

    private final List<Character> blocks = asList('.','X', '#');

    private static final Predicate<char[]> hasStartPoint = mazeRow -> String.valueOf(mazeRow).indexOf('S') >= 0;

    public static void main(String... args) {
        try {
            final MazeProblem mazeProblem = new MazeProblem();

            // Read the file data.
            char[][] mazeValues = Files.readAllLines(Paths.get(MazeProblem.class.getClassLoader().getResource("maze1.txt").toURI()))
                    .stream()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);

            Point startingPoint = IntStream.range(0, mazeValues.length)
                    .filter(counter -> hasStartPoint.test(mazeValues[counter]))
                    .mapToObj(counter -> new Point(counter,String.valueOf(mazeValues[counter]).indexOf('S')))
                    .findAny().orElse(null);

            if (Objects.nonNull(startingPoint)) {
//                mazeProblem.findPath(mazeValues, startingPoint);
                boolean status = mazeProblem.iterativeDFS(mazeValues, startingPoint);
                System.out.println("Status: " + status);
            }

            Stream.of(mazeValues)
                    .forEach(System.out::println);

        } catch (URISyntaxException | IOException e) {
            System.out.println("Exception : {} " + e.getMessage());
        }

    }

    public void mazePath(final char[][] maze) throws IllegalArgumentException{
        if(Objects.isNull(maze) || maze.length == 0){
            throw new IllegalArgumentException();
        }

        // Get the index of Starting element 'S'.
        Point startingPoint = IntStream.range(0, maze.length)
                .filter(counter -> hasStartPoint.test(maze[counter]))
                .mapToObj(counter -> new Point(counter,String.valueOf(maze[counter]).indexOf('S')))
                .findAny().orElse(null);

        if (Objects.nonNull(startingPoint)) {
            //this.findPath(maze, startingPoint);
            this.iterativeDFS(maze, startingPoint);
        }

    }

    // Uses recursion
    private boolean exploreNeighbourNode(final char[][] values, Point point) {
        boolean result;

        // Success
        if (values[point.xPos()][point.yPos()] == 'E') {
            return true;
        }

        // Failure, if its already visited
        if (blocks.contains(values[point.xPos()][point.yPos()])) {
            return false;
        }

        // Check for empty space and set the value to '.' to make traversable.
        values[point.xPos()][point.yPos()] = '.';
        System.out.println("Traversing at Point: " + point);

        // Traverse Up
        result = exploreNeighbourNode(values, point.traverseForward());
        if (result) {
            return true;
        }

        // Traverse Right
        result = exploreNeighbourNode(values, point.traverseRight());
        if (result) {
            return true;
        }

        // Traverse Down
        result = exploreNeighbourNode(values, point.traverseBackward());
        if (result) {
            return true;
        }

        // Traverse Left
        result = exploreNeighbourNode(values, point.traverseLeft());
        if (result) {
            return true;
        }
        // Reset the traversed path to empty space
        values[point.xPos()][point.yPos()] = ' ';
        return result;
    }


    private void findPath(char[][] values, Point point) {
        if (exploreNeighbourNode(values, point)) {
            // Resetting the same value. Start Value
            values[point.xPos()][point.yPos()] = 'S';
        }
    }

    // Uses stack implementation

    // Time complexity O(M*N) => M,N are dimensions of maze
    // Space complexity O(M*N) => worst case if we visit every cell.
    private boolean iterativeDFS(char[][] maze, Point point) {
        Deque<Point> deque = new ArrayDeque<>();
        deque.addLast(point);

        while (!deque.isEmpty()) {
            Point current = deque.pop();
            if (maze[current.xPos()][current.yPos()] == 'E') {
                return true;
            }

            if (maze[current.xPos()][current.yPos()] != 'S') {
                maze[current.xPos()][current.yPos()] = '.';
            }


            for (Direction direction : Direction.values()) {
                int newX = current.xPos() + direction.getX();
                int newY = current.yPos() + direction.getY();

                Point newPoint = new Point(newX, newY);
                if (isExplorable(maze, newPoint)) {
                    deque.push(newPoint);
                }
            }
        }

        return false;
    }

    private boolean isExplorable(char[][] maze, Point point) {
        return point.xPos() >= 0 && point.xPos() < maze.length && point.yPos() >= 0 && point.yPos() < maze[0].length &&
                (maze[point.xPos()][point.yPos()] == ' ' || maze[point.xPos()][point.yPos()] == 'E');
    }




}