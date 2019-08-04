import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by phani16 on 04/08/2019.
 */
public class MazeProblemTest {

    @Rule
    public ExpectedException exception= ExpectedException.none();

    private MazeProblem mazeProblem;

    @Before
    public void setUp() {
        mazeProblem = new MazeProblem();
    }

    @Test
    public void mazePathForNullDataThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        mazeProblem.mazePath(null);
    }

    @Test
    public void mazePathThrowsForEmptyDataIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        char[][] empty = new char[][]{};
        mazeProblem.mazePath(empty);
    }

    @Test
    public void mazePathReturnsResponseSameAsInput() {
        final char[][] arrays = new char[][] {
                { '#', '#','#' , '#'},
                { '#', ' ', 'E', '#' },
                { '#', 'A', '#' , '#'},
                { '#', '#', '#' , '#'},
        } ;

        final char[][] expected = new char[][] {
                { '#', '#','#' , '#'},
                { '#', ' ', 'E', '#' },
                { '#', 'A', '#' , '#'},
                { '#', '#', '#' , '#'},
        } ;

        mazeProblem.mazePath(arrays);
        assertThat(expected, is(equalTo(arrays)));
    }

    @Test
    public void mazePathReturnsResponseForAsymetricData() {
        final char[][] arrays = new char[][] {
                { '#', '#','#' , '#'},
                { '#', ' ', 'E', '#' },
                { '#', 'S', '#' , '#'}
        } ;

        final char[][] expected = new char[][] {
                { '#', '#','#' , '#'},
                { '#', '.', 'E', '#' },
                { '#', 'S', '#' , '#'}
        } ;

        mazeProblem.mazePath(arrays);
        assertThat(expected, equalTo(arrays));
    }

    @Test
    public void mazePathReturnsResponseForSymetricData() {
        final char[][] arrays = new char[][] {
                { '#', '#','#' , '#'},
                { '#', ' ', 'E', '#' },
                { '#', ' ', '#', '#' },
                { '#', 'S', '#' , '#'}
        };

        final char[][] expected = new char[][] {
                { '#', '#','#' , '#'},
                { '#', '.', 'E', '#' },
                { '#', '.', '#', '#' },
                { '#', 'S', '#' , '#'}
        } ;

        mazeProblem.mazePath(arrays);
        assertThat(expected, equalTo(arrays));
    }
}
