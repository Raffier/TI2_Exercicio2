package com.ti2cc;

public class Conta {

	//atributos que todo conta tem
    private int numero;
    private String dono;
    private double saldo;
    private double limite;

    public Conta(int numero, String dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void setLimite(double limite) {
        this.limite = limite;
    }

    public int getNumero() {
        return numero;
    }

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }
    
    //metodo que saca uma determinada quantidade.
    boolean saca(double valor) {
        if (this.saldo < valor) {
            return false;
        } 
        else {
            this.saldo = this.saldo - valor;
            return true;
        }
    }//fim do metodo saca.

    //metodo que deposita uma quantia.
    void deposita(double valor) {
        this.saldo += valor; //ou this.saldo = saldo + valor;
        // *soma quantidade ao valor antigo do saldo
        // e guarda no proprio saldo.
    }//fim do metodo deposita.
    
    
    //metodo que retorna a conta e suas informacoes.
    @Override
	public String toString() {
		return "Conta [numero=" + numero + ", dono=" + dono + ", saldo=" + saldo + ", limite=" + limite + "]";
	}//fim do metodo retorna	
}//fim da classe Conta.
