namespace RefugeeLink.Models
{
    /*
     *The MentorRating class represents the average rating of a mentor.
     */
    public class MentorRating
    {
        /*
         * The Id property represents the id of the mentor.
         */
        public int Id { get; set; }

        /*
         * The Username property represents the username of the mentor.
         */
        public string Username { get; set; }

       /*
        * The AverageRating property represents the average rating of the mentor.
        */
        public double AverageRating { get; set; }

        /*
         * The TotalRaters property represents the total number of raters of the mentor.
         */
        public int TotalRaters { get; set; }

    }
}
