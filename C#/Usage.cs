using System;
using System.Text;

namespace YIGIM
{
    class Usage
    {
        static void Main(string[] args)
        {
            // Token class initialization with API secret key
            Token token = new Token("A8225D2B5651D9447663A1A21F9430CD");
            // Setting agent name
            token.SetData("Agent007");
            // Calculating timestamp
            TimeSpan span = DateTime.UtcNow -
                new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
            // Token generation
            String result = token.Generate(((long)span.TotalMilliseconds) / 200);
            // Printing result
            Console.WriteLine(result);
        }
    }
}
