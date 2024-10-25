import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by phani16 on 04/08/2019.
 */
public class MazeProblemTest {

    private MazeProblem mazeProblem;

    @BeforeEach
    public void setUp() {
        mazeProblem = new MazeProblem();
    }

    @Test
    void mazePathForNullDataThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> mazeProblem.mazePath(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void mazePathThrowsForEmptyDataIllegalArgumentException() {
        assertThatThrownBy(() -> mazeProblem.mazePath(new char[][]{}))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void mazePathReturnsResponseSameAsInput() {
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
    void mazePathReturnsResponseForAsymmetricData() {
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
        assertThat(expected, is(equalTo(arrays)));
    }

    @Test
    void mazePathReturnsResponseForSymmetricData() {
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
        assertThat(expected, is(equalTo(arrays)));
    }
}
