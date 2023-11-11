package christmas.domain;

public class Benefit {
    private int dDayDiscount = 0;
    private int weekdayDiscount = 0;
    private int weekendDiscount = 0;
    private int specialDiscount = 0;

    public Benefit() {
    }

    public void setDiscount(Calendar visitDate) {
        calculateDDayDiscount(visitDate);
        calculateWeekdayDiscount(visitDate);
        calculateWeekendDiscount(visitDate);
        calculateSpecialDiscount(visitDate);
    }

    private void calculateDDayDiscount(Calendar visitDate) {
        if (visitDate.getDay() <= 25) {
            dDayDiscount = 1000 + (visitDate.getDay() - 1) * 100;
        }
    }

    private void calculateWeekdayDiscount(Calendar visitDate) {
    }

    private void calculateWeekendDiscount(Calendar visitDate) {
    }

    private void calculateSpecialDiscount(Calendar visitDate) {
        if (visitDate.isSpecialDiscountDay()) {
            specialDiscount = 1000;
        }
    }


}
