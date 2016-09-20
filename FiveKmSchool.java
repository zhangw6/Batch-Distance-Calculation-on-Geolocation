package kk;



/**
33    * This class consists exclusively of static methods that operate on or return
34    * int[]. 
37    *
38    * <p>The methods of this class all throw a <tt>NullPointerException</tt>
39    * if the collections or class objects provided to them are null.
40    *
41    * <p>The documentation for the polymorphic algorithms contained in this class
42    * generally includes a brief description of the <i>implementation</i>.  Such
43    * descriptions should be regarded as <i>implementation notes</i>, rather than
44    * parts of the <i>specification</i>.  Implementors should feel free to
45    * substitute other algorithms, so long as the specification itself is adhered
46    * to.  (For example, the algorithm used by <tt>sort</tt> does not have to be
47    * a mergesort, but it does have to be <i>stable</i>.)
48    *
49    * <p>The "destructive" algorithms contained in this class, that is, the
50    * algorithms that calculate the distance on which they operate, are specified
51    * to throw <tt>UnsupportedOperationException</tt> if the collection does not
52    * support the appropriate mutation ints(s), such as the <tt>set</tt>
53    * method.  
57    *
58    * <p>This class is a member of the
59    * <a href="{@docRoot}/../technotes/guides/collections/index.html">
60    * Java Collections Framework</a>.
61    *
62    * @author  Wen Zhang
64    * @see     Collection
65    * @see     Set
66    * @see     List
67    * @see     Map
69    */


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Arrays;
import java.lang.StringBuilder;


public class FiveKmSchool {
	
	public static void main(String[] args) throws IOException {
    //each property
	    String fName = "/Users/Wen/Downloads/property.csv";
	    String thisLine;
	    
	    FileInputStream fis = new FileInputStream(fName);
	    DataInputStream myInput = new DataInputStream(fis);
	    
	    Integer i = new Integer(0);
	   
	    double[] lat = new double[90000];
     double[] lng = new double[90000];

      
      while ((thisLine = myInput.readLine()) != null) {
	        String strar[] = thisLine.split(",");
           lat[i] = Double.parseDouble(strar[1]);
           lng[i] = Double.parseDouble(strar[2]); 
           i++;
	     }
      
      //each school
	    String fNames = "/Users/Wen/Downloads/facility.csv";
	    String thisLines;
	    Integer j = new Integer(0);

	    FileInputStream fiss = new FileInputStream(fNames);
	    DataInputStream myInputs = new DataInputStream(fiss);
	    
	    double[] latF = new double[3000];
     double[] lngF = new double[3000];
     
	    while ((thisLines = myInputs.readLine()) != null) {
	        String strar[] = thisLines.split(",");
          latF[j] = Double.parseDouble(strar[0]);
          lngF[j] = Double.parseDouble(strar[1]);             
	         j++;

	         
	    }	 


  
	    int[] test = new int[90000];
	    
	    double[] testD = new double[90000];

			  
		test =   minimalDistanceIndex(lat,lng,latF,lngF);
		
		testD =   minimalDistance(lat,lng,latF,lngF);
 		
//		Double[] temp = new Double[636];

// 		
// 		for(int p=0;p<60000;p++){
// 			
// 			
// 			for(int m=0;m<636;m++){
// 				
//              temp[m] = testD[p][m];
//              
// 			  
// 				
// 				
// 			}
// 			
// 		 		Arrays.sort(removeNull(temp));
// 			
// 			
// 				
// 				for(int k=0;k<636;k++){
// 					
// 				testD[p][k] = temp[k];
// 				temp[k] = (double) 0;
// 				
// 				
//
// 			}
// 			
// 			
// 			
// 		}
//		
/**test method here is already written
* but commented out
* check console for output
* 
* 		
**/
//		 for(int k=0;k<10;k++){
//			    System.out.print("id"+k+" ");
//	 		 for (int m = 0; m < 5; m++) {
//	 		    System.out.print(test[k][m]+" ");
//	 	    }
//             System.out.println("");
//	 	    }
		
		 FileWriter writer = new FileWriter("Sydney_Clinic_nearest_facility.csv");

	    for(int k=0;k<=80852;k++){   	
			writer.append(String.valueOf(test[k]));
		    writer.append(",");
		    writer.append(String.valueOf(testD[k]));
		    writer.append(",");
	    
	    writer.append("\n");

	    }
	    
	    
	   writer.close();


		
		
		


	
	}
	

	
	/**@param lat of property
	 *@param lng of property
	 *@return list of schools index within 5km
	 * 
	 * 
	 **/
	
	//magic number here is 5000m
	
	
	public static int[] minimalDistanceIndex (double[] latP,double[] lngP,double[] latS,double[] lngS){

	int[] result = new int[90000];
	Integer j = new Integer(0);
    Integer i = new Integer(0);
    
		
    for(i=0;i<80851;i++) {
        double min = distance(latP[i],latS[0],lngP[i],lngS[0],0,0);
        int k =0;
 	for (j=1;j<=245;j++){
 		if(distance(latP[i],latS[j],lngP[i],lngS[j],0,0)<min){
 			min = distance(latP[i],latS[j],lngP[i],lngS[j],0,0);
 			k=j;
 			
 		}
        
	}
 	result[i] = k;
    }
	
    return result;
    
	}
	
	public static double[] minimalDistance (double[] latP,double[] lngP,double[] latS,double[] lngS){

	double[] result = new double[90000];
	Integer j = new Integer(0);
    Integer i = new Integer(0);
    
		
    for(i=0;i<80851;i++) {
        double min = distance(latP[i],latS[0],lngP[i],lngS[0],0,0);
 	for (j=1;j<=245;j++){
 		if(distance(latP[i],latS[j],lngP[i],lngS[j],0,0)<min){
 			min = distance(latP[i],latS[j],lngP[i],lngS[j],0,0);			
 		}//if
 		
        
	}//for
 	result[i] =min;
    }
	
    return result;
    
	}
	
	
	
	
	

 public static int[][] withIn5000meters (double[] latP,double[] lngP,double[] latS,double[] lngS){
	    
 	Integer j = new Integer(0);
	    Integer i = new Integer(0);
	    int[][] result = new int[90000][646];
    //for 646 schools
	    
	  for(i=0;i<80850;i++) {
		int k =0;

 	for (j=0;j<=310;j++){
 		if (distance(latP[i],latS[j],lngP[i],lngS[j],0,0)>0){
 			
            result[i][k] = j;
            k++;
           
         	 
 		}
 		
 		
 		
 	}
	  }
 	 	
		return result;
 	
 	
 	
 }


 
 public static Double[] removeNull(Double[] ttok){
	
	 for(int i=0;i<ttok.length;i++){
		 
		 if(ttok[i]==null){
			 
			 ttok[i] = (double) 80000;
		 }
	 }
	 
	 
	 
	 return ttok;
	 
	 
	 
	 
	 
 }
 


 
 
 public static Double[][] withIn5000metersD (double[] latP,double[] lngP,double[] latS,double[] lngS){
	    
 	Integer j = new Integer(0);
	    Integer i = new Integer(0);
	    Double[][] result = new Double[90000][646];
    //for 646 schools
	    
	  for(i=0;i<80850;i++) {
		int k =0;
 	for (j=0;j<=310;j++){
 		if (distance(latP[i],latS[j],lngP[i],lngS[j],0,0)>0){
 			
            result[i][k] =  distance(latP[i],latS[j],lngP[i],lngS[j],0,0);
            k++;
            
             
         	 
 		}
 		
 		
 		
 	}
	  }
 	 	
		return result;
 	
 	
 	
 }
 		
 		
 		
 		
 
 
 
 
 /**
 @param  lat of school
 @param  lat of property
 @param  lng of school
 @param  lng of property
 @return the distance between two coordinates
**/
	 public static double distance(double lat1, double lat2, double lon1,
	            double lon2, double el1, double el2) {

	        final int R = 6371; // Radius of the earth
	        Double latDistance = Math.toRadians(lat2 - lat1);
	        Double lonDistance = Math.toRadians(lon2 - lon1);
	        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        double distance = R * c * 1000; // convert to meters
	        double height = el1 - el2;
	        distance = Math.pow(distance, 2) + Math.pow(height, 2);
	        return Math.sqrt(distance);
	    }

	 
	 

	 
	 

}
