import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;

public class OperatingSystem {


    private int clock_hand = 0;

    public int handlePageFault(String va, physicalMemory pm, pageTable vpt) throws IOException {

        boolean evicted = false;
        int dbs = 0;

        while (evicted == false) {
            if (vpt.getR((int)pm.getPysMem(clock_hand, 0)) == 1) {
                vpt.setR((int)pm.getPysMem(clock_hand, 0), 0);
            } else {
                // r = 0, evict the page
                if (vpt.getD((int)pm.getPysMem(clock_hand, 0)) == 1){
                    // evicted page has been written to, write back to disk
                    String hex = Integer.toHexString((int)pm.getPysMem(clock_hand, 0));
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }

                    File file = getPageFile(hex);
                    PrintWriter pw = new PrintWriter(new FileWriter(file));

                    for (int i = 1; i < 256; i++) {
                        if (pm.getPysMem(clock_hand, i) != -1) {
                            pw.print(pm.getPysMem(clock_hand, i) + "\n");
                        }
                    }
                    dbs = 1;
                    pw.close();
                }
                // remove the page from pm and replace with needed page
                int vpNum = Integer.parseInt(va.substring(0, 2), 16);
                pm.setPhysMem(clock_hand,0,vpNum);
                File file = getPageFile(va.substring(0,2));
                Scanner scan = new Scanner(file);
                for (int i = 1; i < 256; i++ ) {
                    pm.setPhysMem(clock_hand, i, scan.nextDouble());
                }

                vpt.setPageFrame(vpNum, clock_hand);
                vpt.setV(vpNum,1);
                vpt.setD(vpNum,0);
                vpt.setR(vpNum,1);

                evicted = true;
                scan.close();
            }

            clock_hand++;
            if (clock_hand == 16) {
                clock_hand = 0;
            }
        }

        // return true if d bit was set on page evicted
        return dbs;
    }

    public void resetTables(TLBentries[] tlb, pageTable vpt) {
        for (int i = 0; i < tlb.length; i++) {
            tlb[i].setR(0);
        }
        vpt.resetTble();
    }
    private File getPageFile(String hex) throws IOException{
        String src = "output/Page_Files/original" + hex + ".pg";
        String dest = "output/Page_Files/copy" + hex + ".pg";
        Files.copy(Paths.get(src),Paths.get(dest),StandardCopyOption.REPLACE_EXISTING);
        return new File(dest);
    }
}