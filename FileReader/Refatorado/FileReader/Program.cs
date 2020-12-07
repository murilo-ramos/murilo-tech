using System;

namespace FileReader
{
    class Program
    {
        static void Main(string[] args)
        {
            var fileReader = new FileReader();
            fileReader.read("/Users/murilo-tech/filereadersample.txt");
        }
    }
}
