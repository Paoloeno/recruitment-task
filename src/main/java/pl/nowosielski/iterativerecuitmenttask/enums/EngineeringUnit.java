package pl.nowosielski.iterativerecuitmenttask.enums;

public enum EngineeringUnit {
    M("Meter", "m"),
    M_2("Square meter", "m2"),
    KG("kilogram", "kg"),
    M_S("Meter per second", "m/s"),
    N("Newton", "N"),
    PA("Pascal", "P"),
    W("Watt", "W")
    ;

    private String description;
    private String symbol;

    EngineeringUnit(String description, String symbol) {
        this.description = description;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
