package utilis;

import java.util.*;

public class DateHelper {

    private DateHelper() {
    }

    public static Date getDays(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
}
