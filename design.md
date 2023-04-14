#Architecture Dependencies

The Lord of the Rings SDK depends on the following libraries:
* Apache HttpClient: For making HTTP requests to the Lord of the Rings API.
* Gson: For parsing JSON responses from the API.

##Usage
Developers can use the Lord of the Rings SDK in their Java projects by including the SDK as a dependency in their project's build file (e.g., pom.xml for Maven projects). Once the SDK is included as a dependency, developers can import the necessary classes and methods from the SDK and use them in their code to interact with the Lord of the Rings API.
The Lord of the Rings SDK is designed as a Java library that provides a set of classes and methods for interacting with the Lord of the Rings API. The SDK is designed to be easy to use and to provide a simple interface for developers to work with.

##Features
The Lord of the Rings SDK provides the following features:
* Access to the Lord of the Rings API movie endpoints, including:
* List of all movies
* Request one specific movie
* Request all movie quotes for one specific movie (only working for the LotR trilogy)
* Pagination, sorting, and filtering options for API requests.
* Bearer token authentication for API requests.

##Implementation
The Lord of the Rings SDK is implemented in Java and uses the Apache HttpClient library for making HTTP requests to the Lord of the Rings API. The SDK also uses the Gson library for parsing JSON responses from the API.
The SDK provides a set of classes and methods for interacting with the API, including:
LordOfTheRingsApi class: The main entry point for the SDK. This class provides methods for making requests to the API, including methods for retrieving a list of movies, retrieving a specific movie, and retrieving all movie quotes for a specific movie.
getAllMoviesmethod:interacts with an existing API for the Lord of the Rings movies. The method, getAllMovies(), retrieves all the movies from the API with the specified parameters.
The method takes four parameters: limit, page, sortBy, and offset.
* limit specifies the number of movies to retrieve per page.
* page specifies the page number of the results to retrieve.
* sortBy specifies the field to sort the results by, e.g.boxOffice.
* offset specifies the starting index of the movies to retrieve.

These parameters are used to build a query string, which is appended to the API base URL and /movie endpoint to construct the full URL for the API call. 
The queryParams HashMap is populated with these parameters, and the buildQueryString() method constructs the query string from the queryParams HashMap. The resulting URL includes the query string, which specifies the values of these parameters in the API call.
Moviemethod: Represents a single movie in the Lord of the Rings universe. This method retrieving information about the movie, such as its title, release date, budget, and runtime.
MovieQuotemethod: That represents a single quote from a movie in the Lord of the Rings universe. This methodretrieving information about the quote, such as the character who said it and the context in which it was said.
The SDK also provides a set of utility classes and methods for handling pagination, sorting, and filtering options for API requests, as well as for handling authentication with bearer tokens.

##Dependencies
The Lord of the Rings SDK depends on the following libraries:
* Apache HttpClient: For making HTTP requests to the Lord of the Rings API.
* Gson: For parsing JSON responses from the API.

##Usage
Developers can use the Lord of the Rings SDK in their Java projects by including the SDK as a dependency in their project's build file (e.g., pom.xml for Maven projects). Once the SDK is included as a dependency, developers can import the necessary classes and methods from the SDK and use them in their code to interact with the Lord of the Rings API.