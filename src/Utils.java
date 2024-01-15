import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    /* Retorna um formatador de números para a localidade da Alemanha */
    public static NumberFormat getNumberFormat() {
        /* Define a localidade como Alemanha */
        Locale locale = Locale.GERMANY;
        /* Retorna um formatador de moeda para a localidade especificada */
        return NumberFormat.getCurrencyInstance(locale);
    }
}