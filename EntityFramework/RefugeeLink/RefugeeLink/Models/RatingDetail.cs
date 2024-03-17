namespace RefugeeLink.Models
{
    /*
     * The RatingDetail class represents the details of a rating.
     */
    public class RatingDetail
    {
        
        /*
         * The Id property represents the unique identifier for the RatingDetail.
         */
        public int Id { get; set; }
        
        
        /*
         * The MentorUsername property represents the username of the mentor.
         */
        public string MentorUsername { get; set; }

        /*
         * The UserUsername property represents the username of the user.
         */
        public string UserUsername { get; set; }

        /*
         * The Rating property represents the rating of the mentor.
         */
        public double Rating { get; set; }
    }
}
