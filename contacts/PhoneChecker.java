package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneChecker {
    public static boolean isValid(String number) {
        Pattern pattern = Pattern.compile("\\+?(\\([a-zA-Z0-9]+\\)[ -][a-zA-Z0-9]{2,}|[a-zA-Z0-9]+[ -]\\([a-zA-Z0-9]{2,}\\)|[a-zA-Z0-9]+[ -][a-zA-Z0-9]{2,}|\\([a-zA-Z0-9]+\\)|[a-zA-Z0-9]+)([ -][a-zA-Z0-9]{2,})*");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
