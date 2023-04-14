package LordOfTheRingsSDK;

import java.io.IOException;

public class APP {

	 private final static int REQUEST_LIMIT = 100;
	    private final static int REQUEST_PAGE = 2;
	    private final static int REQUEST_OFFSET=2;
	    private static final String REQUEST_SORT="name:asc";
	    
		public static void main(String[] args) {
	        // Example usage of the SDK
	        try {

	            String movieId = "5cd95395de30eff6ebccde5d";
	            String movieQuoteId = "5cd95395de30eff6ebccde5c";

	            String moviesJson = LordOfTheRingsSDK.getAllMovies(REQUEST_LIMIT, REQUEST_PAGE, REQUEST_SORT,REQUEST_OFFSET);
	            System.out.println(moviesJson);
		
	            String movieJson = LordOfTheRingsSDK.getMovieById(movieId);
	            System.out.println(movieJson);

	            String quoteJson = LordOfTheRingsSDK.getAllQuotes(movieQuoteId);
	            System.out.println(quoteJson);
	            
	            String moviesWithFilteringJson = LordOfTheRingsSDK.getAllMovies(REQUEST_LIMIT, REQUEST_PAGE, REQUEST_SORT,REQUEST_OFFSET, "<100", ">0", ">=160");
	            System.out.println(moviesWithFilteringJson);
	            
	     } catch (IOException e) {
	         // Log the error message
	         System.err.println("Error connecting to API: " + e.getMessage());
	         
	         // Provide a user-friendly error message or take other appropriate action
	         // ...
	     } catch (Exception e) {
	         // Log the error message
	         System.err.println("Error: " + e.getMessage());
	         
	         // Provide a user-friendly error message or take other appropriate action
	         // ...
	     }
		}

}
