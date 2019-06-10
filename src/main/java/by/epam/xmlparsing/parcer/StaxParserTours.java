package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class StaxParserTours extends XMLParserAbstract {
    private Set<Tour> tours;
    private XMLInputFactory inputFactory;
    private static final String DEFAULT_PATH_FILE = "data/tours.xml";

    private static final Logger LOGGER = LogManager.getLogger();


    public StaxParserTours(){
        inputFactory = XMLInputFactory.newInstance();
        tours = new HashSet<>();
    }

    @Override
    public Set<Tour> takeTours() {
        return tours;
    }

    public void buildTours(String pathFile) {
        if(pathFile == null){
            pathFile = DEFAULT_PATH_FILE;
        }
        String name;
        try(InputStream inputStream = Files.newInputStream(Paths.get(pathFile))){
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if(name != XMLParserAbstract.TOURIST_VOUCHERS
                            && TourEnumSet.valueOf(name.toUpperCase().trim()) == TourEnumSet.TOUR){
                        Tour tour = staxBuildTour(reader);
                        tours.add(tour);
                    }
                }
            }
        } catch (IOException | XMLStreamException e){
            LOGGER.error("Wrong parsing path :" + e);
        }
    }

    private Tour staxBuildTour(XMLStreamReader reader) throws XMLStreamException {
        Tour tour = null;
        Tour.TourBuilder builder = new Tour.TourBuilder();
        builder.setTypeTour(TypeTour.valueOf(reader.getAttributeValue(0).toUpperCase().trim()));
        builder.setCountry(Country.valueOf(reader.getAttributeValue(1).toUpperCase().trim()));
        builder.setId(reader.getAttributeValue(2));
        String name;

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (name != XMLParserAbstract.HOTEL_OPTION) {
                        switch (TourEnumSet.valueOf(name.toUpperCase().trim())) {
                            case TRANSPORT:
                                builder.setTransport(getXmlText(reader).toUpperCase().trim());
                                break;
                            case HOTEL:
                                builder.setHotelStar(HotelStar.valueOf(getXmlText(reader).toUpperCase().trim()));
                                break;
                            case CITY:
                                builder.setCity(getXmlText(reader));
                                break;
                            case COST:
                                builder.setCost(Integer.parseInt(getXmlText(reader)));
                                break;
                            case DAYS:
                                builder.setDays(Integer.parseInt(getXmlText(reader)));
                                break;
                            case FOOD:
                                builder.setFood(getXmlText(reader));
                                break;
                            case TV:
                                builder.setTv(new Boolean(getXmlText(reader)));
                                break;
                            case CONDITION:
                                builder.setCondition(new Boolean(getXmlText(reader)));
                                break;
                            case POOL:
                                builder.setPool(new Boolean(getXmlText(reader)));
                                break;
                            default:
                                break;
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                      if (name != XMLParserAbstract.HOTEL_OPTION) {
                        if (TourEnumSet.valueOf(name.toUpperCase()) == TourEnumSet.TOUR) {
                            tour = builder.build();
                            return tour;
                        }
                      }
                      break;
            }
        }
        return tour;
    }


        private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
            String text = null;
            if(reader.hasNext()){
                reader.next();
                if(reader.isCharacters()){
                    text = reader.getText();
                }
            }
            return text;
        }
}
