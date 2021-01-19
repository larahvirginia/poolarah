import java.util.ArrayList;
import java.util.Scanner;

class Operacao {
    public int id;
    public String descricao;
    public float valor;
    public float saldo;

    public Operacao (int id, String descricao, float valor, float saldo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.saldo = saldo;
    }

    public String toString() {
        return id + " : " + "descrição: " + descricao + " | " + "valor: " + valor + " | " + "saldo: " + saldo;
    }
}

public class ContaBancaria {
    Operacao op;
    int proxId;
    private float saldo;
    private int numero;
    ArrayList<Operacao> extrato;

    public ContaBancaria (int numero) {
        this.numero = numero;
        this.proxId = 0;
        extrato = new ArrayList<>();
    }

    public ArrayList<Operacao> getExtrato() {
        for (int i = 0; i < extrato.size(); i++) {
            System.out.println(extrato.get(i));
        }
        return extrato;
    }

    public ArrayList<Operacao> getExtratoLast(int n) {
        for (int i = extrato.size()-n; i < extrato.size(); i++) {
            System.out.println(extrato.get(i));
        }
        return extrato;
    }
    
    public ArrayList setExtrato() {
        this.extrato = extrato;
        return setExtrato;
    }

    public void setSaldo (float valor) {
        this.saldo = valor;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setNumero (int numero) {
        if (numero == this.numero) {
            System.out.println("essa conta já existe");
        } else {
            this.numero = numero;
        }
    }

    public int getNumero () {
        return numero;
    }

    public void adicionarOperacao(String descricao, float valor) {
        Operacao op = new Operacao (proxId, descricao, valor, getSaldo());
        proxId += 1;
        extrato.add(op);
    }

    public boolean depositar (float valor) {
        if (valor > 0) {
            float aux = getSaldo();
            this.setSaldo(aux + valor);
            adicionarOperacao("depósito", valor);
            return true;
        } else {
            System.out.println("fail: valor inválido");
            return false;
        }
    }
    
    public boolean sacar (float valor) {
        if (getSaldo() >= valor) {
            float aux = getSaldo();
            this.setSaldo(aux - valor);
            adicionarOperacao("saque", valor);
            return true;
        } else {
            System.out.println("fail: valor insuficiente");
            return false;
        }
    }

    public boolean tarifa (float valor) {
        float aux = getSaldo();
        this.setSaldo(aux - valor);
        adicionarOperacao("tarifa", valor);
        return true;
    }

    public void estornar(int indice) {
        float auxValor = 0;

        if (extrato.get(indice).descricao.equals("tarifa")) {
            auxValor = extrato.get(indice).valor;
            this.setSaldo((auxValor) + getSaldo());
            adicionarOperacao("estorno", auxValor*-1);
        } else {
            System.out.println("o indice " + indice + " nao corresponde à uma tarifa" );
        }
        
    }

    public String toString () {
        return "conta: " + numero + ", saldo: " + saldo;
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner (System.in);
        ContaBancaria conta = new ContaBancaria(0);
        /*conta.depositar(58);
        System.out.println(conta);
        conta.depositar(30);
        System.out.println(conta);
        conta.sacar(20);
        System.out.println(conta);
        conta.tarifa(2);
        System.out.println(conta);
        conta.getExtrato();
        conta.getExtratoLast(2);*/
        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("conta")) {
                conta.setNumero(Integer.parseInt(ui[1]));
                conta = new ContaBancaria(Integer.parseInt(ui[1]));
                System.out.println(conta);
            } else if (ui[0].equals("depositar")) {
                conta.depositar(Integer.parseInt(ui[1]));
                System.out.println(conta);
            } else if (ui[0].equals("sacar")) {
                conta.sacar(Integer.parseInt(ui[1]));
                System.out.println(conta);
            } else if (ui[0].equals("tarifa")) {
                conta.tarifa(Integer.parseInt(ui[1]));
                System.out.println(conta);    
            } else if (ui[0].equals("extrato")) {
                conta.getExtrato();
                System.out.println(conta);
            } else if (ui[0].equals("ultimoextrato")) {
                conta.getExtratoLast(Integer.parseInt(ui[1]));
                System.out.println(conta);
            } else if (ui[0].equals("estornar")) {
                conta.estornar(Integer.parseInt(ui[1]));
                conta.getExtrato();
            }
        }
        leitor.close();
    }
}