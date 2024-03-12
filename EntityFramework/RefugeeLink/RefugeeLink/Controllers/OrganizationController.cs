using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Core;
using RefugeeLink.Data;
using RefugeeLink.Models;
using System.Text.Json;
using System.Text;
using System.Net.Http;

namespace RefugeeLink.Controllers
{
    [Route("organization")]
    [ApiController]
    public class OrganizationController : ControllerBase
    {
        private readonly OrganizationCore _organizationCore;

        private readonly HttpClient _httpClient;

        public OrganizationController(MainContext context)
        {
            this._organizationCore = new OrganizationCore(context);
            _httpClient = new HttpClient();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Organization>> GetOrganizationById(long id)
        {
            var org = await _organizationCore.GetOrganizationById(id);

            if (org == null)
            {
                return NotFound();
            }
            return org;
        }

        [HttpPost]
        public async Task<ActionResult<Organization>> CreateOrganization(Organization org)
        {
            var newOrg = await _organizationCore.CreateOrganization(org);
            if (newOrg == null)
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


        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] OrganizationDTO org)
        {
            var loginSuccess = await _organizationCore.Login(org);
            if (loginSuccess)
            {
                // Successful login
                return Ok(new { message = "Login successful" });
            }
            else
            {
                // Failed login
                return Unauthorized(new { message = "Invalid username or password" });
            }
        }


        [HttpPost("createFormation/{orgId}")]
        public async Task<bool> CreateFormation(Formation formation, long orgId)
        {
            try
            {
                // Serialize the formation object to JSON
                var formationJson = JsonSerializer.Serialize(formation);

                // Attempt to create the formation
                var response = await _httpClient.PostAsync("http://localhost:8080/formation/register", new StringContent(formationJson, Encoding.UTF8, "application/json"));

                if (response.IsSuccessStatusCode)
                {
                    // If creation is successful, read the response and update the formation with the organization ID
                    var createdFormationContent = await response.Content.ReadAsStringAsync();
                    var createdFormation = JsonSerializer.Deserialize<Formation>(createdFormationContent);


                    string formationName = createdFormation.Name;


                    // Assuming the formation object has an Id property that gets populated upon successful creation
                    var updateResponse = await _httpClient.PutAsync($"localhost:8080/formation/{formationName}/organization/{orgId}", null);

                    if (updateResponse.IsSuccessStatusCode)
                    {
                        // If the update is also successful
                        return true;
                    }
                    else
                    {
                        // Handle unsuccessful update
                        Console.WriteLine($"Failed to update formation with orgId. Status Code: {updateResponse.StatusCode}");
                        return false;
                    }
                }
                else
                {
                    // Handle unsuccessful creation
                    Console.WriteLine($"Failed to create formation. Status Code: {response.StatusCode}");
                    return false;
                }
            }
            catch (HttpRequestException e)
            {
                // Handle request-level errors (e.g., network issues)
                Console.WriteLine($"HttpRequestException caught while creating/updating formation: {e.Message}");
            }
            catch (Exception e)
            {
                // Handle other exceptions
                Console.WriteLine($"Exception caught while creating/updating formation: {e.Message}");
            }

            return false;
        }


        //[HttpPost("createFormation/{orgId}")]
        //public async Task CreateFormationAndLinkToOrganization(Formation formation, long orgId)
        //{
        //    var httpClient = new HttpClient();
        //    var formationJson = JsonSerializer.Serialize(formation);
        //    var response = await httpClient.PostAsync("http://localhost:8080/formation/register", new StringContent(formationJson, Encoding.UTF8, "application/json"));

        //    if (response.IsSuccessStatusCode)
        //    {
        //        var responseStream = await response.Content.ReadAsStreamAsync();
        //        var createdFormation = await JsonSerializer.DeserializeAsync<Formation>(responseStream);

        //        await httpClient.PutAsync($"localhost:8080/formation/{createdFormation.Id}/organization/{orgId}", null);
        //    }
        //}
    }
}
