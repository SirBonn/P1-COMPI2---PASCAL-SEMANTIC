package srbn.Backend.Domain.Tables;

import srbn.Backend.Domain.SymbT;
import srbn.Backend.Domain.Type;

public class TablesController {

    private Table<SymbT> symbolTable;
    private Table<Type> typeTable;

    public TablesController() {
        this.symbolTable = new Table<>();
        this.typeTable = new Table<>();
    }

    public void addSymbol(String name, SymbT element) {
        symbolTable.addSymbol(name, element);
    }

    public void addType(String name, Type element) {
        typeTable.addSymbol(name, element);
    }

    public SymbT getSymbol(String name) {
        return symbolTable.getSymbol(name);
    }

    public Type getType(String name) {
        return typeTable.getSymbol(name);
    }

    public boolean isSymbol(String name) {
        return symbolTable.getSymbol(name) != null;
    }

    public boolean isType(String name) {
        return typeTable.getSymbol(name) != null;
    }

    public Table<SymbT> getSymbolTable() {
        return symbolTable;
    }

    public Table<Type> getTypeTable() {
        return typeTable;
    }
}
