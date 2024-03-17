using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RefugeeLink.Data;
using RefugeeLink.Models;
using System.Globalization;

namespace RefugeeLink.Controllers
{

    /* 
    The RatingController class is part of the RefugeeLink.Controllers namespace.
    It uses the MainContext class to interact with the database and IHttpClientFactory to make HTTP requests.
    */
    public class RatingController : Controller
    {
        // Private fields to hold the instances of MainContext and IHttpClientFactory.
        private readonly MainContext _context;
        private readonly IHttpClientFactory _clientFactory;


        /* 
        The constructor for the RatingController class.
        It takes a MainContext object and an IHttpClientFactory object as parameters.
        */
        public RatingController(MainContext context, IHttpClientFactory clientFactory)
        {
            _context = context;
            _clientFactory = clientFactory;
        }



        /* 
        Method to rate a mentor.
        It uses the Post method and expects a RatingDetail object in the request body.
        */
        [HttpPost("/rate-mentor")]
        public async Task<IActionResult> RateMentor([FromBody] RatingDetail ratingDetail)
        {
          

            
            var client = _clientFactory.CreateClient();
            var response = await client.GetAsync($"http://localhost:8080/mentor/get-rating/{ratingDetail.MentorUsername}");

            if (!response.IsSuccessStatusCode)
            {
                return BadRequest("Could not retrieve the current rating from the external API.");
            }

            var contentString = await response.Content.ReadAsStringAsync();
            var currentRating = double.Parse(contentString, CultureInfo.InvariantCulture);

            _context.RatingDetails.Add(ratingDetail);

         
            var mentorRating = await _context.MentorRatings
                .FirstOrDefaultAsync(mr => mr.Username == ratingDetail.MentorUsername);
            if (mentorRating == null)
            {
                mentorRating = new MentorRating
                {
                    Username = ratingDetail.MentorUsername,
                    AverageRating = ratingDetail.Rating,
                    TotalRaters = 1 // Assuming if it's not found, it's the first rater
                };
                _context.MentorRatings.Add(mentorRating);
            }
            else
            {
                var totalRaters = mentorRating.TotalRaters + 1;
                var totalRatingSum = mentorRating.AverageRating * mentorRating.TotalRaters; // Sum of all ratings so far
                var newTotalRatingSum = totalRatingSum + ratingDetail.Rating; // Sum of all ratings including the new one
                mentorRating.AverageRating = newTotalRatingSum / totalRaters; // Calculate the new average
                mentorRating.TotalRaters = totalRaters; // Update the total raters count


            }



            await _context.SaveChangesAsync();

           
            string formattedRating = mentorRating.AverageRating.ToString(CultureInfo.InvariantCulture);
            var updateResponse = await client.PutAsync($"http://localhost:8080/mentor/update-rating/{ratingDetail.MentorUsername}/{formattedRating}", null);


            if (!updateResponse.IsSuccessStatusCode)
            {
                return BadRequest("Failed to update the mentor's rating through the external API.");
            }

            return Ok();
        }



        /* 
        Method to check if a user has rated a mentor.
        It uses the Post method and expects a RatingDetail object in the request body.
        */
        [HttpPost("/has-rated")]
        public async Task<IActionResult> HasRatedMentor([FromBody] RatingDetail ratingDetail)
        {
            var existingRating = await _context.RatingDetails
                .FirstOrDefaultAsync(rd => rd.UserUsername == ratingDetail.UserUsername && rd.MentorUsername == ratingDetail.MentorUsername);

            if (existingRating != null)
            {
               
                return Ok(new { hasRated = true });
            }

            
            return Ok(new { hasRated = false });
        }


        /* 
        Method to get all rating details.
        It uses the Get method.
        */
        [HttpGet("/rating-details")]
        public async Task<IActionResult> GetAllRatingDetails()
        {
            var ratingDetails = await _context.RatingDetails.ToListAsync();

            return Ok(ratingDetails);
        }



        /* 
        Method to get a rating.
        It uses the Post method and expects a RatingDetail object in the request body.
        */
        [HttpPost("/get-rating")]
        public async Task<IActionResult> GetRating([FromBody] RatingDetail ratingDetail)
        {
            var existingRating = await _context.RatingDetails
                .FirstOrDefaultAsync(rd => rd.UserUsername == ratingDetail.UserUsername && rd.MentorUsername == ratingDetail.MentorUsername);

            if (existingRating != null)
            {
                return Ok(existingRating);
            }

            return NotFound();
        }
    }
}
