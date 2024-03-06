using System.Text.Json.Serialization;

namespace RefugeeLink.Models
{
    public class LoginRequest
    {
        [JsonPropertyName("userName")]
        public string? UserName { get; set; }
        [JsonPropertyName("password")]
        public string? Password { get; set; }

    }
}
