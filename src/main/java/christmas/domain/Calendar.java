package christmas.domain;

import java.util.Set;

public enum Calendar {
    DAY_1(1, DayType.WEEKEND),
    DAY_2(2, DayType.WEEKEND),
    DAY_3(3, DayType.WEEKDAY, true),
    DAY_4(4, DayType.WEEKDAY),
    DAY_5(5, DayType.WEEKDAY),
    DAY_6(6, DayType.WEEKDAY),
    DAY_7(7, DayType.WEEKDAY),
    DAY_8(8, DayType.WEEKEND),
    DAY_9(9, DayType.WEEKEND),
    DAY_10(10, DayType.WEEKDAY, true),
    DAY_11(11, DayType.WEEKDAY),
    DAY_12(12, DayType.WEEKDAY),
    DAY_13(13, DayType.WEEKDAY),
    DAY_14(14, DayType.WEEKDAY),
    DAY_15(15, DayType.WEEKEND),
    DAY_16(16, DayType.WEEKEND),
    DAY_17(17, DayType.WEEKDAY, true),
    DAY_18(18, DayType.WEEKDAY),
    DAY_19(19, DayType.WEEKDAY),
    DAY_20(20, DayType.WEEKDAY),
    DAY_21(21, DayType.WEEKDAY),
    DAY_22(22, DayType.WEEKEND),
    DAY_23(23, DayType.WEEKEND),
    DAY_24(24, DayType.WEEKDAY, true),
    DAY_25(25, DayType.WEEKDAY, true),
    DAY_26(26, DayType.WEEKDAY),
    DAY_27(27, DayType.WEEKDAY),
    DAY_28(28, DayType.WEEKDAY),
    DAY_29(29, DayType.WEEKEND),
    DAY_30(30, DayType.WEEKEND),
    DAY_31(31, DayType.WEEKDAY, true);

    private static final Set<Integer> SPECIAL_DISCOUNT_DAYS = Set.of(3, 10, 17, 24, 25, 31);

    private final int day;
    private final DayType dayType;
    private final boolean isSpecialDiscountDay;

    Calendar(int day, DayType dayType) {
        this(day, dayType, false);
    }

    Calendar(int day, DayType dayType, boolean isSpecialDiscountDay) {
        this.day = day;
        this.dayType = dayType;
        this.isSpecialDiscountDay = isSpecialDiscountDay;
    }


    public static Calendar getByDay(int day) {
        for (Calendar calendar : Calendar.values()) {
            if (calendar.day == day) {
                return calendar;
            }
        }
        throw new IllegalArgumentException("[ERROR]");
    }

    public int getDay() {
        return this.day;
    }

    public boolean isWeekend() {
        return dayType == DayType.WEEKEND;
    }

    public boolean isSpecialDiscountDay() {
        return isSpecialDiscountDay;
    }

    private enum DayType {
        WEEKDAY, WEEKEND
    }
}
