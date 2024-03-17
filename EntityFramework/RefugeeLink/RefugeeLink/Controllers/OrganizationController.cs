using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Core;
using RefugeeLink.Data;
using RefugeeLink.Models;
using System.Text;
using System.Text.Json;

namespace RefugeeLink.Controllers
{

    /* 
    The RatingController class is part of the RefugeeLink.Controllers namespace.
    It uses the MainContext class to interact with the database and IHttpClientFactory to make HTTP requests.
    */
    [Route("organization")]
    [ApiController]
    public class OrganizationController : ControllerBase
    {
        // Private fields to hold the instances of MainContext and IHttpClientFactory.
        private readonly OrganizationCore _organizationCore;

        private readonly HttpClient _httpClient;


        /* 
        The constructor for the RatingController class.
        It takes a MainContext object and an IHttpClientFactory object as parameters.
        */
        public OrganizationController(MainContext context)
        {
            this._organizationCore = new OrganizationCore(context);
            _httpClient = new HttpClient();
        }


        /* 
        Method to rate a mentor.
        It uses the Post method and expects a RatingDetail object in the request body.
        */
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


        /* 
        Method to check if a user has rated a mentor.
        It uses the Post method and expects a RatingDetail object in the request body.
        */
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


        /* 
        Method to create a new Organization entity.
        It uses the Post method and expects an Organization object in the request body.
        */
        [HttpGet]
        public async Task<ActionResult<ICollection<Organization>>> GetOrganizations()
        {
            return await _organizationCore.GetOrganizations();
        }


        /* 
        Method to get a list of all Organization entities.
        It uses the Get method.
        */
        [HttpGet("/username/{username}")]
        public async Task<ActionResult<Organization>> GetOrganizationByUsername(string username)
        {
            var org = await _organizationCore.GetOrganizationByUsername(username);

            if (org == null)
            {
                return NotFound();
            }

            return org;
        }


        /* 
        Method to get an Organization entity by its username.
        It uses the Get method and expects a username in the URL.
        */
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


        /* 
        Method to delete an Organization entity by its ID.
        It uses the Delete method and expects an ID in the URL.
        */
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



        /* 
        Method to update an existing Organization entity.
        It uses the Put method, expects an ID in the URL and an Organization object in the request body.
        */
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


        /* 
       Method to login an Organization entity.
       It uses the Post method and expects an OrganizationDTO object in the request body.
       */
        [HttpPost("createFormation/{orgId}")]
        public async Task<bool> CreateFormation(Formation formation, long orgId)
        {
            try
            {
                formation.OrganizationId = orgId;
                formation.Status = "AWAITING_START";
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

            return true;
        }


        /* 
        Method to get an Organization entity's ID by its username.
        It uses the Get method and expects a username in the URL.
        */
        [HttpGet("formations/{username}")]
        public async Task<ActionResult<long>> GetOrganizationUsernameById(string username)
        {
            if (string.IsNullOrEmpty(username))
            {
                return NotFound();
            }

            var id = await _organizationCore.GetOrganizationByIdUsername(username);

            if (id == 0)
            {
                return NotFound();
            }



            return id;
        }


        /* 
        Method to get an Organization entity's name by its ID.
        It uses the Get method and expects an ID in the URL.
        */
        [HttpGet("/name/{id}")]
        public async Task<ActionResult<string>> GetOrganizationNameById(long id)
        {
            if (id <= 0)
            {
                return NotFound();
            }

            var organizationName = await _organizationCore.GetOrganizationNameById(id);

            if (string.IsNullOrEmpty(organizationName))
            {
                return NotFound();
            }

            return organizationName;
        }

    }
}
