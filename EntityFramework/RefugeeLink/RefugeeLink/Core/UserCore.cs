using RefugeeLink.Data;
using RefugeeLink.Models;

namespace RefugeeLink.Core
{
    public class UserCore
    {
        private readonly MainContext _dbContext;
        private readonly AccountCore _accountCore;

        public UserCore(MainContext dbContext, AccountCore accountService)
        {
            _dbContext = dbContext;
            _accountCore = accountService;
        }

        public async Task CreateUserWithTier(string username)
        {
            var (Username, AccountCreationDate) = await _accountCore.FetchAccountData(username);

            var now = DateTime.Now;
            var accountAgeYears = (now - AccountCreationDate).Days / 365;

            var user = new User
            {
                Username = Username,
                AccountCreationDate = AccountCreationDate,
                Tier = accountAgeYears >= 2 ? accountAgeYears : 1 // Assigning tier based on account age
            };

            _dbContext.Users.Add(user);
            await _dbContext.SaveChangesAsync();
        }
    }
}
