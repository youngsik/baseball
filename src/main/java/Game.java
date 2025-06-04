public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIlleagalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, 3, 0);
        } else {
            int strikeCount = 0;
            int ballCount = 0;
            for (int digit = 0; digit < 3; digit++) {
                if (guessNumber.charAt(digit) == question.charAt(digit)) {
                    strikeCount++;
                } else {
                    for (int otherDigit = 0; otherDigit < 3; otherDigit++) {
                        if (digit == otherDigit) {
                            continue;
                        } else if (guessNumber.charAt(digit) == question.charAt(otherDigit)) {
                            ballCount++;
                            break;
                        }
                    }
                }
            }
            return new GuessResult(false, strikeCount, ballCount);
        }
    }

    private static void assertIlleagalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
