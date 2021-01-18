using System;
using System.IO;
using System.Collections.Generic;

namespace FileReader
{
    public class FileReader
    {
        private static readonly string DateFormat = "dd/MM/yyyy";
        private readonly List<Employee> employees = new List<Employee>();
        private readonly IEmployeeRepository employeeRepository;
        private string inputFile;

        public FileReader(IEmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        public void Read(string inputFile)
        {
            this.inputFile = inputFile;

            ReadInputFile();
            ValidateData();
            ImportToDatabase();
        }

        private void ReadInputFile()
        {
            Console.WriteLine("Reading input file");
            
            using (var fileReader = new StreamReader(inputFile))
            {
                string fileLine;
                while((fileLine = fileReader.ReadLine()) != null)  
                {
                    if (string.IsNullOrEmpty(fileLine))
                    {
                        continue;
                    }
                    
                    var fields = fileLine.Split(";");

                    var employee = new Employee
                    {
                        Id     = fields[0],
                        Name   = fields[1],
                        Gender = GetGender(fields[2]),
                        Phone  = fields[3],
                        HireDate   = ParseDate(fields[4]),
                        ImportDate = DateTime.Now
                    };

                    employees.Add(employee);
                }  
            }
        }

        private void ValidateData()
        {
            Console.WriteLine("Validating data");

            var matchId = "0013";
            var minimumHireDate = ParseDate("01/01/2019");

            foreach (var employee in employees)
            {
                //Funcionarios que nao tem seu Id comecando com 0013 pertencem a empresas terceirizadas
                if (!employee.Id.StartsWith(matchId)) 
                {
                    throw new Exception("ID não começa com 0013: " + employee.Id);
                }

                //Funcionarios que foram contratados antes de 01/01/2019 nao devem ser importados a mando do RH
                if (employee.HireDate < minimumHireDate)
                {
                    throw new Exception("Data de importação não pode ser menor que 01/01/2019: " + employee.HireDate);
                }
            }
        }

        private void ImportToDatabase()
        {
            Console.WriteLine("Importing to database");

            employeeRepository.AddAll(employees);
        }

        private string GetGender(string gender)
        {
            switch (gender)
            {
                case "M":
                    return "Masculino";
                case "F":
                    return "Feminino";
                case "NI":
                    return "Não Informado";
                default:
                    throw new Exception("Gênero desconhecido: " + gender);
            }
        }

        private DateTime ParseDate(string date)
        {
            return DateTime.ParseExact(date, DateFormat, System.Globalization.CultureInfo.InvariantCulture);
        }
    }
}