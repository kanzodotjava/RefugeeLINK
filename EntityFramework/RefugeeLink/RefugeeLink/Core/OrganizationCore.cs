using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualBasic;
using RefugeeLink.Data;
using RefugeeLink.Models;
using System.Net.Http;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;



namespace RefugeeLink.Core
{
    public class OrganizationCore
    {
        private readonly MainContext _context;

        

        public OrganizationCore(MainContext context)
        {
            _context = context;
         
        }

        public async Task<Organization> GetOrganizationById(long id)
        {
            return await _context.Organization.FindAsync(id);
        }

        public async Task<List<Organization>> GetOrganizations()
        {
            return await _context.Organization.ToListAsync();
        }

        public async Task<Organization> CreateOrganization(Organization org)
        {
            _context.Organization.Add(org);
            await _context.SaveChangesAsync();
            return org;
        }

        public async Task<Organization> UpdateOrganization(Organization org)
        {
            _context.Organization.Update(org);
            await _context.SaveChangesAsync();
            return org;
        }

        public async Task<Organization> DeleteOrganization(long id)
        {
            var org = await _context.Organization.FindAsync(id);
            if (org == null)
            {
                return null;
            }

            _context.Organization.Remove(org);
            await _context.SaveChangesAsync();

            return org;
        }


        public async Task<Organization> GetOrganizationByUsername(string username)
        {
            return await _context.Organization.FirstOrDefaultAsync(x => x.Username == username);
        }

        public async Task<bool> Login(OrganizationDTO org)
        {
            var orgInDb = await GetOrganizationByUsername(org.Username);
            if (orgInDb == null)
            {
                return false;
            }

            if (orgInDb.Password == org.Password)
            {
                return true;
            }

            return false;
        }

        
    }




}
