package srbn.Backend.Domain.Tables;

import srbn.Backend.Domain.ErrorE;
import srbn.Backend.Domain.SymbT;
import srbn.Backend.Domain.Type;
import srbn.Backend.Domain.TypeEnums.VariableType;

import java.util.ArrayList;

public class TablesController {

    private Table<SymbT> symbolTable;
    private Table<Type> typeTable;
    private int dirCounter = 0;

    public TablesController() {
        this.symbolTable = new Table<>();
        this.typeTable = new Table<>();
    }

    public void updateVars(ArrayList<SymbT> vars) {
        if (vars == null) {
            return;
        }
        for (SymbT var : vars) {
            if(var != null)
            symbolTable.addSymbol(var.getName(), var);
        }
    }

    public void addSymbol(String name, SymbT element) {
        element.setDir(dirCounter);
        symbolTable.addSymbol(name, element);
        dirCounter += element.getMemorySize();
    }

    public void remplaceSymbol(SymbT element) {

        if(element == null){
            return;
        }

        element.setDir(element.getDir());
        symbolTable.addSymbol(element.getName(), element);
    }

    public void addType(String name, Type element) {
        typeTable.addSymbol(name, element);
    }

    public SymbT getSymbol(String name) {
        return symbolTable.get(name);
    }

    public void deleteSymbol(String name) {
        symbolTable.delete(name);
    }

    public void deleteType(String name) {
        typeTable.delete(name);
    }

    public void fillString(String name, String value) throws ErrorE {
        SymbT symbol = getSymbT(name);
        if (symbol.isArray() && symbol.getType() == VariableType.CHAR.ordinal()) {
            Character[] arr = (Character[]) symbol.getArray();
            if (arr.length >= value.length()) {
                for (int i = 0; i < value.length(); i++) {
                    arr[i] = value.charAt(i);
                }
            } else {
                throw new ErrorE("The string is not the same size as the array");
            }
        } else if (symbol.getType() == VariableType.STRING.ordinal()) {
            symbol.setValue(value);
            symbolTable.addSymbol(name, symbol);
        } else {
            throw new ErrorE("The variable is not a string");
        }
    }

    public boolean valuate(String name, int type) throws ErrorE {
        SymbT symbol = getSymbT(name);
        if (symbol.getType() == type) {
            return true;
        } else {
            throw new ErrorE("The variable is not the same type");
        }
    }

    public void setValueFromId(String varName, String valueName) throws ErrorE {
        SymbT value = symbolTable.get(valueName);
        SymbT var = symbolTable.get(varName);

        if (var.getType() != value.getType()) {
            throw new ErrorE("The variable is not the same type");
        }

        var.setValue(value.getValue());
        symbolTable.addSymbol(varName, var);
    }

//todo evaluar para cambiar atributos de symbT, no generales de Type
    public void setValueOnParentType(String varName, String attribName, Object Value) throws ErrorE {

        SymbT var = getSymbT(varName);
        try {
            SymbT attribute = var.getAttribute(attribName);
            attribute.setValue(Value);
            var.setAttribute(attribute);

        } catch (ErrorE e) {
            throw new ErrorE(e.getMessage());
        }
    }

    public void setValueOnParentType(String varName, String attribName, String Value) throws ErrorE {

        SymbT var = getSymbT(varName);
        SymbT value = symbolTable.get(Value);
        try {
            SymbT attribute = var.getAttribute(attribName);
            attribute.setValue(value.getValue());
            var.setAttribute(attribute);

        } catch (ErrorE e) {
            throw new ErrorE(e.getMessage());
        }
    }

    public void setValueOnParentType(String varName, String attribName, String Value, int index) throws ErrorE {

        SymbT var = getSymbT(varName);
        SymbT value = symbolTable.get(Value);

        try {
            SymbT attribute = var.getAttribute(attribName);
            attribute.setValue(value.getFromIndex(index));
            var.setAttribute(attribute);

        } catch (ErrorE e) {
            throw new ErrorE(e.getMessage());
        }
    }


//
    public void setFromIndex(String varName, String valueName, int index) throws ErrorE {
        SymbT value = symbolTable.get(valueName);
        SymbT var = symbolTable.get(varName);

        if (valuate(varName, var.getType())) {
            value.setValue(var.getFromIndex(index));
            symbolTable.addSymbol(valueName, value);

        } else {
            throw new ErrorE("The variable is not an array");
        }

    }

    public void setOnIndex(String varId, int index, String idvalue) throws ErrorE {
        SymbT var = symbolTable.get(varId);
        SymbT value = symbolTable.get(idvalue);

        if (valuate(varId, value.getType()) && valuate(idvalue, var.getType())) {
            var.setInArray(index, value.getValue());
            symbolTable.addSymbol(varId, var);
        } else {
            throw new ErrorE("The variable is not an array");
        }
    }

    public void setOnIndex(String varId, int varInd, String valueId, int valueInd) throws ErrorE {
        SymbT var = symbolTable.get(varId);
        SymbT value = symbolTable.get(valueId);

        if (valuate(varId, var.getType()) && valuate(valueId, value.getType())) {
            if (var.getArray().length > varInd && value.getArray().length > valueInd) {
                var.setInArray(varInd, value.getFromIndex(valueInd));
                symbolTable.addSymbol(varId, var);
            } else {
                throw new ErrorE("The index is out of bounds");
            }
        } else {
            throw new ErrorE("The variable is not an array");
        }
    }

    public void setOnIndex(String id, int index, Object value) throws ErrorE {
        SymbT symbol = getSymbT(id);

        if (symbol.isArray() && valuate(id, symbol.getType())) {
            if (symbol.getArray().length > index && symbol.getInitSize() <= index) {
                symbol.setInArray(index, value);
                addSymbol(id, symbol);
            } else {
                throw new ErrorE("The index is out of bounds");
            }
        } else {
            throw new ErrorE("The variable is not an array");
        }
    }

    private SymbT getSymbT(String name) throws ErrorE {
        SymbT symbol = symbolTable.get(name);
        Type type = typeTable.get(name);
        if (symbol == null && type == null) {
            throw new ErrorE("The variable does not exist");
        } else if (symbol == null && type != null) {
            throw new ErrorE("this is a type, not a variable");
        } else {
            return symbol;
        }
    }

    private boolean isRecordChild(String parent, String child) throws ErrorE {
        SymbT childSymb = symbolTable.get(child);
        SymbT parentSymb = symbolTable.get(parent);
        if (childSymb.getParent().equals(parentSymb.getParent())) {
            return true;
        } else {
            throw new ErrorE("The variable is not an atributte of this record");
        }
    }

    public void updateSymbol(String name, Object value) throws ErrorE {
        SymbT symbol = symbolTable.get(name);
        symbol.setValue(value);
        symbolTable.addSymbol(name, symbol);
    }

    public Type getType(String name) {
        return typeTable.get(name);
    }

    public boolean isSymbol(String name) {
        return symbolTable.get(name) != null;
    }

    public boolean isType(String name) {
        return typeTable.get(name) != null;
    }

    public Table<SymbT> getSymbolTable() {
        return symbolTable;
    }

    public Table<Type> getTypeTable() {
        return typeTable;
    }
}
