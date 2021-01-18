using System;
using System.IO;
using System.Collections.Generic;
using Xunit;
using Moq;

namespace FileReader.Tests
{
    public class FileReaderTest
    {

        [Fact]
        public void Should_ThrownFileNotFound_When_ReadNonexistentFile()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);
            Assert.Throws<FileNotFoundException>(() => fileReader.Read("./Resources/NonexistentFile.txt"));
        }

        [Fact]
        public void Should_ThrownFormatException_When_ReadFileWithInvalidHireDateFormat()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);
            Assert.Throws<FormatException>(() => fileReader.Read("./Resources/InvalidHireDateFormatFile.txt"));
        }

        [Fact]
        public void Should_ThrownException_When_ReadFileWithInvalidGender()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);

            var exception = Assert.Throws<Exception>(() => fileReader.Read("./Resources/InvalidGenderFile.txt"));
 
            Assert.Equal("Gênero desconhecido: A", exception.Message);
        }

        [Fact]
        public void Should_ThrownException_When_ReadFileWithInvalidID()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);

            var exception = Assert.Throws<Exception>(() => fileReader.Read("./Resources/InvalidIDFile.txt"));
 
            Assert.Equal("ID não começa com 0013: 001501", exception.Message);
        }

        [Fact]
        public void Should_ThrownException_When_ReadFileWithInvalidHireDate()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);

            var exception = Assert.Throws<Exception>(() => fileReader.Read("./Resources/InvalidHireDateFile.txt"));
 
            Assert.Equal("Data de importação não pode ser menor que 01/01/2019: 01/01/2018 00:00:00", exception.Message);
        }

        [Fact]
        public void Should_AddOneItemToDatabase_When_ReadFileWithOneItem()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);

            fileReader.Read("./Resources/OneItemFile.txt");
 
            employeeRepositoryMock.Verify(c => c.AddAll(It.Is<List<Employee>>(l => l.Count == 1)), Times.Once());
        }

        [Fact]
        public void Should_AddFourItemsToDatabase_When_ReadFileWithFourItems()
        {
            var employeeRepositoryMock = new Mock<IEmployeeRepository>();
            var fileReader = new FileReader(employeeRepositoryMock.Object);

            fileReader.Read("./Resources/MultipleItemsFile.txt");
 
            employeeRepositoryMock.Verify(c => c.AddAll(It.Is<List<Employee>>(l => l.Count == 4)), Times.Once());
        }
    }
}
