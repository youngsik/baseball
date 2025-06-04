import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }



    @Test
    void createGame() {
        assertNotNull(game);
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void returnSolvedResultMatchedNumber() {
        generateQuestion("123");

        assertMatchedNumber(game.guess("123"), true,3,0);
    }


    @Test
    void returnSolvedResultUnMatchedNumber() {
        generateQuestion("123");
        GuessResult result = game.guess("567");

        assertMatchedNumber(game.guess("567"), false,0,0);

    }

    private void generateQuestion(String questionNumber) {
        game.question = questionNumber;
    }


    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertNotNull(result);
        assertEquals(result.isSolved(), solved);
        assertEquals(result.getStrikes(), strikes);
        assertEquals(result.getBalls(), balls);
    }
}