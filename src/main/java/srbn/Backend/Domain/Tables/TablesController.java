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

    public Type getType(String name) {
        return typeTable.get(name);
    }

    public boolean containParametter(ArrayList<SymbT> params, String p) {
        for (SymbT param : params) {
            if (param.getName().equals(p)) {
                return false;
            }
        }
        return true;
    }

    public Table<SymbT> getSymbolTable() {
        return symbolTable;
    }

    public Table<Type> getTypeTable() {
        return typeTable;
    }
}
