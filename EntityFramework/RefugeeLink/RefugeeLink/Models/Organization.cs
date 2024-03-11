namespace RefugeeLink.Models
{
    public class Organization
    {

        public long Id { get; set; }


        public string Name { get; set; }

        public string Username { get; set; }


        public string Password { get; set; }

        public OrganizationDTO ToDTO()
        {
            return new OrganizationDTO
            {

                Username = this.Username,
                Password = this.Password
                
            };
        }
    }
}
