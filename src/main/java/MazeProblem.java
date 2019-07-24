import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by phani16 on 23/07/2019.
 */
public class MazeProblem {

    private List blocks = asList('#','.');

    public static void main(String... args) throws URISyntaxException {
        try {
            final MazeProblem mazeProblem = new MazeProblem();

            // Read the file data.
            final char[][] mazeValues = Files.readAllLines(Paths.get(MazeProblem.class.getClassLoader().getResource("maze.txt").toURI()))
                                              .stream()
                                              .map(String::toCharArray)
                                              .toArray(char[][]::new);

            // Get the index of Starting element 'S'.
            final Integer[] point = IntStream.range(0, mazeValues.length)
                    .filter(counter -> String.valueOf(mazeValues[counter]).indexOf('S') >= 0)
                    .mapToObj(counter -> new Integer[]{counter, String.valueOf(mazeValues[counter]).indexOf('S')})
                    .findAny().orElse(null);

            if (Objects.nonNull(point)) {
                mazeProblem.findPath(mazeValues, point[0],point[1]);
            }

            Stream.of(mazeValues)
                    .flatMap(Stream::of)
                    .forEach(System.out::println);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    private boolean exploreNeighbourNode(char[][] values, int x, int y) {
        boolean result;

        // Success
        if (values[x][y] == 'E') {
            return true;
        }

        // Failure
        if (blocks.contains(values[x][y])) {
            return false;
        }

        // Check for empty space and set the value to '.' to make traversable.
        values[x][y] = '.';

        // Traverse Right
        result = exploreNeighbourNode(values, x+1, y);
        if (result) { return true;}

        // Traverse Left
        result = exploreNeighbourNode(values,x-1, y);
        if (result) { return true;}

        // Traverse Up
        result = exploreNeighbourNode(values, x, y+1);
        if (result) { return true;}

        // Traverse Down
        result = exploreNeighbourNode(values, x, y-1);
        if (result) { return true;}

        // Reset the value to empty space.
        values[x][y] = ' ';
        return result;
    }

    private void findPath(char[][] values, int x, int y) {
        if (exploreNeighbourNode(values,x,y)) {
            // Resetting the same value. Start Value
            values[x][y] = 'S';
        }

    }


}
