package tech.murilo.jcommandercalcsample;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class CalcParameters {
    
    @Parameter(names = "primeiro_valor", description = "Primeiro valor da operação", required = true)
    private double firstValue;
    
    @Parameter(names = "segundo_valor", description = "Segundo valor da operação", required = true)
    private double secondValue;
    
    @Parameter(names = "operacao", description = "Tipo da operação", required = true, converter = OperationEnumConverter.class)
    private Operation operation;
    
    public double getFirstValue() {
        return firstValue;
    }
    
    public double getSecondValue() {
        return secondValue;
    }
    
    public Operation getOperation() {
        return operation;
    }
}
