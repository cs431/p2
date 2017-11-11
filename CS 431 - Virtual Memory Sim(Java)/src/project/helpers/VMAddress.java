package project.helpers;

public class VMAddress {

    public VMAddress(){}

    public VMAddress(boolean wtm, String ma) {
        setWriteToMemory(wtm);
        setMemoryAddress(ma);
    }

    public VMAddress(boolean wtm, String ma, double d){
        setWriteToMemory(wtm);
        setMemoryAddress(ma);
        setDecimalVal(d);
    }

    public boolean getWriteToMemory() {
        return writeToMemory;
    }

    public void setWriteToMemory(boolean writeToMemory) {
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

    private boolean writeToMemory;
    private String memoryAddress;
    private double decimalVal;
}
