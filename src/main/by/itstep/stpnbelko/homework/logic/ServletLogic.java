package by.itstep.stpnbelko.homework.logic;

import java.util.Random;

public class ServletLogic {
    public static final String NEGATIVE_ANSWER = "Must be: lowNum < hiNum";
    static String answer = NEGATIVE_ANSWER;

    public static String getAnswer() {
        return answer;
    }

    public static String countRandom(String minString, String maxString) {
        int min = Integer.parseInt(minString.trim());
        int max = Integer.parseInt(maxString.trim());

        if (min < max) {
            Random random = new Random();
            answer = String.valueOf((random.nextInt(max - min + 1) + min));
        }else {
            answer = NEGATIVE_ANSWER;
        }
        return answer;
    }
}
