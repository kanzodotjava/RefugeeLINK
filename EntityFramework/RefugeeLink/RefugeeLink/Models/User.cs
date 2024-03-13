namespace RefugeeLink.Models
{
    public class User
    {
        public int UserId { get; set; } // Primary key
        public string Username { get; set; }
        public DateTime AccountCreationDate { get; set; }
        public int Tier { get; set; }

    }
}
