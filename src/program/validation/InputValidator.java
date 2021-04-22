package program.validation;

public class InputValidator {

    public static int isValidNumber(String s) {
        int num;

        try {
            num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (num <= 0)
            return -1;
        else
            return num;

    }
}
