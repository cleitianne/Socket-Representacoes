package Expressoes;

//Pilha de strings
public class DoublePilha {
	private class Nodo{
		Nodo prox;
		double info;
		Nodo(double s){info=s;}
		Nodo(){};
	}
	private Nodo topo;
	private int nElementos=0;
			
	public void empilha(double v) {
		Nodo novo;
		novo = new Nodo(v);
		novo.prox = topo;
		nElementos++;
		topo = novo;
		}
		
	public double desempilha( ){
		double info;
		if(!vazia()){	
		info = topo.info;
		nElementos--;
		topo = topo.prox;
		return info;}
		else return 0;
		}
		
	public boolean vazia(){return topo==null;}
	public double retornaTopo(){if (!vazia())return topo.info;else return 0;}
	
	
}