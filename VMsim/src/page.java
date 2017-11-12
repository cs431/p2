/**
 * Created by Spencer on 11/11/2017.
 */
public class page {
    private int pageFrame;
    private int v;
    private int r;
    private int d;

    page(int p){
        pageFrame = p;
        v = 0;
        r = 0;
        d = 0;
    }
    public void setV( int valid){ valid = v;}
    public void setR(int read){read = r;}
    public void setD(int dirty){dirty = d;}
    public void setPageFrame(int p){p = pageFrame;}
    public int getV(){return v;}
    public int getR(){return r;}
    public int getD(){return d;}
    public int getPageFrame(){return pageFrame;}
}
