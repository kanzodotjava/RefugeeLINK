using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RefugeeLink.Data;
using RefugeeLink.Models;
using System;
using System.Globalization;
using System.Text.Json;

namespace RefugeeLink.Controllers
{
    public class RatingController : Controller
    {

        private readonly MainContext _context;
        private readonly IHttpClientFactory _clientFactory;


        public RatingController(MainContext context, IHttpClientFactory clientFactory)
        {
            _context = context;
            _clientFactory = clientFactory;
        }

     
        [HttpPost("/rate-mentor")]
        public async Task<IActionResult> RateMentor([FromBody] RatingDetail ratingDetail)
        {
            // Assuming the external API is the source of truth for the current average rating
            var client = _clientFactory.CreateClient();
            var response = await client.GetAsync($"http://localhost:8080/mentor/get-rating/{ratingDetail.MentorUsername}");

            if (!response.IsSuccessStatusCode)
            {
                return BadRequest("Could not retrieve the current rating from the external API.");
            }

            var contentString = await response.Content.ReadAsStringAsync();
            var currentRating = double.Parse(contentString, CultureInfo.InvariantCulture);

            _context.RatingDetails.Add(ratingDetail);

            // Get or create the mentor rating entity
            var mentorRating = await _context.MentorRatings
                .FirstOrDefaultAsync(mr => mr.Username == ratingDetail.MentorUsername);
            if (mentorRating == null)
            {
                mentorRating = new MentorRating
                {
                    Username = ratingDetail.MentorUsername,
                    AverageRating = currentRating,
                    TotalRaters = 1 // Assuming if it's not found, it's the first rater
                };
                _context.MentorRatings.Add(mentorRating);
            }
            else
            {
                // Calculate the new average based on the external API's current average
                var totalRaters = mentorRating.TotalRaters + 1;
                mentorRating.AverageRating = ((mentorRating.AverageRating * mentorRating.TotalRaters) + ratingDetail.Rating) / totalRaters;
                mentorRating.TotalRaters = totalRaters; // Update the total raters count
            }

            

            await _context.SaveChangesAsync();

            // Update the external API with the new average rating
            var updateResponse = await client.PutAsync($"http://localhost:8080/mentor/update-rating/{ratingDetail.MentorUsername}/{mentorRating.AverageRating}", null);

            if (!updateResponse.IsSuccessStatusCode)
            {
                return BadRequest("Failed to update the mentor's rating through the external API.");
            }

            return Ok();
        }
    }
}
