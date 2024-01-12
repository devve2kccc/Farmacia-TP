import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static NumberFormat getNumberFormat() {

        Locale locale = Locale.GERMANY;
        return NumberFormat.getCurrencyInstance(locale);

    }
}
