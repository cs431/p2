import java.io.*;
import java.util.Scanner;

/**
 * Created by Spencer on 11/11/2017.
 */
public class cpu{
    private static TLBentries[] tlbEntries;
    public static physicalMemory physMem = new physicalMemory();
    public static OperatingSystem os = new OperatingSystem();
    public static pageTable VPT = new pageTable();
    public static int pointer = 7;

    public static void main(String[] args) throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        File inFile = new File(args[0]);
        if (!inFile.canRead()) {
            System.out.println("Enter a valid file and try again.");
            System.exit(0);
        }
        
        if (args.length < 1) {
            System.out.println("Enter the file name as a command line argument and try again.");
            System.exit(0);
        }
        
        Scanner input = new Scanner(inFile);
        createTLB();

        while(input.hasNext()){
            int rw = 0;
            rw = input.nextInt();

            double decimal = 0;
            String virtualAdresss;
            virtualAdresss = input.next();

            if(rw == 1){
                decimal = input.nextDouble();
            }
            MMU(rw, virtualAdresss, decimal);

        }
    }
    public static void MMU(int r, String vAdress, double value) throws IOException{
        boolean sMiss = true;
        boolean hardmiss = true;
        boolean hit = false;
        int dirtybit = 1;
        int va = Integer.parseInt(vAdress.substring(0,2), 16);
        int offset = Integer.parseInt(vAdress.substring(2,4),16);
        System.out.println("The address called for:\n" + vAdress);
        System.out.println("Read/Write: " + r);

        int entry = checkTLB(va);
        //vAdress is located in TLB
        if(r == 0) {
            //located in TLB
            if (entry != -1) {
                //dirty bit = 0
                if (tlbEntries[entry].getD() == 0) {
                    sMiss = false;
                    hardmiss = false;
                    hit = true;
                    tlbEntries[entry].setR(1);
                    tlbEntries[entry].setV(1);

                    if (physMem.getPysMem(tlbEntries[entry].getVirtualPageNum(), offset) == -1) {
                        dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                        value = physMem.getPysMem(VPT.getPageFrame(va), offset);
                    } else {
                        value = physMem.getPysMem(tlbEntries[entry].getVirtualPageNum(), offset);
                    }
                }
                //Tlb entry is dirty
                else if (tlbEntries[entry].getD() == 1) {
                    sMiss = false;
                    hardmiss = false;
                    dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                    value = physMem.getPysMem(VPT.getPageFrame(va), offset);
                }

            }
            //not found in TLB softmiss hardmiss
            else {
                if (sMiss) {
                    int pgFrame = VPT.getPageFrame(va);
                    if (pgFrame < 16 && VPT.getD(va) == 0) {
                        hardmiss = false;
                        VPT.setR(va, 1);
                        VPT.setV(va, 1);
                        //page fault
                        if (physMem.getPysMem(pgFrame, offset) == -1) {
                            dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                            value = physMem.getPysMem(VPT.getPageFrame(va), offset);
                        } else {
                            value = physMem.getPysMem(pgFrame, offset);
                            TLB(va, pgFrame);
                        }
                    } else if (pgFrame < 16 && VPT.getD(va) == 1) {
                        hardmiss = false;
                        dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                        value = physMem.getPysMem(VPT.getPageFrame(va), offset);
                    }
                } else if (hardmiss) {
                    dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                    value = physMem.getPysMem(VPT.getPageFrame(va), offset);
                }

            }
        }
        //if read = 1 aka write
        else{
            //located in TLB
            if(entry != -1){
                sMiss = false;
                hardmiss = false;
                hit = true;
                tlbEntries[entry].setV(1);
                tlbEntries[entry].setD(1);
                tlbEntries[entry].setR(1);

                if (physMem.getPysMem(tlbEntries[entry].getVirtualPageNum(),offset) == -1) {

                    dirtybit= os.handlePageFault(vAdress, physMem, VPT);
                    physMem.setPhysMem(VPT.getPageFrame(va), offset, value);

                } else {
                    physMem.setPhysMem(tlbEntries[entry].getPageFrame(),offset, value);
                }
            }
            if(sMiss){
                int pgFrame = VPT.getPageFrame(va);
                if(pgFrame < 16){
                    hardmiss = false;
                    VPT.setV(va,1);
                    VPT.setR(va,1);
                    VPT.setD(va,1);
                    if(physMem.getPysMem(pgFrame,offset) == -1){
                        dirtybit= os.handlePageFault(vAdress, physMem, VPT);
                        physMem.setPhysMem(VPT.getPageFrame(va), offset, value);
                    }
                    else{
                        physMem.setPhysMem(pgFrame,offset,value);
                        TLB(va,pgFrame);
                    }
                }
            }
            if(hardmiss){
                dirtybit= os.handlePageFault(vAdress, physMem, VPT);
                physMem.setPhysMem(VPT.getPageFrame(va), offset, value);
            }
        }
        
        System.out.println("Dirty Bit: " + dirtybit);
        System.out.println("Soft Miss: " + sMiss);
        System.out.println("Hard Miss: " + hardmiss);
        System.out.println("Hit: " + hit);
        System.out.println("Value: " + value + "\n");

    }
    public static int checkTLB(int vAdress){
        for(int i = 0; i < 8; ++i){
            if(tlbEntries[i].getVirtualPageNum() == vAdress)
                return i;
        }
        return -1;
    }
    public static void createTLB(){
        tlbEntries = new TLBentries[8];
        for(int i = 0; i < 8; i++){
            TLBentries entries = new TLBentries();
            tlbEntries[i] = entries;
        }
    }
    //This is wrong will find a better way to FIFO tom.
    public static void TLB(int vNum,int pageFrame){
        int index = 0;
        boolean room = false;
        for (int i = 0; i < 8; i++) {
            if (tlbEntries[i].getPageFrame() == -1) {
                index = i;
                room = true;
            }
        }

        if (room == false) {
            if (pointer + 1 == tlbEntries.length) {
                pointer = 0;
            } else {
                pointer += 1;
            }
            index = pointer;
        }

        tlbEntries[index].setV(1);
        tlbEntries[index].setD(0);
        tlbEntries[index].setR(1);
        tlbEntries[index].setPageFrame(pageFrame);
        tlbEntries[index].setVPN(vNum);
    }


}
