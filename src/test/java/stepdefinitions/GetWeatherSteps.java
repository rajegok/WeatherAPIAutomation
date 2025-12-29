package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.RestAssured;

public class GetWeatherSteps {
    private Response response;
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
   //API key to be passed
    private final String API_KEY = "";
    

    @Given("the weather API is available")
    public void the_weather_api_is_available() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @When("I request the weather for {string}")
    public void i_request_the_weather_for(String city) {
        response = given()
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .when()
                .get(BASE_URL);
    }

    @Then("the response status code should be 200")
    public void the_response_status_code_should_be_200() {
        assertEquals(response.getStatusCode(), 200, "Status code is not 200");
    }

    @Then("the temperature should be in metric units")
    public void the_temperature_should_be_in_metric_units() {
        String unit = response.jsonPath().getString("main.temp");
        assertNotNull(unit, "Temperature is missing in response");
    }
}
