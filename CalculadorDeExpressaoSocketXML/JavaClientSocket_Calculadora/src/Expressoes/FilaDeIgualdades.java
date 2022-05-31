package Expressoes;

//Fila de igualdades -- associa vari�veis alg�bricas com n�meros
public class FilaDeIgualdades{
	
	public int nElementos=0;
	public NoFilaDeIgualdades frente;
	public NoFilaDeIgualdades tras;
	
	public FilaDeIgualdades(){
		frente = new NoFilaDeIgualdades();
		tras = frente;
		frente.prox = null;
	}
	public boolean vazia(){return frente==tras;}

	public void enfileira(char ch, double d){
		if (pesquisa(ch)==null){
			tras.prox = new NoFilaDeIgualdades(ch,d);
			tras = tras.prox;
			tras.prox = null;
			nElementos++;
		}
	}
	public void enfileira(char ch){
		if (Character.isDigit(ch)){
			tras.prox=new NoFilaDeIgualdades(ch,Character.digit(ch,10));
			tras=tras.prox;
			tras.prox=null;
			nElementos++;
		}
		else{
			tras.prox= new NoFilaDeIgualdades(ch);
			tras = tras.prox;
			tras.prox = null;
			nElementos++;
		}
	}
	public double desenfileira(){
		if (!vazia()){
			NoFilaDeIgualdades q;
			q = frente;
			frente = frente.prox;
			double x = frente.valor;
			nElementos--;
			return x;
		}
		return 0;
	}

	public NoFilaDeIgualdades pesquisa(char c){
		NoFilaDeIgualdades q=frente.prox;
		while(q!=null){
			if(q.variavel==c)
				return q;
			q=q.prox;
		}
		return null;
	}
	public double retornaPrimeiro(){
		if (!vazia())
			return frente.prox.valor;
		return 0;
	}

	public void atribuiValor(NoFilaDeIgualdades nodo,double d){
		nodo.valor=d;
	}
	public void imprime(){
		if (!vazia()){
			NoFilaDeIgualdades q=frente.prox;
		while(q!=null){
			System.out.println(q.variavel+" = "+q.valor);
			q=q.prox;
		}}
		else System.out.println("Erro: Fila vazia");
		System.out.println();
	}
}