package Utils.MyILock;

import Exception.MyException;

import java.util.Map;

public interface MyILock {
    int add();
    void update(int address, int value) throws MyException;
    Integer lookup(int address) throws MyException;
    int getFreeLocation();
    boolean isDefined(int address);
    Map<Integer, Integer> getContent();
    Map<Integer, Integer> toMap();

}
