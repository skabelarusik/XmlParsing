package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

class TourSaxHandler extends DefaultHandler {
    private Set<Tour> tours;
    private Tour currentTour = null;
    private TourEnumSet temp;
    private Tour.TourBuilder builder = new Tour.TourBuilder();


    public TourSaxHandler(){
      tours = new HashSet<Tour>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if(qName.equals(XMLParserAbstract.TOUR)){
            builder.setTypeTour(TypeTour.valueOf(atts.getValue(0).toUpperCase().trim()));
            builder.setCountry(Country.valueOf(atts.getValue(1).toUpperCase().trim()));
            builder.setId(atts.getValue(2));

        } else {
            if(localName != XMLParserAbstract.HOTEL_OPTION && localName!= XMLParserAbstract.TOURIST_VOUCHERS){
                temp =TourEnumSet.valueOf(localName.toUpperCase().trim());
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(XMLParserAbstract.TOUR.equals(localName)){
            currentTour = builder.build();
            tours.add(currentTour);
            builder = new Tour.TourBuilder();
           }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();

        if(temp != null){
            switch (temp){
                case CITY:
                    builder.setCity(s);
                    break;
                case FOOD:
                    builder.setFood(s);
                    break;
                case DAYS:
                    builder.setDays(Integer.parseInt(s));
                    break;
                case HOTEL:
                    builder.setHotelStar(HotelStar.valueOf(s.toUpperCase().trim()));
                    break;
                case TRANSPORT:
                    builder.setTransport(s);
                    break;
                case COST:
                    builder.setCost(Integer.parseInt(s));
                    break;
                case TV:
                    builder.setTv(new Boolean(s));
                    break;
                case POOL:
                    builder.setPool(new Boolean(s));
                    break;
                case CONDITION:
                    builder.setCondition(new Boolean(s));
                    break;
                default:
                    break;
            }
        }
        temp = null;
    }

    public Set<Tour> getTours() {
        return tours;
    }


}
