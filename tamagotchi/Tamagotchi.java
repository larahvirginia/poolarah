import java.util.Scanner;

public class Tamagotchi {
    private int energia;
    private int energiaMax;
    private int fome;
    private int fomeMax;
    private int limpeza;
    private int limpezaMax;
    int diamantes;
    int idade;
    boolean vivo;
    
    Tamagotchi (int energiaMax, int fomeMax, int limpezaMax) {
        this.energiaMax = energiaMax;
        this.fomeMax = fomeMax;
        this.limpezaMax = limpezaMax;
        this.energia = this.energiaMax;
        this.fome = this.fomeMax;
        this.limpeza = this.limpezaMax;
        this.idade = 0;
        this.diamantes = 0;
        this.vivo = true;
    }
    
    private void setEnergia (int value) {
            this.energia = value;
        if (this.energia < 0) {
            this.energia = 0;
            this.vivo = false;
            System.out.println("morreu de fraqueza");
        }
        if (this.energia > energiaMax) {
            this.energia = energiaMax;
        }
    }
    
    int getEnergia () {
        return this.energia;
    }
    
    private void setFome (int value) {
            this.fome = value;
        if (this.fome < 0) {
            this.fome = 0;
            this.vivo = false;
            System.out.println("morreu de fome");
        }
        if (this.fome > fomeMax) {
            this.fome = fomeMax;
        }
    }
    
    int getFome () {
        return this.fome;
    }
    
    private void setLimpeza (int value) {
        this.limpeza = value;
        if (this.limpeza < 0) {
            this.limpeza = 0;
            this.vivo = false;
            System.out.println("morreu de sujeira");
        }
        if (this.limpeza > limpezaMax) {
            this.limpeza = limpezaMax;
        }
    }
    
    int getLimpeza () {
        return this.limpeza;
    }
    
    void brincar () {
        if (!estaVivo())
            return;
        diamantes +=1;
        idade +=1;
        setEnergia (this.energia-2);
        setFome (this.fome-1);
        setLimpeza (this.limpeza-3);
    }
    
    void tomarBanho () {
        if (!estaVivo())
            return;
        idade +=2;
        setEnergia (this.energia-2);
        setFome (this.fome-1);
        setLimpeza (this.limpeza-3);
    }
    
    void comer () {
        if (!estaVivo())
            return;
        idade +=2;
        setEnergia (this.energia+3);
        setFome (this.fome-1);
        setLimpeza (limpezaMax);
    }
    
    void dormir (int t) {
        if (!estaVivo())
            return;
        
        if (this.energia == (energiaMax - 5)) {
            idade +=t;
            setEnergia (energiaMax);
        } else {
            System.out.println("não tô com sono");
        }
        if (this.energia == (energiaMax - 5)) {
            idade +=t;
            setEnergia (energiaMax);
            
        } else {
            System.out.println("não tô com sono");
        }
    }
    
    boolean estaVivo () {
        if (!vivo) {
            System.out.println("Não pode interajir!");
            return false;
        }
        return true;
    }
    
    public String toString() {
        return "energia: " + energia + "/" + energiaMax + " fome: " + fome + "/" + fomeMax + " limpeza: " + limpeza + "/" + limpezaMax;
    }
}

class Jogo {
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner (System.in);
        Tamagotchi hyuck = new Tamagotchi(0, 0, 0);
        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("parametros")) {
                hyuck = new Tamagotchi (Integer.parseInt(ui[1]), Integer.parseInt(ui[2]), Integer.parseInt(ui[3]));
            } else if (ui[0].equals("show")) {
                System.out.println(hyuck);
            } else if (ui[0].equals("brincar")) {
                hyuck.brincar();
                System.out.println(hyuck);
            } else if (ui[0].equals("tomar banho")) {
                hyuck.tomarBanho();
                System.out.println(hyuck);
            } else if (ui[0].equals("comer")) {
                hyuck.comer();
                System.out.println(hyuck);
            } else if (ui[0].equals("dormir")) {
                hyuck.dormir(Integer.parseInt(ui[1]));
                System.out.println(hyuck);
            }
        }
        leitor.close();
    }
}