package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaxParserTours extends XMLParserAbstract {

    private static final String DEFAULT_PATH_FILE = "data/tours.xml";
    private XMLReader reader;
    private TourSaxHandler handler;
    Set<Tour> tours;

    private static final Logger LOGGER = LogManager.getLogger();


    public SaxParserTours(){
        try {
            reader = XMLReaderFactory.createXMLReader();
            handler = new TourSaxHandler();
            tours = new HashSet<Tour>();
        } catch (SAXException e) {
            LOGGER.error("Wrong DomParcerTours : "+ e);
        }
    }

    @Override
    public Set<Tour> takeTours() {
        return tours;
    }

    public void buildTours(String pathFile) {
        if(pathFile == null){
            pathFile = DEFAULT_PATH_FILE;
        }
        reader.setContentHandler(handler);
        try {
            reader.parse(pathFile);
            tours = handler.getTours();
        } catch (SAXException | IOException e) {
            LOGGER.error("Wrong parsing path :" + e);
        }

    }
}
