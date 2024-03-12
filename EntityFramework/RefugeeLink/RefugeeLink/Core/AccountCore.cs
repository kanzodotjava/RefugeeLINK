using Newtonsoft.Json;

namespace RefugeeLink.Core
{
    public class AccountCore
    {

        private readonly HttpClient _httpClient;

        public AccountCore(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<(string Username, DateTime AccountCreationDate)> FetchAccountData(string username)
        {
            var response = await _httpClient.GetAsync($"http://localhost:8080/mentor/tier/{username}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            var accountData = JsonConvert.DeserializeObject<AccountData>(content);
            return (accountData.Username, accountData.AccountCreationDate);
        }

        private class AccountData
        {
            public string Username { get; set; }
            public DateTime AccountCreationDate { get; set; }
        }

    }
}
