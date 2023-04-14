package LordOfTheRingsSDK;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LordOfTheRingsSDK {

    private final static String  API_BASE_URL = "https://the-one-api.dev/v2";
    private final static String ACCESS_TOKEN = "-pnQMU-mBrAIXGd9ecuJ";
    private static final String AUTHORIZATION_HEADER = "Bearer " + ACCESS_TOKEN;
    private final static int REQUEST_PERIOD = 10 * 60 * 1000; // 10 minutes in milliseconds
    private final static int HTTP_TOO_MANY_REQUESTS = 429;
    private static Map<String, String> queryParams;
    private static HttpURLConnection conn;
    private static int responseCode;
    private static BufferedReader reader;
    
    /**
     * Retrieve a list of all Lord of the Rings movies
     * @param limit The maximum number of movies to return (default: 100)
     * @param page The page of movies to return (default: 10)
     * @param sort A field to sort by, and a sort order (default: "_id:asc")
     * @param filter A filter to apply to the movies (default: empty)
     * @return JSON string representing the movies
     */

    public static String getAllMovies(int limit, int page, String sortBy, int offset) throws IOException {
        queryParams = new HashMap<String, String>();
        queryParams.put("limit", String.valueOf(limit));
        queryParams.put("page", String.valueOf(page));
        queryParams.put("sort", sortBy);
        queryParams.put("offset",String.valueOf(offset));
        

        String queryString = buildQueryString(queryParams);
        URL url = new URL(API_BASE_URL + "/movie" + queryString);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", AUTHORIZATION_HEADER);
        conn.setRequestProperty("Accept", "application/json");
        
        responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else if (responseCode == HTTP_TOO_MANY_REQUESTS)  { // If the request limit has been reached, wait for the specified period before sending another request
            try {
                Thread.sleep(REQUEST_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getAllMovies(limit, page, sortBy,offset); // Retry the request
        }
            else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();              
        return response.toString();
    }
    
    /**
     * Retrieve a specific Lord of the Rings movie by ID
     * @param id The ID of the movie to retrieve
     * @return JSON string representing the movie
     */
    public static String getMovieById(String id) throws IOException {
		URL url = new URL(API_BASE_URL + "/movie/" + id);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", AUTHORIZATION_HEADER);
        conn.setRequestProperty("Accept", "application/json");            
        responseCode = conn.getResponseCode();
       
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else if (responseCode == HTTP_TOO_MANY_REQUESTS)  { // If the request limit has been reached, wait for the specified period before sending another request
            try {
                Thread.sleep(REQUEST_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getMovieById(id); // Retry the request
        }
            else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
       
    }
    
    /**
     * Retrieve all quotes from a specific Lord of the Rings movie by ID
     * @param id The ID of the movie to retrieve quotes from
     * @return JSON string representing the quotes
     */
    public static String getAllQuotes(String id) throws IOException {
        queryParams = new HashMap<String, String>();
        queryParams.put("limit", "100");
        queryParams.put("movie", String.valueOf(id));
        String queryString = buildQueryString(queryParams);
		URL url = new URL(API_BASE_URL + "/quote" + queryString);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", AUTHORIZATION_HEADER);
        conn.setRequestProperty("Accept", "application/json");

        
         responseCode = conn.getResponseCode();
        
         if (responseCode == HttpURLConnection.HTTP_OK) {
             reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         } else if (responseCode == HTTP_TOO_MANY_REQUESTS)  { // If the request limit has been reached, wait for the specified period before sending another request
             try {
                 Thread.sleep(REQUEST_PERIOD);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             return getAllQuotes(id); // Retry the request
         }
             else {
             reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
         }

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();                   
        return response.toString();     
    }
    
    
       //getAllMovies method to include the filtering functionality based on the query parameters
    
	public static String getAllMovies(int limit, int page,String sortBy,int offset, String budgetInMillions,
			String academyAwardWins, String runtimeInMinutes) throws IOException {

		    queryParams = new HashMap<String, String>();
		  
		    queryParams.put("limit", String.valueOf(limit));
	        queryParams.put("page", String.valueOf(page));
	        queryParams.put("sort", sortBy);
	        queryParams.put("offset",String.valueOf(offset));
	     
        // Add filters as needed

	      
	        if (budgetInMillions != null && !budgetInMillions.isEmpty()) {
	            queryParams.put("budgetInMillions", budgetInMillions.replace("<", "%3C").replace(">", "%3E").replace(">=", "%3E%3D").replace("=<", "%3D%3C").replace("=", "%3D"));  
	        }

	        if (academyAwardWins != null && !academyAwardWins.isEmpty()) {
	            queryParams.put("academyAwardWins", academyAwardWins.replace("<", "%3C").replace(">", "%3E").replace(">=", "%3E%3D").replace("=<", "%3D%3C").replace("=", "%3D"));
	        }

	        if (runtimeInMinutes != null && !runtimeInMinutes.isEmpty()) {
	            queryParams.put("runtimeInMinutes", runtimeInMinutes.replace("<", "%3C").replace(">", "%3E").replace(">=", "%3E%3D").replace("=<", "%3D%3C").replace("=", "%3D"));  
	        }   
	    //     
	       
	        String queryString = buildQueryString(queryParams);
			URL url = new URL(API_BASE_URL + "/movie" + queryString);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", AUTHORIZATION_HEADER);
	        conn.setRequestProperty("Accept", "application/json");
	        
	        responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else if (responseCode == HTTP_TOO_MANY_REQUESTS)  { // If the request limit has been reached, wait for the specified period before sending another request
	            try {
	                Thread.sleep(REQUEST_PERIOD);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            return getAllMovies(limit, page, sortBy,offset,budgetInMillions,academyAwardWins,runtimeInMinutes); // Retry the request
	        }
	            else {
	            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }

	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();              
	        return response.toString();
	    }


        // other methods and fields
        
        public static String buildQueryString(Map<String, String> queryParams) {
            if (queryParams == null || queryParams.isEmpty()) {
                return "";
            }

            StringBuilder queryStringBuilder = new StringBuilder();
            boolean firstParam = true;

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();

                if (firstParam) {
                    queryStringBuilder.append("?");
                    firstParam = false;
                } else {
                    queryStringBuilder.append("&");
                }

                if (paramName.equals("budgetInMillions") || paramName.equals("academyAwardWins") || paramName.equals("runtimeInMinutes")) {
                    queryStringBuilder.append(paramName).append(paramValue);
                } else {
                    queryStringBuilder.append(paramName).append("=").append(paramValue);
                }
            }

            return queryStringBuilder.toString();
        }

}
