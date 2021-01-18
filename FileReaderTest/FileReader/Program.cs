using System;

namespace FileReader
{
    class Program
    {
        static void Main(string[] args)
        {
            var employeeRepository = new MySqlEmployeeRepository();
            var fileReader = new FileReader(employeeRepository);
            fileReader.Read("/Users/murilo/Git/murilo-tech/FileReader/ArquivoDeExemplo/filereadersample.txt");
        }
    }
}
