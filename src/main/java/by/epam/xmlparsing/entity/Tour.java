package by.epam.xmlparsing.entity;

import java.util.Objects;

public class Tour {
    private String city;
    private HotelStar hotelStar;
    private TransportType transport;
    private int cost;
    private Country country;
    private TypeTour typeTour;
    private Food food;
    private boolean tv;
    private boolean condition;
    private boolean pool;
    private String id;
    private int days;

    private Tour(TourBuilder builder){
        this.city = builder.city;
        this.cost = builder.cost;
        this.transport = builder.transport;
        this.hotelStar = builder.hotelStar;
        this.country = builder.country;
        this.typeTour = builder.typeTour;
        this.food = builder.food;
        this.tv = builder.tv;
        this.condition = builder.condition;
        this.pool = builder.pool;
        this.id = builder.id;
        this.days = builder.days;
    }

    public static class TourBuilder {
        private String city;
        private HotelStar hotelStar;
        private TransportType transport = TransportType.CAR;
        private int cost;
        private Country country;
        private TypeTour typeTour;
        private Food food = Food.NONE;
        private boolean tv;
        private boolean condition;
        private boolean pool;
        private String id;
        private int days;


        public TourBuilder() {

        }

        public TourBuilder setFood(String food) {
            if (food != null) {
                this.food = Food.valueOf(food.toUpperCase().trim());
            }
            return this;
        }

        public TourBuilder setTv(boolean tv) {
            this.tv = tv;
            return this;
        }

        public TourBuilder setCondition(boolean condition) {
            this.condition = condition;
            return this;
        }

        public TourBuilder setPool(boolean pool) {
            this.pool = pool;
            return this;
        }

        public TourBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public TourBuilder setDays(int days) {
            this.days = days;
            return this;
        }

        public TourBuilder setTypeTour(TypeTour typeTour) {
            this.typeTour = typeTour;
            return this;
        }

        public TourBuilder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public TourBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public TourBuilder setHotelStar(HotelStar hotelStar) {
            this.hotelStar = hotelStar;
            return this;
        }

        public TourBuilder setTransport(String transport) {
            if (transport != null) {
                this.transport = TransportType.valueOf(transport.toUpperCase().trim());
            }
            return this;
        }

        public TourBuilder setCost(int cost) {
            this.cost = cost;
            return this;
        }

        public Tour build() {
            return new Tour(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return cost == tour.cost &&
                tv == tour.tv &&
                condition == tour.condition &&
                pool == tour.pool &&
                days == tour.days &&
                Objects.equals(city, tour.city) &&
                hotelStar == tour.hotelStar &&
                transport == tour.transport &&
                country == tour.country &&
                typeTour == tour.typeTour &&
                food == tour.food &&
                Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, hotelStar, transport, cost, country, typeTour, food, tv, condition, pool, id, days);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\t *** Tour id=" + id + ", country=" + country + ", type tour=" + typeTour);
        builder.append("\nOption{ city:" + city.toUpperCase() + ", transport=" + transport + ", hotel star="
        + hotelStar + ", days=" + days + ", food=" + food + ", cost=" + cost);
        builder.append("\nOther options: TV=" + tv + ", condittion=" + condition + ", pool=" + pool + "}");

        return builder.toString();
    }
}
