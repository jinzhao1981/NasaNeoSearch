
import java.math.BigDecimal;

import org.apache.log4j.Logger;
enum NeoResult_Type {Size, Distance,Count, Done};
class NeoResult {

	public NeoResult(NeoResult_Type t,	BigDecimal s,	String r, String n) {
		type =t;
		value =s;
		refId=r;
		name = n;
	}
	public String getTotalCount() {
		return name;
	}
	public BigDecimal getCount() {
		return value;
	}
	public void setTotalCount(String n) {
		name = n;
	}
	public void setCount(BigDecimal v) {
		value = v;
	}
	public NeoResult_Type getType() {
		return type;
	}
	public BigDecimal getValue() {
		return value;
	}

	public String getRefId() {
		return refId;
	}
	public String getName() {
		return name;
	}
	public String getSizeLogString(String q) {
		return String.format("Find %s Neo (id:%s, name:%s) with Size (%s miles)%n", q, getRefId(), getName(), getValue().divide(new BigDecimal(2)));
			
	}
	public String getDistanceLogString(String q) {
		return String.format("Find %s Neo (id:%s, name:%s) with Distance (%s miles)%n", q, getRefId(), getName(),getValue());
  		
	}
	public boolean isSizeResult() {
	    return getType() == NeoResult_Type.Size;
	}
	public boolean isDistanceResult() {
	    return getType() == NeoResult_Type.Distance;
	}
	public boolean isLargerThan(BigDecimal v) {
	    return getValue().compareTo(v)>0;
	}
    public boolean isCloserThan(BigDecimal v) {
        return getValue().compareTo(v)<0;
    }
	private NeoResult_Type type;
	private BigDecimal value;
	private String refId;
	private String name;
}

/**
* <h1>NeoSearchResultSingleton</h1>
* The NeoSearchResultSingleton class is used to accommodate 
* the search result and display it to the users.
* 
*/
public class NeoSearchResultSingleton{
    static final Logger logger = Logger.getLogger(NeoSearchResultSingleton.class);
		
	static NeoSearchResultSingleton instance=null;
	
	public static synchronized NeoSearchResultSingleton getInstance() {
		if(instance ==null) {
		    instance = new NeoSearchResultSingleton();
        }
		return instance;
	}
	private NeoResult largestNeo = new NeoResult(NeoResult_Type.Size,new BigDecimal(Integer.MIN_VALUE),"0","");
	public NeoResult getLargestNeo() {
        return largestNeo;
    }
    public NeoResult getClosestNeo() {
        return closestNeo;
    }
    public NeoResult getCountNeo() {
        return countNeo;
    }
    private NeoResult closestNeo = new NeoResult(NeoResult_Type.Distance,new BigDecimal(Integer.MAX_VALUE),"0","");
	private NeoResult countNeo = new NeoResult(NeoResult_Type.Count,new BigDecimal(0),"","0");
	public synchronized void processNeo(NeoResult result) {
	  	if(result.isSizeResult() && result.isLargerThan(largestNeo.getValue())) {
	  		largestNeo = result;
	  		String log = largestNeo.getSizeLogString("a Larger"); 
	  		logger.info(log);
	  	}
	  	if(result.isDistanceResult() && result.isCloserThan(closestNeo.getValue())) {
	  		closestNeo = result;
	  		String log = closestNeo.getDistanceLogString("a Closer");
	  		logger.info(log);
	  	}
	  	if(result.getType()==NeoResult_Type.Count) {
	  		countNeo.setCount(countNeo.getCount().add(result.getCount()));
	  		countNeo.setTotalCount(result.getTotalCount());
	  	}
	}
	public void printResult() {
		synchronized (NeoSearchResultSingleton.class) {
			if(countNeo.getValue().compareTo(BigDecimal.ZERO)==0) {
				logger.info("=====No Search Result======");
		   	}
			else {
				logger.info("=====Neo Search Result======");
		   	 	String log = String.format("Searched/Total number of Neos: %s / %s", countNeo.getValue(), countNeo.getTotalCount());
		   	 	logger.info(log);
		   	 	log = largestNeo.getSizeLogString("the Largest");
		   	 	logger.info(log);
				log = closestNeo.getDistanceLogString("the Closest");
				logger.info(log);
			}
		}
	}
	
}

