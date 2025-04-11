package com.pokfulamroad.admintemplate.business.emum;

public enum TimeEnum {
    SIX("6:00", 1),
    SEVEN("7:00", 2),
    EIGHT("8:00", 3),
    NINE("9:00", 4),
    TEN("10:00", 5),
    ELEVEN("11:00", 6),
    TWELVE("12:00", 7),
    THIRTEEN("13:00", 8),
    FOURTEEN("14:00", 9),
    FIFTEEN("15:00", 10),
    SIXTEEN("16:00", 11),
    SEVENTEEN("17:00", 12),
    EIGHTEEN("18:00", 13);

    final String time;
    final Integer value;

    TimeEnum(String time, Integer value) {
        this.time = time;
        this.value = value;
    }
}
