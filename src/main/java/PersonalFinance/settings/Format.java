package PersonalFinance.settings;

import PersonalFinance.model.Currency;
import PersonalFinance.model.Filter;
import com.sun.tools.javac.Main;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
    public static String amount(double amount) {
        return String.format(Settings.FORMAT_AMOUNT, amount);
    }

    public static String amount(double amount, Currency currency) {
        return amount(amount) + " " + currency.getCode();
    }

    public static String rate(double rate) {
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) {
        return rate(rate) + " " + currency.getCode();
    }

    public static String date(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE);
    }

    public static String dateMonth(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_MONTH);
    }

    public static String dateYear(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_YEAR);
    }

    private static String dateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, new MainDataFormatSymbols());
        return sdf.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException {
        amount = amount.replaceAll(",",".");
        return Double.parseDouble(amount);
    }

    public static String yesMo(boolean yes) {
        if (yes) return Text.get("YES");
        return Text.get("NO");
    }

    public static String getTitleFilter(Filter filter) {
        Date time = filter.getTo();
        switch (filter.getStep()) {
            case Filter.STEP_DEY:
                return date(time);
            case Filter.STEP_MONTH:
                return dateMonth(time);
            case Filter.STEP_YEAR:
                return dateYear(time);
        }
        return null;
    }

    private static class MainDataFormatSymbols extends DateFormatSymbols {
        public String[] getMonths() {
            return Text.getMonths();
        }
    }
}

