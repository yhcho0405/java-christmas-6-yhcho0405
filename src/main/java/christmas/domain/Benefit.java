package christmas.domain;

import java.util.Map;

public class Benefit {
    private int dDayDiscount = 0;
    private int weekdayDiscount = 0;
    private int weekendDiscount = 0;
    private int specialDiscount = 0;
    private final Calendar visitDate;
    private final Map<MenuBoard, Integer> orders;

    public Benefit(Calendar visitDate, Map<MenuBoard, Integer> orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public void calculateBenefit() {
        calculateDDayDiscount();
        calculateWeekdayDiscount();
        calculateWeekendDiscount();
        calculateSpecialDiscount();
    }

    private void calculateDDayDiscount() {
        if (visitDate.getDay() <= 25) {
            dDayDiscount = 1000 + (visitDate.getDay() - 1) * 100;
        }
    }

    private void calculateWeekdayDiscount() {

    }

    private void calculateWeekendDiscount() {
    }

    private void calculateSpecialDiscount() {
        if (visitDate.isSpecialDiscountDay()) {
            specialDiscount = 1000;
        }
    }


}
