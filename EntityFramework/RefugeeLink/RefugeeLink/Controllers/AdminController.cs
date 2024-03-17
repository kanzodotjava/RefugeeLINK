using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Core;
using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{
    /* 
    The AdminController class is part of the RefugeeLink.Controllers namespace.
    It uses the AdminCore class to interact with the database.
    */
    [Route("admin")]
    [ApiController]
    public class AdminController : ControllerBase
    {

        // Private field to hold the instance of AdminCore
        private readonly AdminCore _adminCore;


        /* 
        The constructor for the AdminController class.
        It takes a MainContext object as a parameter, which is used to create an instance of AdminCore.
        */
        public AdminController(MainContext context)
        {
            this._adminCore = new AdminCore(context);
        }


        /* 
        Method to get an Admin entity by its ID.
        It uses the Get method and expects an ID in the URL.
        */
        [HttpGet("{id}")]
        public async Task<ActionResult<Admin>> GetAdminById(long id)
        {
            var admin = await _adminCore.GetAdminById(id);

            if (admin == null)
            {
                return NotFound();
            }

            return admin;
        }


        /* 
        Method to get an Admin entity by its username.
        It uses the Get method and expects a username in the URL.
        */
        [HttpGet("username/{username}")]
        public async Task<ActionResult<Admin>> GetOrganizationByUsername(string username)
        {
            var admin = await _adminCore.GetAdminByUsername(username);

            if (admin == null)
            {
                return NotFound();
            }

            return admin;
        }


        /* 
        Method to create a new Admin entity.
        It uses the Post method and expects an Admin object in the request body.
        */
        [HttpPost]
        public async Task<ActionResult<Admin>> CreateAdmin(Admin admin)
        {
            var newAdmin = await _adminCore.CreateAdmin(admin);
            return CreatedAtAction(nameof(GetAdminById), new { id = newAdmin.Id }, newAdmin);
        }


        /* 
        Method to update an existing Admin entity.
        It uses the Put method, expects an ID in the URL and an Admin object in the request body.
        */
        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateAdmin(long id, Admin admin)
        {
            if (id != admin.Id)
            {
                return BadRequest();
            }

            await _adminCore.UpdateAdmin(admin);

            return NoContent();
        }


        /* 
        Method to delete an Admin entity by its ID.
        It uses the Delete method and expects an ID in the URL.
        */
        [HttpDelete("{id}")]
        public async Task<ActionResult<Admin>> DeleteAdmin(long id)
        {
            var admin = await _adminCore.DeleteAdmin(id);
            if (admin == null)
            {
                return NotFound();
            }

            return admin;
        }


        /* 
        Method to get a list of all Admin entities.
        It uses the Get method.
        */
        [HttpGet]
        public async Task<ActionResult<List<Admin>>> GetAdmins()
        {
            return await _adminCore.GetAdmins();
        }


        /* 
        Method to login an Admin entity.
        It uses the Post method and expects an Admin object in the request body.
        */
        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] Admin admin)
        {
            var loginSuccess = await _adminCore.Login(admin);
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
    }
}
