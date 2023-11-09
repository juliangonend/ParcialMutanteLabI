import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner src= new Scanner(System.in);
        String[] adn = new String[6];
        boolean correctLetter=false;
        for (int i = 0; i < adn.length; i++){
            correctLetter=false;
            do {
                System.out.println("Ingrese la linea n: "+i+" de adn");
                adn[i]=src.next().toUpperCase();
                correctLetter=verifyLetter(adn[i]);
            }while(!correctLetter);
        }
        // ADN DE MUTANTE
       // String[] adn = {
        //      "TTCGGA",
        //      "ATTGAC",
        //      "TATTCT",
        //      "TTACTC",
        //      "GTAAAC",
        //      "TCTTAC"
        // };
        //ADN DE NO  MUTANTE
       // String[] adn = {
        //      "TTCGGA",
        //      "ATTGAC",
        //      "TATACT",
        //      "TTCCTC",
        //      "GTAAAC",
        //      "TCTTAC"
        // };
    printAdn(adn);
    boolean isWin=verifyIsMutant(adn);
    if(isWin){
        System.out.println("EL ADN PERTENECE A LOS GENES DE UNA PERSONA MUTANTE");
    }else{
        System.out.println("\"EL ADN NO PERTENECE A LOS GENES DE UNA PERSONA MUTANTE\"");
    }
    }
    public static  boolean verifyLetter(String line){
        if(line.length()!=6){
            System.out.println("Cantidad de caracteres Incorrecta");
            return false;
        }else{
            for (int i=0;i<6;i++ ){
              if(line.charAt(i)!='A' && line.charAt(i)!='T'&& line.charAt(i)!='C'  && line.charAt(i)!='G'){
                  System.out.println("Caracter "+ line.charAt(i)+ " incorrecto ingrese nuevamente la secuencia de caracteres");
                    return false;

              }
            }
        }
        return true;
    }
    public static  void printAdn(String[]adn){
        for (String line:adn
             ) {
            System.out.println("------------------------------------");
                for (int i = 0; i < 6; i++) {
                    System.out.print(" ["+line.charAt(i)+"] ");
                }
            System.out.println(" ");
            System.out.println("------------------------------------");
            }

        }
    public static boolean verifyIsMutant(String[] adn) {
        ArrayList<String> coincidence = new ArrayList<>();
        int isMutant = 0;

        // VERTICAL
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 3; col++) {
                if (adn[row].charAt(col) == adn[row].charAt(1 + col) && adn[row].charAt(1 + col) == adn[row].charAt(2 + col) && adn[row].charAt(2 + col) == adn[row].charAt(3 + col)) {
                    isMutant++;
                    String sequence = adn[row].substring(0, 6);
                    coincidence.add(sequence);
                    break;
                }
            }
        }

        // HORIZONTAL
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 3; row++) {
                if (adn[row].charAt(col) == adn[row + 1].charAt(col) && adn[row + 1].charAt(col) == adn[row + 2].charAt(col) && adn[row + 2].charAt(col) == adn[row + 3].charAt(col)) {
                    isMutant++;
                    String sequence = "" + adn[0].charAt(col) + adn[1].charAt(col) + adn[2].charAt(col) + adn[3].charAt(col) + adn[4].charAt(col) + adn[5].charAt(col);
                    coincidence.add(sequence);
                    break;
                }
            }
        }

        // DIAGONAL
        for (int row = 0; row < 3; row++) {
            if (adn[row].charAt(row) == adn[row + 1].charAt(row + 1) && adn[row + 1].charAt(row + 1) == adn[row + 2].charAt(row + 2) && adn[row + 2].charAt(row + 2) == adn[row + 3].charAt(row + 3)) {
                isMutant++;
                String sequence = "" + adn[0].charAt(0) + adn[1].charAt(1) + adn[2].charAt(2) + adn[3].charAt(3) + adn[4].charAt(4) + adn[5].charAt(5);
                coincidence.add(sequence);
                break;
            }
            if (adn[row].charAt(5 - row) == adn[row + 1].charAt(4 - row) && adn[row + 1].charAt(4 - row) == adn[row + 2].charAt(3 - row) && adn[row + 2].charAt(3 - row) == adn[row + 3].charAt(2 - row)) {
                isMutant++;
                String sequence = "" + adn[0].charAt(5) + adn[1].charAt(4) + adn[2].charAt(3) + adn[3].charAt(2) + adn[4].charAt(1) + adn[5].charAt(0);
                coincidence.add(sequence);
                break;
            }
        }

        // DIAGONALES SECUNDARIAS
        boolean[] secondarySequence = {false, false, false, false};
        for (int row = 0; row < 2; row++) {

            for (int i = 0; i < 2; i++) {
                if (!secondarySequence[0] && adn[i + 1].charAt(i) == adn[i + 2].charAt(i + 1) && adn[i + 2].charAt(i + 1) == adn[i + 3].charAt(i + 2) && adn[i + 3].charAt(i + 2) == adn[i + 4].charAt(i + 3)) {
                    isMutant++;
                    String sequence = "" + adn[1].charAt(0) + adn[2].charAt(1) + adn[3].charAt(2) + adn[4].charAt(3) + adn[5].charAt(4);
                    coincidence.add(sequence);
                    secondarySequence[0] = true;
                }
                if (!secondarySequence[1] && adn[i].charAt(i + 1) == adn[i + 1].charAt(i + 2) && adn[i + 1].charAt(i + 2) == adn[i + 2].charAt(i + 3) && adn[i + 2].charAt(i + 3) == adn[i + 3].charAt(i + 4)) {
                    isMutant++;
                    String sequence = "" + adn[0].charAt(1) + adn[1].charAt(2) + adn[2].charAt(3) + adn[3].charAt(4) + adn[4].charAt(5);
                    coincidence.add(sequence);
                    secondarySequence[1] = true;
                }
                if (!secondarySequence[2] && adn[i + 1].charAt(5 - i) == adn[i + 2].charAt(4 - i) && adn[i + 2].charAt(4 - i) == adn[i + 3].charAt(3 - i) && adn[i + 3].charAt(3 - i) == adn[i + 4].charAt(2 - i)) {
                    isMutant++;
                    String sequence = "" + adn[1].charAt(5) + adn[2].charAt(4) + adn[3].charAt(3) + adn[4].charAt(2) + adn[5].charAt(1);
                    coincidence.add(sequence);
                    secondarySequence[2] = true;
                }
                if (!secondarySequence[3] && adn[i].charAt(4 - i) == adn[i + 1].charAt(3 - i) && adn[i + 1].charAt(3 - i) == adn[i + 2].charAt(2) && adn[i + 2].charAt(2) == adn[i + 3].charAt(1)) {
                    isMutant++;
                    String sequence = "" + adn[1].charAt(4) + adn[2].charAt(3) + adn[3].charAt(2) + adn[4].charAt(1) + adn[5].charAt(0);
                    coincidence.add(sequence);
                    secondarySequence[3] = true;
                }
            }
        }

        // Last two diagonal cases
        if (adn[2].charAt(0) == adn[3].charAt(1) && adn[3].charAt(1) == adn[4].charAt(2) && adn[4].charAt(2) == adn[5].charAt(3)) {
            isMutant++;
            String sequence = "" + adn[2].charAt(0) + adn[3].charAt(1) + adn[4].charAt(2) + adn[5].charAt(3);
            coincidence.add(sequence);
        }
        if (adn[0].charAt(2) == adn[1].charAt(3) && adn[1].charAt(3) == adn[2].charAt(4) && adn[2].charAt(4) == adn[3].charAt(5)) {
            isMutant++;
            String sequence = "" + adn[0].charAt(2) + adn[1].charAt(3) + adn[2].charAt(4) + adn[3].charAt(5);
            coincidence.add(sequence);
        }

        System.out.println("LAS COINCIDENCIAS SON : " + coincidence);
        return isMutant > 1;
    }


}