import java.io.IOException;

/**
 * Created by Terezka on 4/19/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Pohyb Hra = new Pohyb();
        Herni_pole PoleNaHrani = new Herni_pole();
       int[][] Souradnice = {{0,0},{1,0},{2,0}};
       String [][] Grid = PoleNaHrani.VytvorTabulku(10,10);
       Hra.Tah(Grid,Souradnice, 10, 10);
    }
}
