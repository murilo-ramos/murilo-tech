package tech.murilo.jcommandercalcsample;

public enum Operation {
    
    ADD("Somar"),
    SUBTRACT("Subtrair"),
    MULTIPLY("Multiplicar"),
    DIVIDE("Dividir");
    
    private String name;
    
    private Operation(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static Operation getOperationByName(String name) {
        for (Operation operation : Operation.values()) {
            if (operation.getName().equals(name)) {
                return operation;
            }
        }
        
        throw new IllegalArgumentException("Nome não encontrado na lista de operações: " + name);
    }
}