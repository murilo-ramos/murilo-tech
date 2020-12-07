using System;
using System.IO;
using MySqlConnector;

namespace FileReader
{
    public class FileReader
    {
        public void execute(string f) {

            MySqlConnectionStringBuilder builder = new MySqlConnectionStringBuilder();
            builder.Server = "localhost";
             builder.Port = 3306;
             builder.Database = "company";
             builder.UserID = "company";
             builder.Password = "123456";
            

            using (var conn = new MySqlConnection(builder.ConnectionString)) {
                Console.WriteLine("Opening connection");
                conn.Open();

                    var trans = conn.BeginTransaction();

                using (var readFile = new StreamReader(f))
                {
                    string line;
                      while((line = readFile.ReadLine()) != null)  
                    {
                        if (string.IsNullOrEmpty(line))
                          continue;
                        

                        //lendo e validando
                        string[] campos = line.Split(";");
                        DateTime data;
                        string gender;

                        if (!campos[0].StartsWith("0013")) throw new Exception("ID não começa com 0013: " + campos[0]);
                        

                        switch (campos[2]) {
                            case "M":
                            gender = "Masculino";
                            break;
                            case "F":
                            gender = "Feminino";
                            break;
                            case "NI":
                            gender = "Não Informado";
                            break;
                            default:
                                throw new Exception("Gênero desconhecido: " + campos[2]);
                        }

                        data = DateTime.ParseExact(campos[4], "dd/MM/yyyy", System.Globalization.CultureInfo.InvariantCulture);

                        if (data < DateTime.ParseExact("01/01/2019", "dd/MM/yyyy", System.Globalization.CultureInfo.InvariantCulture))
                        throw new Exception("Data de importação não pode ser menor que 01/01/2019: " + campos[4]);


                        using (MySqlCommand command = conn.CreateCommand()) {   
                            command.Transaction = trans; 
                            command.CommandText = @"INSERT INTO employees (id, name, gender, phone, hire_date, import_date) VALUES (@id, @name, @gender, @phone, @hire_date, @import_date)";
                            command.Parameters.AddWithValue("@id", campos[0]);
                            command.Parameters.AddWithValue("@name", campos[1]);
                            command.Parameters.AddWithValue("@gender", gender);
                            command.Parameters.AddWithValue("@phone", campos[3]);
                            command.Parameters.AddWithValue("@hire_date", data);
                            command.Parameters.AddWithValue("@import_date", DateTime.Now);
                            command.ExecuteNonQuery();
                        }
                    }  
                }

                // connection will be closed by the 'using' block
                Console.WriteLine("Closing connection");
                trans.Commit();
            }
        }
    }
}
