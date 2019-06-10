package by.epam.xmlparsing.parcer;

import by.epam.xmlparsing.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

public class DomParserTours extends XMLParserAbstract {

    private static final String DEFAULT_PATH_FILE = "data/tours.xml";
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Set<Tour> tours;

    private static final Logger LOGGER = LogManager.getLogger();

    public DomParserTours(){
            factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            LOGGER.error("Wrong DomParcerTours : "+ ex );
        }
    }

    @Override
    public Set<Tour> takeTours() {
        return tours;
    }

    @Override
    public void buildTours(String pathFile){
        if(pathFile == null){
            pathFile = DEFAULT_PATH_FILE;
        }
        try {
            Document document = builder.parse(pathFile);
            Element element = document.getDocumentElement();
            tours = DomTourAnalyzer.listBuilder(element);
        } catch (SAXException  | IOException e) {
            LOGGER.error("Wrong parsing path :" + e);
        }
    }

}

