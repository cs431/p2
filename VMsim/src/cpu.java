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

    public static void main(String[] args) throws IOException{
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Text with Virtual Adresses");
        File inFile = new File("test_files\\" + "test_1" + ".txt");
        Scanner input = new Scanner(inFile);
        createTLB();

        while(input.hasNext()){
            int rw = 0;
            rw = input.nextInt();
            System.out.println(rw);

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
        int dirtybit = 0;
        int va = Integer.parseInt(vAdress.substring(0,2), 16);
        int offset = Integer.parseInt(vAdress.substring(2,4),16);
        System.out.println("The address called for:\n" + vAdress);
        System.out.println("Read/Write: " + r);

        int entry = checkTLB(va);
        if(entry != -1){
            if(tlbEntries[entry].getD() != 0) {
                sMiss = false;
                hardmiss = false;
                hit = true;
                tlbEntries[entry].setR(1);
                tlbEntries[entry].setV(1);

                if(physMem.getPysMem(tlbEntries[entry].getVirtualPageNum(),offset) == -1){
                    dirtybit = os.handlePageFault(vAdress, physMem, VPT);
                }
            }

        }
        else{


        }

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

}
