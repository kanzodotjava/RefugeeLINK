using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{
    [ApiController]
    public class LoginController : ControllerBase
    {
        private readonly HttpClient _httpClient;

        public LoginController()
        {
            _httpClient = new HttpClient();
        }

        //[HttpPost("login")]
        //public async Task<IActionResult> Login([FromBody] LoginRequest loginRequest)
        //{

        //    var jpaApiUrl = "http://localhost:8080/mentor/login";

        //    var response = await _httpClient.PostAsJsonAsync(jpaApiUrl, loginRequest);

        //    if (response.IsSuccessStatusCode)
        //    {
        //        var loginSuccess = await System.Text.Json.JsonSerializer.DeserializeAsync<bool>(await response.Content.ReadAsStreamAsync());
        //        if (loginSuccess)
        //        {
        //            return Ok(new { UserType = "Mentor" } ); // Login success
        //        }
        //    }

        //    return Unauthorized(); // Login failed
        //}


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

            //// If REfugee login fails, try Organization login
            //var organizationApiUrl = "http://localhost:8080/organization/login";
            //var organizationResponse = await _httpClient.PostAsJsonAsync(organizationApiUrl, loginRequest);

            //if (organizationResponse.IsSuccessStatusCode)
            //{
            //    var organizationLoginSuccess = await System.Text.Json.JsonSerializer.DeserializeAsync<bool>(await organizationResponse.Content.ReadAsStreamAsync());
            //    if (organizationLoginSuccess)
            //    {

            //        return Ok(new { UserType = "Organization" });
            //    }
            //}

            // If both logins fail
            return Unauthorized(); // Login failed
        }

    }
}