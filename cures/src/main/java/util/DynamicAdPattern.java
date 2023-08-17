package util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DynamicAdPattern {
	
	public static  List<String>  CreatePattern(LinkedHashMap<String, Double> brandRatios, LinkedHashMap<String, Integer> dynamicStoppingPoints)
	{       	       
	     List<String> ads = generateAds(brandRatios);
	       return ads;       
	}
    
    static List<String> generateAds(LinkedHashMap<String, Double> AdRatios) {
    	int rotationCount=10;
        List<String> ads = new ArrayList<>();
  //      int totalAds = calculateTotalAds(AdRatios, rotationCount);
        Map<String, Integer> AdCounts = new HashMap<>();
        
        for (Map.Entry<String, Double> entry : AdRatios.entrySet()) {
            AdCounts.put(entry.getKey(), 0);
        }
        
//        System.out.println(totalAds);
        for (Map.Entry<String, Double> entry : AdRatios.entrySet()) {
        	
            String Ad = entry.getKey();
   
            double ratio = entry.getValue();
            int adCount = calculateAdCount(ratio, rotationCount);
            int currentAdCount =AdCounts.get(Ad);
                     
            System.out.println(adCount);
            for (int i = 0; i < adCount; i++) {
            	  currentAdCount=AdCounts.get(Ad);
 
            	  ads.add(Ad);
            	AdCounts.put(Ad, currentAdCount + 1);
            }
        }
        
  //      System.out.println(ads);
        return ads;
    }
  private static int calculateTotalAds(LinkedHashMap<String, Double> brandRatios, int rotationCount) {
        int totalAds = 0;
        for (Double ratio : brandRatios.values()) {
            totalAds += calculateAdCount(ratio, rotationCount);
        }
        return totalAds;
    }

    private static int calculateAdCount(double ratio, int totalAds) {
        return (int) Math.round(ratio * totalAds);
    }

    

    
}
