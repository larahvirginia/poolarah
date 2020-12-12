package lapiseira;

import java.util.Scanner;

class Grafite {
    float calibre;
    String dureza;
    int tamanho;
    
    public Grafite (float calibre, String dureza, int tamanho) {
        this.calibre = calibre;
        this.dureza = dureza;
        this.tamanho = tamanho;
    }
    
    public String toString() {
        return " [" + calibre + ":" + dureza + ":" + tamanho + "]";
    }
}

public class Lapiseira {
    float calibre;
    Grafite grafite;
    
    Lapiseira (float calibre) {
        this.calibre = calibre;
        this.grafite = null;
    }
    
    void inserir (Grafite grafite) {
        if (this.grafite != null) {
            System.out.println("já exite grafite");
        } else if (this.calibre < grafite.calibre) {
            System.out.println("calibre incompatível");
        } else {
            this.grafite = grafite;
        }
    }
    
    void remover () {
        if (this.grafite != null) {
            this.grafite = null;
        }
    }
    
    void escrever (int folhas) {
        
    }
    
    public String toString() {
        return "calibre:" + calibre + "," + " grafite:" + grafite;
    }
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        Lapiseira lapiseira = new Lapiseira(0.5f);
        // lapiseira.inserir(new Grafite(0.5f, "HB", 4));
        
        while (true) {
            String line = leitor.nextLine();
            String input[] = line.split(" ");
            if (input[0].equals("end")) {
                System.out.println("fim");
                break;
            }
            
            else if (input[0].equals("inserir")) {
                lapiseira.inserir(new Grafite());
                System.out.println(lapiseira);
            }
            
            else if (input[0].equals("remover")) {
                lapiseira.remover();
                System.out.println(lapiseira);
            }
            
            else if (input[0].equals("escrever")) {
                lapiseira.escrever();
                System.out.println(lapiseira);
            }
        }
        leitor.close();
    }
}