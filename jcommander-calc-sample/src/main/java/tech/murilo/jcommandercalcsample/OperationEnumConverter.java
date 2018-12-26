package tech.murilo.jcommandercalcsample;

import com.beust.jcommander.IStringConverter;

public class OperationEnumConverter implements IStringConverter<Operation> {
    public Operation convert(String name) {
        return Operation.getOperationByName(name);
    }
}