using System.Text.Json.Serialization;

namespace RefugeeLink.Models
{
    /*
    * The Formation class represents a training or educational program.
    */
    public class Formation
    {
        /*
         * The Id property represents the unique identifier for the Formation.
         */
        [JsonPropertyName("id")]
        public long Id { get; set; }

        /*
         * The Name property represents the name of the Formation.
         */
        [JsonPropertyName("name")]
        public string Name { get; set; }

        /*
         The Description property represents the description of the Formation.               
         */
        [JsonPropertyName("description")]
        public string Description { get; set; }

        /*
         * The NumberOfLessons property represents the number of lessons in the Formation.
         */
        [JsonPropertyName("numberOfLessons")]
        public int NumberOfLessons { get; set; }

        /*
         * The StartDate property indicates when the Formation is set to begin.
         */
        [JsonPropertyName("startDate")]
        public DateTime? StartDate { get; set; }


        /*
         * The EndDate property indicates when the Formation is set to end.
         */
        [JsonPropertyName("duration")]
        public int Duration { get; set; }

        /*
         * The Status property indicates the current status of the Formation.
         */
        [JsonPropertyName("status")]
        public string Status { get; set; } = "AWAITING_START";

        /*
         * The OrganizationId property represents the unique identifier for the Organization that is offering the Formation.
         */
        [JsonPropertyName("organizationId")]
        public long? OrganizationId { get; set; }
    }
}
