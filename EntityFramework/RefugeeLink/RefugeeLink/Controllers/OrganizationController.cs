using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Core;
using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{
    [Route("organization")]
    [ApiController]
    public class OrganizationController : ControllerBase
    {
        private readonly OrganizationCore _organizationCore;


        public OrganizationController(MainContext context)
        {
            this._organizationCore = new OrganizationCore(context);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Organization>> GetOrganizationById(long id)
        {
            var org = await _organizationCore.GetOrganizationById(id);

            if(org == null)
            {
                return NotFound();
            }
            return org;
        }

        [HttpPost]
        public async Task<ActionResult<Organization>> CreateOrganization(Organization org)
        {
            var newOrg = await _organizationCore.CreateOrganization(org);
            if(newOrg == null)
            {
                return NotFound();
            }
            return newOrg;
        }

        [HttpGet]
        public async Task<ActionResult<ICollection<Organization>>> GetOrganizations()
        {
            return await _organizationCore.GetOrganizations();
        }

        [HttpGet("/{username}")]
        public async Task<ActionResult<Organization>> GetOrganizationByUsername(string username)
        {
            var org = await _organizationCore.GetOrganizationByUsername(username);

            if (org == null)
            {
                return NotFound();
            }

            return org;
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult<Organization>> DeleteOrganization(long id)
        {
            var deletedOrg = await _organizationCore.DeleteOrganization(id);
            if (deletedOrg == null)
            {
                return NotFound();
            }

            return deletedOrg;
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<Organization>> UpdateOrganization(long id, Organization org)
        {
            if (id != org.Id)
            {
                return BadRequest();
            }
            var updatedOrg = await _organizationCore.UpdateOrganization(org);
            if (updatedOrg == null)
            {
                return NotFound();
            }

            return Ok(updatedOrg);
        }

    }
}
