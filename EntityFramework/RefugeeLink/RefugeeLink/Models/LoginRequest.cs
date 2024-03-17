using System.Text.Json.Serialization;

namespace RefugeeLink.Models
{
    /*
     * The LoginRequest class represents a request to log in to the system.
     */
    public class LoginRequest
    {
        /*
         *         * The UserName property represents the username of the user.
         *                 */
        [JsonPropertyName("userName")]
        public string? UserName { get; set; }

        /*
         *         *         * The Password property represents the password of the user.
         *                 *                 */
        [JsonPropertyName("password")]
        public string? Password { get; set; }

    }
}
