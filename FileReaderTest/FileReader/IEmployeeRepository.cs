using System.Collections.Generic;

namespace FileReader
{
    public interface IEmployeeRepository
    {
        void AddAll(List<Employee> employees);
    }
}