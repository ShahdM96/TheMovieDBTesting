package APITest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Enforce test order
public class NuxMoviesAPITest {
    private String BASE_URL;
    String apiKey;
    String apiToken;
    int movieID;
    String movieName = "Star Wars";

    @BeforeEach
    void setup() {
        BASE_URL = "https://api.themoviedb.org/3";
        apiKey ="9457f66c26e37c48144faf02ea0e920a";
        apiToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NDU3ZjY2YzI2ZTM3YzQ4MTQ0ZmFmMDJlYTBlOTIwYSIsIm5iZiI6MTczNDg1NTU3MC43NTIsInN1YiI6IjY3NjdjYjkyMzMwYmNlNmVjOTkxMjkwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NP_u87sIGXRJvoWxUPUpdLz-wthsE0WdMSoGsoXjoYw";
        movieID = 11;
    }

    @Test
    @Order(1)
    public void testMovieSearchByID() {
        BASE_URL = "https://api.themoviedb.org/3";

        RestAssured.baseURI = BASE_URL;

/**
        // Retrieve API Key from Environment Variable
        String apiKey = System.getenv("TMDB_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("API Key is missing! Set TMDB_API_KEY as an environment variable.");
        }
**/
        Response response = given().
                        queryParam("api_key", apiKey).
                        log().all().
                        when().
                        get("/movie/11"). // Movie ID 11 -> "Star Wars"
                        then().
                        log().all().
                        statusCode(200). // Validate HTTP 200 OK
                        body("id", equalTo(movieID)).
                        body("title", equalTo("Star Wars")).
                        body("budget", equalTo(11000000)).
                        body("release_date", equalTo("1977-05-25")).
                extract().response();
            //movieName = response.jsonPath().getString("title");
    }


    @Test
    @Order(2)
    public void testAddToWatchlist() {
        BASE_URL = "https://api.themoviedb.org/3";

        RestAssured.baseURI = BASE_URL;
        String requestBody = """
                {
                  "media_type": "movie",
                  "media_id": 11,
                  "watchlist": true
                }""";

        Response response = given().
                header("Authorization", "Bearer " + apiToken).
                header("accept", "application/json").
                header("content-type", "application/json").
                body(requestBody).
                when().
                post("/account/21702994/watchlist"). // Watchlist endpoint
                        then().
                statusCode(201). // Expect HTTP 201
                        extract().response();

        boolean success = response.jsonPath().getBoolean("success");
        assertThat(success, equalTo(true));
    }

    @Test
    @Order(3)
    public void testMovieInWatchlist() {
        BASE_URL = "https://api.themoviedb.org/3";

        RestAssured.baseURI = BASE_URL;

        Response response = given().
                queryParam("api_key", apiToken).
                queryParam("language", "en-US").
                queryParam("page", 1).
                queryParam("sort_by", "created_at.asc").
                log().all().
                header("Authorization", "Bearer " + apiToken).
                when().
                get("/account/21702994/watchlist/movies"). // Watchlist endpoint
                        then().
                log().all().
                statusCode(200).
                extract().response();

        List<String> movieTitles = response.jsonPath().getList("results.title");

        assertThat(movieTitles, hasItem(movieName));

    }

    @Test
    @Order(4)
    public void testDeleteFromWatchlist() {
        BASE_URL = "https://api.themoviedb.org/3";

        RestAssured.baseURI = BASE_URL;
        String requestBody = """
                {
                  "media_type": "movie",
                  "media_id": 11,
                  "watchlist": false
                }""";

        Response response = given().
                header("Authorization", "Bearer " + apiToken).
                header("accept", "application/json").
                header("content-type", "application/json").
                body(requestBody).
                when().
                post("/account/21702994/watchlist"). // Watchlist endpoint
                        then().
                statusCode(200). // Expect HTTP 200 OK
                        extract().response();

        boolean success = response.jsonPath().getBoolean("success");
        assertThat(success, equalTo(true));
    }


}
