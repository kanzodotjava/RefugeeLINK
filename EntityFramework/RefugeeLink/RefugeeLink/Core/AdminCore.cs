using Microsoft.EntityFrameworkCore;
using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Core
{
    /* 
    The AdminCore class is part of the RefugeeLink.Core namespace.
    It uses the MainContext class to interact with the database.
    */
    public class AdminCore
    {
        // A private field to hold the instance of MainContext.
        private readonly MainContext _context;


       /* 
       The constructor for the AdminCore class.
       It takes a MainContext object as a parameter, which is used to interact with the database.
       */
        public AdminCore(MainContext context)
        {
            _context = context;
        }


        /* 
        The constructor for the AdminCore class.
        It takes a MainContext object as a parameter, which is used to interact with the database.
        */
        public async Task<Admin> GetAdminById(long id)
        {
            return await _context.Admin.FindAsync(id);
        }


        /* 
        Method to get an Admin entity by its ID.
        It uses the FindAsync method provided by Entity Framework Core.
        */
        public async Task<Admin> GetAdminByUsername(string username)
        {
            return await _context.Admin.FirstOrDefaultAsync(x => x.Username == username);
        }


        /* 
        Method to create a new Admin entity.
        It adds the entity to the Admin DbSet and then saves the changes to the database.
        */
        public async Task<Admin> CreateAdmin(Admin admin)
        {
            _context.Admin.Add(admin);
            await _context.SaveChangesAsync();
            return admin;
        }


       /* 
       Method to update an existing Admin entity.
       It updates the entity in the Admin DbSet and then saves the changes to the database.
       */
        public async Task<Admin> UpdateAdmin(Admin admin)
        {
            _context.Admin.Update(admin);
            await _context.SaveChangesAsync();
            return admin;
        }


        /* 
        Method to delete an Admin entity by its ID.
        It finds the entity, removes it from the Admin DbSet, and then saves the changes to the database.
        */
        public async Task<Admin> DeleteAdmin(long id)
        {
            var admin = await _context.Admin.FindAsync(id);
            if (admin == null)
            {
                return null;
            }

            _context.Admin.Remove(admin);
            await _context.SaveChangesAsync();

            return admin;
        }


       /* 
       Method to get a list of all Admin entities.
       It uses the ToListAsync method provided by Entity Framework Core.
       */
        public async Task<List<Admin>> GetAdmins()
        {
            return await _context.Admin.ToListAsync();
        }


      /* 
      Method to check the login credentials of an Admin entity.
      It compares the provided username and password with the ones in the database.
      */
        public async Task<bool> Login(Admin admin)
        {
            var adminInDb = await GetAdminByUsername(admin.Username);
            if (adminInDb == null)
            {
                return false;
            }

            if (adminInDb.Password == admin.Password)
            {
                return true;
            }

            return false;
        }
    }
}
