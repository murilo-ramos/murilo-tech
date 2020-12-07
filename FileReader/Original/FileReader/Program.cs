using System;

namespace FileReader
{
    class Program
    {
        static void Main(string[] args)
        {
            var fileReader = new FileReader();
            fileReader.execute("/Users/murilo-tech/filereadersample.txt");
        }
    }
}
