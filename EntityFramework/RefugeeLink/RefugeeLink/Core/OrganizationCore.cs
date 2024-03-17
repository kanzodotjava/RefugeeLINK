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
    /* 
    The OrganizationCore class is part of the RefugeeLink.Core namespace.
    It uses the MainContext class to interact with the database.
    */
    public class OrganizationCore
    {

        // A private field to hold the instance of MainContext.
        private readonly MainContext _context;


        /* 
        The constructor for the OrganizationCore class.
        It takes a MainContext object as a parameter, which is used to interact with the database.
        */
        public OrganizationCore(MainContext context)
        {
            _context = context;
         
        }


        /* 
        Method to get an Organization entity by its ID.
        It uses the FindAsync method provided by Entity Framework Core.
        */
        public async Task<Organization> GetOrganizationById(long id)
        {
            return await _context.Organization.FindAsync(id);
        }


        /* 
        Method to get a list of all Organization entities.
        It uses the ToListAsync method provided by Entity Framework Core.
        */
        public async Task<List<Organization>> GetOrganizations()
        {
            return await _context.Organization.ToListAsync();
        }


        /* 
        Method to create a new Organization entity.
        It adds the entity to the Organization DbSet and then saves the changes to the database.
        */
        public async Task<Organization> CreateOrganization(Organization org)
        {
            _context.Organization.Add(org);
            await _context.SaveChangesAsync();
            return org;
        }


        /* 
        Method to update an existing Organization entity.
        It updates the entity in the Organization DbSet and then saves the changes to the database.
        */
        public async Task<Organization> UpdateOrganization(Organization org)
        {
            _context.Organization.Update(org);
            await _context.SaveChangesAsync();
            return org;
        }


        /* 
        Method to delete an Organization entity by its ID.
        It finds the entity, removes it from the Organization DbSet, and then saves the changes to the database.
        */
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


        /* 
        Method to get an Organization entity by its username.
        It uses the FirstOrDefaultAsync method provided by Entity Framework Core.
        */
        public async Task<Organization> GetOrganizationByUsername(string username)
        {
            return await _context.Organization.FirstOrDefaultAsync(x => x.Username == username);
        }


        /* 
        Method to check the login credentials of an Organization entity.
        It compares the provided username and password with the ones in the database.
        */
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

        /* 
        Method to get an Organization entity's ID by its username.
        It uses the FirstOrDefaultAsync method provided by Entity Framework Core.
        */
        public async Task<long> GetOrganizationByIdUsername(string username)
        {
            var organization = await _context.Organization.FirstOrDefaultAsync(o => o.Username == username);
            return organization?.Id ?? 0;
        }


        /* 
        Method to get an Organization entity's name by its ID.
        It uses the FindAsync method provided by Entity Framework Core.
        */
        public async Task<string> GetOrganizationNameById(long id)
    {
       
        var organization = await _context.Organization.FindAsync(id);

        
        if (organization == null || string.IsNullOrEmpty(organization.Name))
        {
            return null;
        }

        return organization.Name;
    }

    }
}
