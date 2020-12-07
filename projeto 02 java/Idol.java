package idol;
// o donghyuck é um cantor e o programa é sobre as coisas que ele faz na vida :)
// selca é selfie hehe

import java.util.Scanner;

public class Idol {
    int potenciaVocal;
    int energia;
    boolean sPreguiça; // não está com preguiçca
    boolean nLesionado; // não está lesionado
    
    Idol(int potenciaVocal, int energia, boolean sPreguiça, boolean nLesionado){
        this.potenciaVocal = potenciaVocal;
        this.energia = energia;
        this.sPreguiça = sPreguiça;
        this.nLesionado = nLesionado;
    }
    
    void gravar () {
        if (potenciaVocal > 0) {
            System.out.println("Gravando!");
            potenciaVocal -= 1;
            energia -=1;
        } else {
            System.out.println("Cof, cof! Preciso de um copo d'água!");
        }
    }
    
    void postarSelca () {
        if (sPreguiça == false && energia > 0) {
            System.out.println("Diga xis!");
        } else {
            System.out.println("Não vou postar nada naum, tô cansadinho");
        }
    }
    
    void descansar() {
        System.out.println("Mimindo");
        energia +=1;
        potenciaVocal +=1;
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
        return "Donghyuck : pv:" + potenciaVocal + " energia:" + energia + " lesionado?" + nLesionado + " preguiçoso?" + sPreguiça;
    }
    
    
    public static void main(String[] args) {
        
        Scanner leitor = new Scanner(System.in);

        Idol donghyuck = new Idol(6, 5, false, false);

        while(true) {
			String line = leitor.nextLine();
			String input[] = line.split(" ");
			if (input[0].equals("end")) {
				System.out.println("fim");
				break;
			}
			
			else if (input[0].equals("gravar")) {
				donghyuck.gravar();
				System.out.println(donghyuck);
			}
			
			else if (input[0].equals("postarselca")) {
				donghyuck.postarSelca();
				System.out.println(donghyuck);
			}
			
			else if (input [0].equals("descansar")) {
				donghyuck.descansar();
				System.out.println(donghyuck);
			}
			
			else if (input [0].equals("dancar")) {
				donghyuck.dançar();
				System.out.println(donghyuck);
			}
		}
        
        leitor.close();
    }
    
}