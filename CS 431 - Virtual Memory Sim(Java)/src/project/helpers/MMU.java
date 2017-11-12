package project.helpers;

import project.helpers.VMAddress;
import project.helpers.OutPut;

public class MMU {
    public MMU(){
        outPut = new OutPut();
    }

    void processVMAddr(VMAddress vma){
        if(vma.getWriteToMemory()){

        }
        else{
            outPut.OutPutToFile(vma);
        }
    }
    public OutPut outPut;
}
