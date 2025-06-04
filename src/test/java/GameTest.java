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
        assertMatchedNumber(game.guess("567"), false,0,0);
    }

    @Test
    void returnSolvedResultMatched2Strike0ballNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("127"), false,2,0);
    }

    @Test
    void returnSolvedResultMatched0Strike1ballNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("241"), false,0,2);
    }

    @Test
    void returnSolvedResultMatched1Strike2ballNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("321"), false,1,2);
    }

    @Test
    void returnSolvedResultMatched1Strike2ballNumber2() {
        generateQuestion("125");
        assertMatchedNumber(game.guess("215"), false,1,2);
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