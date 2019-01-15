import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class NeoSearchWorkerTest {

    @Test public void processTest() {
     
        testData.getInstance().init();
        NeoRecordTestFetcher fetcher = new NeoRecordTestFetcher();
        NeoSearchWorker worker = new NeoSearchWorker( fetcher);
        BlockingQueue<Command> q = new ArrayBlockingQueue<Command> (100);
        String url = "test";
        worker.process(q, url);
    
        assertTrue(q.size()==1);
        worker.process(q, url);
        worker.process(q, url);
        worker.process(q, url);
        worker.process(q, url);

        NeoResult neo = NeoSearchResultSingleton.getInstance().getClosestNeo();
        assertTrue(neo.getValue().compareTo(new BigDecimal("1107023.25"))==0);
        neo = NeoSearchResultSingleton.getInstance().getLargestNeo();
        assertTrue(neo.getValue().divide(new BigDecimal(2)).compareTo(new BigDecimal("5.11025107325"))==0);
        
    }
}
