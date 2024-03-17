using Microsoft.EntityFrameworkCore;
using RefugeeLink.Models;

namespace RefugeeLink.Data
{

    /* 
    * The MainContext class is part of the RefugeeLink.Data namespace.
    * It inherits from the DbContext class provided by Entity Framework Core.
    */
    public class MainContext : DbContext
    {
        /* 
          The constructor for the MainContext class.
          It takes a DbContextOptions object as a parameter, which is used to configure the DbContext.
          The base keyword is used to call the base class constructor (DbContext in this case) with the provided options.
         */
        public MainContext(DbContextOptions<MainContext> options)
    : base(options)
        {
        }

        /* 
         DbSet properties represent collections of the specified entities in the database.
         The Admin DbSet represents a collection of Admin entities.
         */
        public DbSet<RefugeeLink.Models.Admin> Admin { get; set; }

        /* 
         The Organization DbSet represents a collection of Organization entities.
         */
        public DbSet<Organization> Organization { get; set; }

        /* 
        The MentorRatings DbSet represents a collection of MentorRating entities.
         */
        public DbSet<MentorRating> MentorRatings { get; set; }

        /* 
        RatingDetails DbSet represents a collection of RatingDetail entities.
        */
        public DbSet<RatingDetail> RatingDetails { get; set; }



    }


}
