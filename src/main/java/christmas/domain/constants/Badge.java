package christmas.domain.constants;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minBenefitAmount;

    Badge(String name, int minBenefitAmount) {
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeByAmount(int amount) {
        if (amount >= SANTA.minBenefitAmount) {
            return SANTA;
        }
        if (amount >= TREE.minBenefitAmount) {
            return TREE;
        }
        if (amount >= STAR.minBenefitAmount) {
            return STAR;
        }
        return null;
    }
}