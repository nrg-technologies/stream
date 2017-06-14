using System;
using System.Collections.Generic;
using System.Security.Cryptography;
using System.Linq;
using System.IO;
using System.Text;

namespace STREAM
{
    class Token
    {
        private String key;
        private Object[] data;

        public Token(String key)
        {
            this.key = key;
        }

        public void SetData(params Object[] data)
        {
            this.data = data;
        }

        public String Generate(long time)
        {
            StringBuilder builder = new StringBuilder();
            if (data != null)
            {
                foreach (Object o in data)
                {
                    builder.Append(o);
                }
            }
            byte[] array = Encoding.UTF8.GetBytes(
                builder.ToString()
                ).Concat(
                BitConverter.GetBytes(time).Reverse().ToArray()
                ).ToArray();

            HMACSHA1 mac = new HMACSHA1(Encoding.ASCII.GetBytes(key));
            return Convert.ToBase64String(
                MD5.Create().ComputeHash(
                    mac.ComputeHash(array)
                    )
                    );
        }
    }
}