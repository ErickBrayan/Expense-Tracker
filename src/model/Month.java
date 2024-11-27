package model;

public enum Month {
    January,
    February,
    March,
    April,
    May,
    June,
    July,
    August,
    September,
    October,
    November,
    December;


    public static String getMoth(int numMonths) {
        return Month.values()[numMonths - 1].toString();
    }

}
