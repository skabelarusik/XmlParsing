package by.epam.xmlparsing.factory;

import by.epam.xmlparsing.exception.ParseTourException;
import by.epam.xmlparsing.parcer.DomParserTours;
import by.epam.xmlparsing.parcer.SaxParserTours;
import by.epam.xmlparsing.parcer.StaxParserTours;
import by.epam.xmlparsing.parcer.XMLParserAbstract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TouristVoucherParcerFactory {

    private static Logger LOGGER = LogManager.getLogger();
    private static TouristVoucherParcerFactory parcerFactory;

    private TouristVoucherParcerFactory(){}

    public static TouristVoucherParcerFactory getInstance(){
        if(parcerFactory == null){
            parcerFactory = new TouristVoucherParcerFactory();
        }
        return parcerFactory;
    }

    public XMLParserAbstract createParser(TypeParserFactory type) throws ParseTourException {
        switch (type){
            case DOM:
                LOGGER.info("DOM parcer was created");
                return new DomParserTours();
            case SAX:
                LOGGER.info("SAX parcer was created");
                return new SaxParserTours();
            case STAX:
                LOGGER.info("STAX parcer was created");
                return new StaxParserTours();
            default:
                throw new ParseTourException("Wrong type parcer");
        }
    }
}
