using Microsoft.AspNetCore.Mvc;
using RefugeeLink.Core;
using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Controllers
{
    [Route("admin")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly AdminCore _adminCore;

       public AdminController(MainContext context)
        {
            this._adminCore = new AdminCore(context);
        }


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

        [HttpPost]
        public async Task<ActionResult<Admin>> CreateAdmin(Admin admin)
        {
            var newAdmin = await _adminCore.CreateAdmin(admin);
            return CreatedAtAction(nameof(GetAdminById), new { id = newAdmin.Id }, newAdmin);
        }

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

        [HttpGet]
        public async Task<ActionResult<List<Admin>>> GetAdmins()
        {
            return await _adminCore.GetAdmins();
        }

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
