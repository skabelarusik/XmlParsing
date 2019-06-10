package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.Country;
import by.epam.xmlparsing.entity.HotelStar;
import by.epam.xmlparsing.entity.Tour;
import by.epam.xmlparsing.entity.TypeTour;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashSet;
import java.util.Set;

class DomTourAnalyzer {
    static Set<Tour> listBuilder(Element root){
        Set<Tour> tours = new HashSet<Tour>();
        NodeList toursList = root.getElementsByTagName(XMLParserAbstract.TOUR);
        Tour tour = null;
        for(int i = 0; i < toursList.getLength(); i++){
            Element tourElement = (Element)toursList.item(i);

            tour = new Tour.TourBuilder()
                    .setCity(getChildValue(tourElement, XMLParserAbstract.CITY).trim())
                    .setTypeTour(TypeTour.valueOf(tourElement.getAttribute(XMLParserAbstract.TYPE_TOUR).toUpperCase().trim()))
                    .setCountry(Country.valueOf(tourElement.getAttribute(XMLParserAbstract.COUNTRY).toUpperCase().trim()))
                    .setCost(new Integer(getChildValue(tourElement, XMLParserAbstract.COST)))
                    .setHotelStar(HotelStar.valueOf(getChildValue(tourElement, XMLParserAbstract.HOTEL).toUpperCase().trim()))
                    .setTransport(getChildValue(tourElement, XMLParserAbstract.TRANSPORT))
                    .setFood((getChildValue(tourElement, XMLParserAbstract.FOOD)))
                    .setId(tourElement.getAttribute(XMLParserAbstract.ID))
                    .setDays(new Integer(getChildValue(tourElement, XMLParserAbstract.DAYS)))
                    .setTv(Boolean.parseBoolean(getChildValue(tourElement, XMLParserAbstract.TV)))
                    .setCondition(Boolean.parseBoolean(getChildValue(tourElement, XMLParserAbstract.CONDITION)))
                    .setPool(Boolean.parseBoolean(getChildValue(tourElement, XMLParserAbstract.POOL)))
                    .build();

            tours.add(tour);
        }
    return tours;
    }

    private static String getChildValue(Element parent, String childName)  {
        if(parent.getElementsByTagName(childName).getLength() == 0) {
            return null;
        }
        NodeList nodeList = parent.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }



}
