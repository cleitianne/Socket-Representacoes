package Expressoes;

public class NoFilaDeIgualdades{
    public NoFilaDeIgualdades prox;
    public char variavel;
    public double valor;
    
    NoFilaDeIgualdades(char var, double val){
        variavel=var;
        valor=val;
    }
    
    NoFilaDeIgualdades(){variavel='\0'; valor=0;}
    
    NoFilaDeIgualdades(char c){variavel=c; valor=0;}
}