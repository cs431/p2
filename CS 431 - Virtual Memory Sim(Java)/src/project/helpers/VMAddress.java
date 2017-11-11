package project.helpers;

public class VMAddress {

    public VMAddress(){}

    public VMAddress(char wtm, String ma) {
        setWriteToMemory(wtm);
        setMemoryAddress(ma);
    }

    public VMAddress(char wtm, String ma, double d){
        setWriteToMemory(wtm);
        setMemoryAddress(ma);
        setDecimalVal(d);
    }

    public char getWriteToMemory() {
        return writeToMemory;
    }

    public void setWriteToMemory(char writeToMemory) {
        this.writeToMemory = writeToMemory;
    }

    public String getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(String memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public double getDecimalVal() {
        return decimalVal;
    }

    public void setDecimalVal(double d) {
        decimalVal = d;
    }

    private char writeToMemory;
    private String memoryAddress;
    private double decimalVal;
}
