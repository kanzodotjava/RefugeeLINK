using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{
    public class LoginController : ControllerBase
    {
        private readonly HttpClient _httpClient;

        public LoginController()
        {
            _httpClient = new HttpClient();
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] LoginRequest loginRequest)
        {
            var jpaApiUrl = "http://localhost:8080/mentor/login";

            var response = await _httpClient.PostAsJsonAsync(jpaApiUrl, loginRequest);

            if (response.IsSuccessStatusCode)
            {
                var loginSuccess = await System.Text.Json.JsonSerializer.DeserializeAsync<bool>(await response.Content.ReadAsStreamAsync());
                if (loginSuccess)
                {
                    return Ok(); // Login success
                }
            }

            return Unauthorized(); // Login failed
        }
    }
}