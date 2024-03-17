using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{

    /* 
    The LoginController class is part of the RefugeeLink.Controllers namespace.
    It uses HttpClient to make HTTP requests.
    */
    [ApiController]
    public class LoginController : ControllerBase
    {
        // Private field to hold the instance of HttpClient.
        private readonly HttpClient _httpClient;


        /* 
        The constructor for the LoginController class.
        It initializes the HttpClient object.
        */
        public LoginController()
        {
            _httpClient = new HttpClient();
        }



        /* 
        Method to login a user.
        It uses the Post method and expects a LoginRequest object in the request body.
        */
        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] LoginRequest loginRequest)
        {
            // Try to login as a Mentor first
            var mentorApiUrl = "http://localhost:8080/mentor/login";
            var mentorResponse = await _httpClient.PostAsJsonAsync(mentorApiUrl, loginRequest);

            if (mentorResponse.IsSuccessStatusCode)
            {
                var mentorLoginSuccess = await System.Text.Json.JsonSerializer.DeserializeAsync<bool>(await mentorResponse.Content.ReadAsStreamAsync());
                if (mentorLoginSuccess)
                {

                    return Ok(new { UserType = "Mentor" });
                }
            }

            // If Mentor login fails, try Refugee login
            var refugeeApiUrl = "http://localhost:8080/refugee/login";
            var refugeeResponse = await _httpClient.PostAsJsonAsync(refugeeApiUrl, loginRequest);

            if (refugeeResponse.IsSuccessStatusCode)
            {
                var refugeeLoginSuccess = await System.Text.Json.JsonSerializer.DeserializeAsync<bool>(await refugeeResponse.Content.ReadAsStreamAsync());
                if (refugeeLoginSuccess)
                {

                    return Ok(new { UserType = "Refugee" });
                }
            }

      

            // If both logins fail
            return Unauthorized(); // Login failed
        }

    }
}