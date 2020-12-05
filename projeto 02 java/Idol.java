package idol;
// o donghyuck é um cantor e o programa é sobre as coisas que ele faz na vida :)
// selca é selfie hehe

import java.util.Scanner;

public class Idol {
    int potenciavocal;
    int energia;
    boolean sPreguiça = true; // não está com preguiçca
    boolean nLesionado = true; // não está lesionado
    
    Idol(int potenciavocal, int energia, boolean sPreguiça, boolean nLesionado){
        this.potenciavocal = potenciavocal;
        this.energia = energia;
        this.sPreguiça = sPreguiça;
        this.nLesionado = nLesionado;
    }
    
    void gravar () {
        if (potenciavocal > 0) {
            System.out.println("Gravando!");
            potenciavocal -= 1;
            energia -=1;
        } else {
            System.out.println("Cof, cof! Preciso de um copo d'água!");
        }
    }
    
    void postarselca () {
        if (sPreguiça == false && energia > 0) {
            System.out.println("Diga xis!");
        } else {
            System.out.println("Não vou postar nada naum, tô cansadinho");
        }
    }
    
    void descansar() {
        System.out.println("Mimindo");
        energia +=1;
        potenciavocal +=1;
        sPreguiça = false;
    }
    
    void dançar() {
        if (nLesionado == false && energia > 0) {
            System.out.println("Bomba! Pra balançar esse aqui é BOMBA!");
            energia -= 1;
        } else {
            System.out.println("Ai! Não posso dançar hoje!");
        }
    }
    
    public String toString() {
        return "Donghyuck : pv:" + potenciavocal + " energia:" + energia + " lesionado?" + nLesionado + " preguiçoso?" + sPreguiça;
    }
    
    
    public static void main(String[] args) {
        
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Qual a potência vocal do Donghyuck?");
        int potenciavocal = leitor.nextInt();
        System.out.println("Quanto de energia o Donghyuck tem?");
        int energia = leitor.nextInt();
        System.out.println("Donghyuck está saudável?");
        boolean nLesionado = leitor.nextBoolean();
        System.out.println("Donghyuck está sem peguiça de fazer as coisas?");
        boolean sPreguiça = leitor.nextBoolean();
        
        leitor.close();
        
        Idol donghyuck = new Idol(potenciavocal, energia, nLesionado, sPreguiça);
        
        System.out.println(donghyuck);
        donghyuck.gravar();
        donghyuck.gravar();
        donghyuck.gravar();
        donghyuck.gravar();
        donghyuck.gravar();
        System.out.println(donghyuck); // depois daqui, o dondhyuck, mesmo não estando lesionado, só pode dançar mais uma vez, porque sua energia vai acabar
        donghyuck.dançar();
        donghyuck.dançar();
        System.out.println(donghyuck);
        donghyuck.postarselca();
        donghyuck.descansar();
        donghyuck.descansar();
        donghyuck.postarselca();
    }
    
}
