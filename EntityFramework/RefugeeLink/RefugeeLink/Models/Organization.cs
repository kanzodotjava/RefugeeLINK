namespace RefugeeLink.Models
{
    /*
     * The Organization class represents an organization in the system.
     */
    public class Organization
    {
        /*
         * The Id property represents the unique identifier for the Organization.
         */
        public long Id { get; set; }

        /*
         * The Name property represents the name of the Organization.
         */
        public string Name { get; set; }

        /*
         * The Username property represents the username of the Organization.
         */
        public string Username { get; set; }


        /*
       * The Password property represents the password of the Organization.
       */
        public string Password { get; set; }


        /*
        * The ToDTO method converts the Organization object to an OrganizationDTO object.
        *
        */

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
