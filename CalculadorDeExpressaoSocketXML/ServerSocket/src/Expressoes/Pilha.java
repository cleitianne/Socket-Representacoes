package Expressoes;

public class Pilha {

	private Nodo topo;
	private int nElementos=0;
			
	public void empilha(char v) {
		Nodo novo;
		novo = new Nodo(v);
		novo.prox = topo;
		nElementos++;
		topo = novo;}
		
	public char desempilha( ){
		char info;
		if(!vazia()){	
		info = topo.info;
		nElementos--;
		topo = topo.prox;
		return info;}
		else return '\0';}
		
	public boolean vazia(){return topo==null;}
	public char retornaTopo(){if (!vazia())return topo.info;else return '0';}
	
	
}
