using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RefugeeLink.Core;
using RefugeeLink.Data;

namespace RefugeeLink.Controllers
{

    [Route("user")]
    [ApiController]
    public class UserController : ControllerBase
    {

        private readonly MainContext _context;

        private readonly AccountCore _accountCore;

        private readonly UserCore _userCore;

        public UserController(MainContext context, AccountCore accountCore, UserCore userCore)
        {
            _context = context;
            _accountCore = accountCore;
            _userCore = userCore;
        }

        [HttpGet("TierByUsername")]
        public async Task<ActionResult<int>> GetTierByUsername(string username)
        {
            var user = await _context.Users
                                     .Where(u => u.Username == username)
                                     .Select(u => new { u.Tier })
                                     .FirstOrDefaultAsync();

            if (user == null)
            {
                return NotFound("User not found.");
            }

            return Ok(user.Tier);
        }


        [HttpPost("{username}")]
        public async Task<IActionResult> CreateUserWithTier(string username)
        {
            // Fetch account data from external API
            var (Username, AccountCreationDate) = await _accountCore.FetchAccountData(username);

            // Create user with tier in local database
            await _userCore.CreateUserWithTier(Username);

            return Ok();
        }

    }
}
