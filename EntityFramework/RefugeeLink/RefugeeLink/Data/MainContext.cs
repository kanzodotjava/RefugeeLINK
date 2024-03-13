using Microsoft.EntityFrameworkCore;
using RefugeeLink.Models;

namespace RefugeeLink.Data
{
    public class MainContext : DbContext
    {
        public MainContext(DbContextOptions<MainContext> options)
    : base(options)
        {
        }

        public DbSet<RefugeeLink.Models.Admin> Admin { get; set; }
        public DbSet<Organization> Organization { get; set; }
        public DbSet<MentorRating> MentorRatings { get; set; }
        public DbSet<RatingDetail> RatingDetails { get; set; }



    }


}
