using static System.Runtime.InteropServices.JavaScript.JSType;
using System.Runtime.InteropServices;
using System.Text.Json.Serialization;

namespace RefugeeLink.Models
{
    public class Formation
    {
        [JsonPropertyName("id")]
        public long Id { get; set; }
        [JsonPropertyName("name")]
        public string Name { get; set; }
        [JsonPropertyName("description")]
        public string Description { get; set; }
        [JsonPropertyName("numberOfLessons")]
        public int NumberOfLessons { get; set; }
        [JsonPropertyName("startDate")]
        public DateTime? StartDate { get; set; }

        [JsonPropertyName("duration")]
        public int Duration { get; set; }
        [JsonPropertyName("status")]
        public string Status { get; set; } = "AWAITING_START";
        [JsonPropertyName("organizationId")]
        public long? OrganizationId { get; set; }
    }
}
