package christmas.domain;

public class Order {
    private Calendar visitDate;
    private Benefit benefit;

    public Order() {
        this.benefit = new Benefit();
    }

    public void setVisitDate(String expectedVisitDate) {
        validateExpectedVisitDate(expectedVisitDate);
        this.visitDate = Calendar.getByDay(Integer.parseInt(expectedVisitDate));
        benefit.setDiscount(this.visitDate);
    }


    private void validateExpectedVisitDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
