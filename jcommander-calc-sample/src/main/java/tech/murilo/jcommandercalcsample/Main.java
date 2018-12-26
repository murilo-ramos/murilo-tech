package tech.murilo.jcommandercalcsample;

import com.beust.jcommander.JCommander;

public class Main {
    
    public static void main(String[] args) {
        CalcParameters parameters = new CalcParameters();
        
        try {
            JCommander.newBuilder()
                      .addObject(parameters)
                      .build()
                      .parse(args);
            
            executeOperation(parameters);
        } catch (Exception ex) {
            System.out.println("Erro no processamento: " + ex.getMessage());
        }
    }
    
    private static void executeOperation(CalcParameters parameters) {
        double firstValue   = parameters.getFirstValue();
        double secondValue  = parameters.getSecondValue();
        Operation operation = parameters.getOperation();
        
        System.out.println("Primeiro valor: " + firstValue);
        System.out.println("Segundo valor: " + secondValue);
        System.out.println("Operação: " + operation.getName());
        
        double result = 0;
        
        switch (operation) {
        case ADD:
            result = firstValue + secondValue;
            break;
        case SUBTRACT:
            result = firstValue - secondValue;
            break;
        case MULTIPLY:
            result = firstValue * secondValue;
            break;
        case DIVIDE:
            result = firstValue / secondValue;
            break;
        }
        
        System.out.println("Resultado: " + result);
    }
}
