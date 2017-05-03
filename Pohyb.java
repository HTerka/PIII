import com.sun.deploy.util.ArrayUtil;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.Object;
import java.util.Scanner;

/**
 * Created by Terezka on 4/19/2017.
 */
public class Pohyb {
    Herni_pole Jidelnicek = new Herni_pole();
    public void Tah(String[][] Tabulka, int[][] Souradnice, int MaxRadky, int MaxSloupce) throws IOException {
        int vygenerovano = 0;
        int tah = 0;
        int[] Jidlo2 = {-1,-1};
        String[][] Grid = Jidelnicek.Potrava(Tabulka, vygenerovano, Souradnice);
        int[] Jidlo1 = Jidelnicek.Jidlo;

        for (int[] i : Souradnice){
            int xova_had = (i[0]);
            int yova_had = (i[1]);
            Grid[xova_had][yova_had] = "X";
        }
        Jidelnicek.VytiskniHerniPole(Grid);
        int[] nova;
        while (true){
            nova = new int[2];
            tah = tah+1;
            int a = 0;
            Scanner reader = new Scanner(System.in);
            char strana = (reader.next()).charAt(0);
            //DataInputStream dis = new DataInputStream(System.in);
            //String strana = Character.toString(dis.readChar());
            int[] posledni;

            if (strana == 'q') {
                break;
            }
            posledni = Souradnice[Souradnice.length - 1];
            //doprava
            if (strana == 'd') {
                nova[0] = posledni[0];
                nova[1] = posledni[1] + 1;

                if (nova[0] < 0 || nova[1] < 0) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                } else if (nova[0] >= MaxSloupce || nova[1] >= MaxRadky) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                }
                for (int[] prvek : Souradnice) {
                    if (nova == prvek) {
                        System.out.println("Zde uz had je...");
                        a = 1;
                    }
                }
            }
            //nahoru
            else if (strana == 'w') {
                nova[0] = posledni[0] - 1;
                nova[1] = posledni[1];

                if (nova[0] < 0 || nova[1] < 0) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                } else if (nova[0] >= MaxSloupce || nova[1] >= MaxRadky) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                }
                for (int[] prvek : Souradnice) {
                    if (nova == prvek) {
                        System.out.println("Zde uz had je...");
                        a = 1;
                    }
                }
            }
            //dolu
            else if (strana == 's') {
                nova[0] = posledni[0] + 1;
                nova[1] = posledni[1];

                if (nova[0] < 0 || nova[1] < 0) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                } else if (nova[0] >= MaxSloupce || nova[1] >= MaxRadky) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                }
                for (int[] prvek : Souradnice) {
                    if (nova == prvek) {
                        System.out.println("Zde uz had je...");
                        a = 1;
                    }
                }
            }
            //doleva
            else if (strana == 'a') {
                nova[0] = posledni[0];
                nova[1] = posledni[1] - 1;

                if (nova[0] < 0 || nova[1] < 0) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                } else if (nova[0] >= MaxSloupce || nova[1] >= MaxRadky) {
                    System.out.println("Timto tahem se dostanes mimo hraci pole, jed jinak.");
                    continue;
                }
                for (int[] prvek : Souradnice) {
                    if (nova == prvek) {
                        System.out.println("Zde uz had je...");
                        a = 1;
                    }
                }
            } else {
                System.out.println("Takovou svetovou stranu neznam.");
                continue;
            }
            //kdyz had jel do sebe tak hra konci
            if (a == 1) {
                break;
            }
            List<int[]> SouradniceArr = new ArrayList<>();
            for (int index = 0; index < Souradnice.length; index++) {
                SouradniceArr.add(Souradnice[index]);
            }
            SouradniceArr.add(nova);

            //had papa jidlo
            int[] stara = SouradniceArr.get(0);
            if (nova[0] != Jidlo1[0] || nova[1] != Jidlo1[1]) {
                SouradniceArr.remove(0);
                Grid[stara[0]][stara[1]] = ".";
                for (int i = 0; i < SouradniceArr.size(); i++) {
                    Souradnice[i] = SouradniceArr.get(i);
                }
            } else if (nova[0] == Jidlo1[0] && nova[1] == Jidlo1[1]) {
                Souradnice = new int[SouradniceArr.size()][2];
                for (int i = 0; i < SouradniceArr.size(); i++) {
                    Souradnice[i] = SouradniceArr.get(i);
                }
                vygenerovano = vygenerovano + 1;
                Grid = Jidelnicek.Potrava(Tabulka, vygenerovano, Souradnice);
                Jidlo1 = Jidelnicek.Jidlo;
                System.out.println("Ham Ham");
                Jidelnicek.VytiskniHerniPole(Grid);
            }

            if (Jidlo2[0] != -1 && Jidlo2[1] != -1) {
                if (nova == Jidlo2) {
                    SouradniceArr.add(0, stara);
                    vygenerovano = vygenerovano + 1;
                    System.out.println("Ham Ham");
                    Souradnice = new int[SouradniceArr.size()][2];
                    for (int i = 0; i < SouradniceArr.size(); i++) {
                        Souradnice[i] = SouradniceArr.get(i);
                    }
                }
            }
            //generovani druheho papani
            if (tah % 30 == 0) {
                if (Jidlo2[0] != -1 && Jidlo2[1] != -1) {
                    Grid[Jidlo2[0]][Jidlo2[1]] = ".";
                }
                Grid = Jidelnicek.Potrava(Tabulka, vygenerovano, Souradnice);
                Jidlo2 = Jidelnicek.Jidlo;
            }
            for (int[] poloha : Souradnice) {
                int xova_had = (poloha[0]);
                int yova_had = (poloha[1]);
                Grid[xova_had][yova_had] = "X";
            }
            Jidelnicek.VytiskniHerniPole(Grid);

        }
        int pocetX = 0;
        //kolik ma had dilku
        for (String[] radky : Grid){
            for(String pismeno : radky){
                if (pismeno == "X"){
                    pocetX = pocetX + 1;
                }
            }
        }
        System.out.println("Konec hry. Tvůj had má" + pocetX + "políček.");
    }
}
