using Microsoft.EntityFrameworkCore;

namespace RefugeeLink.Data
{
    public class AdminContext : DbContext
    {
        public AdminContext(DbContextOptions<AdminContext> options)
    : base(options)
        {
        }

        public DbSet<RefugeeLink.Models.Admin> Admin { get; set; }
    }


}
