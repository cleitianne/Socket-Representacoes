package Expressoes;

public class Fila{
	
	public int nElementos=0;
	public Nodo frente;
	public Nodo tras;
	
	public Fila(){
		frente=new Nodo();
		tras=frente;
		frente.prox=null;
	}

	public boolean vazia(){return frente==tras;}
	public void enfileira(char ch){
		tras.prox=new Nodo(ch);
		tras = tras.prox;
		tras.prox = null;
		nElementos++;
	}
	public char desenfileira(){
		if (!vazia()){
			Nodo q;
			q = frente;
			frente = frente.prox;
			char x = frente.info;
			nElementos--;
			return x;
		}
		else return '\0';
	}
	public String toString(){
		Nodo q=frente.prox;
		String string="";
		do{
			string=string+q.info;
			q=q.prox;
		}while(q!=null);
		return string;
	}
	public void imprime(){
		if(!vazia()){
		Nodo q=frente.prox;
		do{
			System.out.print(q.info);
			q=q.prox;
		}while(q!=null);
		System.out.println();}
	}
	public Nodo pesquisa(char c){
		Nodo q=frente.prox;
		while(q!=null){
			if(q.info==c)return q;
			q=q.prox;
		}
		return null;
	}
	public void mudaValor(Nodo nodo, char c){
		nodo.info=c;
	}
	public void mudaValor(char c){
		frente.prox.info=c;
	}
	public char retornaPrimeiro(){
		if (!vazia())return frente.prox.info;
		else {System.out.println("Erro: Fila Vazia");
		return '\0';}}
}