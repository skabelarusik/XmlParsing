package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.Tour;

import java.util.Set;

public abstract class XMLParserAbstract {

    protected static final String TOUR = "tour";
    protected static final String TYPE_TOUR = "typeTour";
    protected static final String CITY = "city";
    protected static final String COST = "cost";
    protected static final String TRANSPORT = "transport";
    protected static final String HOTEL = "hotel";
    protected static final String COUNTRY = "country";
    protected static final String FOOD = "food";
    protected static final String ID = "id";
    protected static final String TV = "tv";
    protected static final String CONDITION = "condition";
    protected static final String HOTEL_OPTION = "hotel-option";
    protected static final String POOL = "pool";
    protected static final String TOURIST_VOUCHERS = "touristVouchers";
    protected static final String DAYS = "days";



    public abstract void buildTours(String pathFile);

    public abstract Set<Tour> takeTours();


}
