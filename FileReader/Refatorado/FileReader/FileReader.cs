using System;
using System.IO;
using MySqlConnector;
using System.Collections.Generic;
using System.Dynamic;

namespace FileReader
{
    public class FileReader
    {
        private static readonly string DateFormat = "dd/MM/yyyy";
        private readonly List<dynamic> employees = new List<dynamic>();
        private string inputFile;

        public void read(string inputFile)
        {
            this.inputFile = inputFile;

            readInputFile();
            validateData();
            importToDatabase();
        }

        private void readInputFile()
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

                    dynamic employee = new ExpandoObject();
                    employee.Id     = fields[0];
                    employee.Name   = fields[1];
                    employee.Gender = getGender(fields[2]);
                    employee.Phone  = fields[3];
                    employee.HireDate   = parseDate(fields[4]);
                    employee.ImportDate = DateTime.Now;

                    employees.Add(employee);
                }  
            }
        }

        private void validateData()
        {
            Console.WriteLine("Validating data");

            var matchId = "0013";
            var minimumHireDate = parseDate("01/01/2019");

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

        private void importToDatabase()
        {
            Console.WriteLine("Importing to database");

            using (var databaseConnection = createDatabaseConnection())
            {
                databaseConnection.Open();

                var connectionTransaction = databaseConnection.BeginTransaction();

                foreach (var employee in employees)
                {
                    using (var databaseCommand = databaseConnection.CreateCommand())
                    {   
                        databaseCommand.Transaction = connectionTransaction;
                        
                        databaseCommand.CommandText = @"INSERT INTO employees (id, name, gender, phone, hire_date, import_date) VALUES (@id, @name, @gender, @phone, @hire_date, @import_date)";
                        databaseCommand.Parameters.AddWithValue("@id", employee.Id);
                        databaseCommand.Parameters.AddWithValue("@name", employee.Name);
                        databaseCommand.Parameters.AddWithValue("@gender", employee.Gender);
                        databaseCommand.Parameters.AddWithValue("@phone", employee.Phone);
                        databaseCommand.Parameters.AddWithValue("@hire_date", employee.HireDate);
                        databaseCommand.Parameters.AddWithValue("@import_date", employee.ImportDate);

                        databaseCommand.ExecuteNonQuery();
                    }
                }
                
                connectionTransaction.Commit();
            }
        }

        private string getGender(string gender) {
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

        private MySqlConnection createDatabaseConnection() {
            var databaseConnectionBuilder = new MySqlConnectionStringBuilder
            {
                Server   = "localhost",
                Port     = 3306,
                Database = "company",
                UserID   = "company",
                Password = "123456",
            };

            return new MySqlConnection(databaseConnectionBuilder.ConnectionString);
        }

        private DateTime parseDate(string date) {
            return DateTime.ParseExact(date, DateFormat, System.Globalization.CultureInfo.InvariantCulture);
        }
    }
}