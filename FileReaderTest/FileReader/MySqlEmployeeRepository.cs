using MySqlConnector;
using System.Collections.Generic;

namespace FileReader
{
    public class MySqlEmployeeRepository : IEmployeeRepository
    {
        public void AddAll(List<Employee> employees)
        {
            using (var databaseConnection = CreateDatabaseConnection())
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

        private MySqlConnection CreateDatabaseConnection()
        {
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
    }
}