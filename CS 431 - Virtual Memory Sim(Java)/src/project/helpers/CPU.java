package project.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import project.helpers.VMAddress;

public class CPU {
    public CPU(){}
    public VMAddress readVMAddr(String fileName) throws FileNotFoundException{
        String writeToMem;
        String memoryAddr;
        double decimalVal;
        Scanner inFile = new Scanner(new File(fileName));
        if(inFile.hasNext()){
            writeToMem = inFile.next();
            memoryAddr = inFile.next();
            if(writeToMem == "1") {
                decimalVal = inFile.nextDouble();
                return new VMAddress(writeToMem.charAt(0), memoryAddr, decimalVal);
            }
            return new VMAddress(writeToMem.charAt(0), memoryAddr);
        }
        return null;
    }
}
