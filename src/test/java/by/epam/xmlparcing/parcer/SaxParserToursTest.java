package by.epam.xmlparcing.parcer;

import by.epam.xmlparsing.entity.Tour;
import by.epam.xmlparsing.exception.ParseTourException;
import by.epam.xmlparsing.factory.TouristVoucherParcerFactory;
import by.epam.xmlparsing.factory.TypeParserFactory;
import by.epam.xmlparsing.parcer.XMLParserAbstract;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class SaxParserToursTest {

    private static final String TEST_PATH = "src/test/resources/test_tours.xml";
    private static final String PATH = "data/tours.xml";

    private XMLParserAbstract parserAbstract;
    private XMLParserAbstract parserAbstract2;


    @Test
    public void testBuildTours(){
        try {
            parserAbstract = TouristVoucherParcerFactory.getInstance().createParser(TypeParserFactory.SAX);
            parserAbstract2 = TouristVoucherParcerFactory.getInstance().createParser(TypeParserFactory.SAX);
        } catch (ParseTourException e) {
            e.printStackTrace();
        }
        parserAbstract.buildTours(TEST_PATH);
        parserAbstract2.buildTours(PATH);

        Set<Tour> actual = parserAbstract.takeTours();
        Set<Tour> expected = parserAbstract2.takeTours();
        Assert.assertEquals(actual, expected);
    }
}
