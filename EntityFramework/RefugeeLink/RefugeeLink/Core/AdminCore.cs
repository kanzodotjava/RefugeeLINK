using Microsoft.EntityFrameworkCore;
using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Core
{
    public class AdminCore
    {
        private readonly AdminContext _context;

        public AdminCore(AdminContext context)
        {
            _context = context;
        }


        public async Task<Admin> GetAdminById(long id)
        {
            return await _context.Admin.FindAsync(id);
        }

        public async Task<Admin> GetAdminByUsername(string username)
        {
            return await _context.Admin.FirstOrDefaultAsync(x => x.Username == username);
        }

        public async Task<Admin> CreateAdmin(Admin admin)
        {
            _context.Admin.Add(admin);
            await _context.SaveChangesAsync();
            return admin;
        }

        public async Task<Admin> UpdateAdmin(Admin admin)
        {
            _context.Admin.Update(admin);
            await _context.SaveChangesAsync();
            return admin;
        }

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

        public async Task<List<Admin>> GetAdmins()
        {
            return await _context.Admin.ToListAsync();
        }

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
