import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class NeoSearchResultSingletonTest {

    @Test public void processNeo_Distance() {
        NeoSearchResultSingleton neoSearchResult = NeoSearchResultSingleton.getInstance();
        NeoResult result = new NeoResult(NeoResult_Type.Distance, new BigDecimal("321.57"),   "2917776", "Neo 2917776");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getClosestNeo().getValue().compareTo(new BigDecimal("321.57"))==0);
        
        result = new NeoResult(NeoResult_Type.Distance, new BigDecimal("1321.57"),   "2917777", "Neo 2917777");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getClosestNeo().getValue().compareTo(new BigDecimal("321.57"))==0);
        
        result = new NeoResult(NeoResult_Type.Distance, new BigDecimal("121.57"),   "2917778", "Neo 2917778");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getClosestNeo().getValue().compareTo(new BigDecimal("121.57"))==0);
    }
    @Test public void processNeo_Size() {
        NeoSearchResultSingleton neoSearchResult = NeoSearchResultSingleton.getInstance();
        NeoResult result = new NeoResult(NeoResult_Type.Size, new BigDecimal("321.57"),   "2917776", "Neo 2917776");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getLargestNeo().getValue().compareTo(new BigDecimal("321.57"))==0);
        
        result = new NeoResult(NeoResult_Type.Size, new BigDecimal("1321.57"),   "2917777", "Neo 2917777");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getLargestNeo().getValue().compareTo(new BigDecimal("1321.57"))==0);
        
        result = new NeoResult(NeoResult_Type.Size, new BigDecimal("121.57"),   "2917778", "Neo 2917778");
        neoSearchResult.processNeo(result);
        assertTrue(neoSearchResult.getLargestNeo().getValue().compareTo(new BigDecimal("1321.57"))==0);
     }
}
