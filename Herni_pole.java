import java.util.*;


/**
 * Created by Terezka on 4/19/2017.
 */
public class Herni_pole {
    Random rand = new Random();
    public int[] Jidlo = new int[2];

    public String[][] VytvorTabulku(int PocetSloupcu, int PocetRadku) {
        String[][] Tabulka = new String[PocetSloupcu][PocetRadku];
        for (int i = 0; i < PocetSloupcu; i++) {
            String[] Radek = new String[PocetSloupcu];
            for (int j = 0; j < PocetSloupcu; j++) {
                Radek[j] = ".";
            }
            Tabulka[i] = Radek;
        }
        return Tabulka;
    }

    public String[][] Potrava(String[][] HraciPole, int Vygenerovano, int[][] Had) {
        if (Vygenerovano == 0) {
            Jidlo[0] = 2;
            Jidlo[1] = 3;
        }
        else {

            Jidlo[0] = rand.nextInt(9);
            Jidlo[1] = rand.nextInt(9);
            while (Arrays.asList(Had).contains(Jidlo)){
                Jidlo[0] = rand.nextInt(9);
                Jidlo[1] = rand.nextInt(9);

            }
        }
        HraciPole[Jidlo[0]][Jidlo[1]] = "?";
        return HraciPole;
    }

    public void VytiskniHerniPole(String[][]Tabulka){
        for (String[] prvek : Tabulka){
            String radek = "";
            for (int k = 0; k < prvek.length; k++ ){
                radek = radek + (prvek[k]);
            }
            System.out.println(radek);
        }
    }
}
