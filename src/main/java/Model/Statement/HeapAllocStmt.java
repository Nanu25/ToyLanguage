package Model.Statement;

import Model.Exp.Exp;
import Model.PrgState;
import Exception.MyException;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import Exception.TypeException;
import Exception.DictionaryException;
import Utils.MyIDictionary.MyIDictionary;
import Exception.MyTypeException;

public class HeapAllocStmt implements IStmt{


    private String var_name;
    private Exp exp;

    public HeapAllocStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        var heap = state.getHeap();

        if (!symTable.isDefined(var_name)) {
            throw new DictionaryException("Variable " + var_name + " is not declared in the Symbol Table.");
        }

        Value varValue = symTable.lookup(var_name);
        if (!(varValue.getType() instanceof RefType)) {
            throw new TypeException("Variable " + var_name + " is not of type RefType.");
        }

        RefType refType = (RefType) varValue.getType();
        IType locationType = refType.getInner();

        Value expValue = null;
        try {
            expValue = exp.eval(symTable, heap);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

        if (!expValue.getType().equals(locationType)) {
            throw new TypeException("Type of the expression does not match the referenced location type.");
        }

        int newAddress = heap.add(expValue);

        symTable.update(var_name, new RefValue(newAddress, locationType));

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(var_name, exp.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + exp.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType varType = typeEnv.lookup(var_name);
        if (!(varType instanceof RefType)) {
            throw new MyTypeException("HeapAllocStmt: Variable " + var_name + " is not of type RefType.");
        }

        IType innerType = ((RefType) varType).getInner();

        IType expType = exp.typecheck(typeEnv);

        if (!innerType.equals(expType)) {
            throw new MyTypeException("HeapAllocStmt: Expression type " + expType + " does not match the variable's inner type " + innerType + ".");
        }

        return typeEnv;
    }

}
