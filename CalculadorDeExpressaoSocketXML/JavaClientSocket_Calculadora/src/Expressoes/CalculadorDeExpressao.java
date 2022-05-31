package Expressoes;

public class CalculadorDeExpressao {

	public CalculadorDeExpressao(){ }
	
	
	//converte para a forma pós-fixada
	public DadosConvertidosParaCalculoDaExpressao converte(String expressaoOriginal){
		FilaDeIgualdades filaDeIgualdades =new FilaDeIgualdades();
		char c;
		Pilha pilha =new Pilha();
		Fila fila =new Fila();
		for (int i=0; i<expressaoOriginal.length(); i++){
			c=expressaoOriginal.charAt(i);
			switch(c){//determina o que faz com o caractere
				case '(': pilha.empilha(c); break;
				case ')': 
					for(char aux=pilha.desempilha();aux!='('; aux=pilha.desempilha())
						fila.enfileira(aux);
					break;
				case '*': pilha.empilha(c);break;
				case '/': pilha.empilha(c); break;
				case '+':
					if (pilha.vazia()||pilha.retornaTopo()=='+'||pilha.retornaTopo()=='-')
					{pilha.empilha(c); break;}
					else{
						while(pilha.retornaTopo()=='*'||pilha.retornaTopo()=='/'){
							fila.enfileira(pilha.desempilha());
						}
						pilha.empilha(c);
						break;
					}
				case '-':
					if (pilha.vazia()||pilha.retornaTopo()=='+'||pilha.retornaTopo()=='-'){pilha.empilha(c); break;}
					else{
						while(pilha.retornaTopo()=='*'||pilha.retornaTopo()=='/'){
							fila.enfileira(pilha.desempilha());
						}
						pilha.empilha(c);
						break;
					}
				default: fila.enfileira(c);filaDeIgualdades.enfileira(c);
			}
		}
		while(!pilha.vazia()) 
            fila.enfileira(pilha.desempilha());
		
		return new DadosConvertidosParaCalculoDaExpressao(filaDeIgualdades, fila);
	}//fim do converte, a partir os dados ja estão na notação em, que o calculo pode ser realizado.
	
	//calcula o valor numerico da expressao
	public double calculaExp(DadosConvertidosParaCalculoDaExpressao dadosConvertidos){
		String expressaoPosfixada = dadosConvertidos.Fila.toString();;
		char c;
		double operando1, operando2;
		DoublePilha doublePilha = new DoublePilha();
		for(int i=0; i < expressaoPosfixada.length(); i++){
			c = expressaoPosfixada.charAt(i);
			switch(c){
				case '*':
					operando1=doublePilha.desempilha(); operando2=doublePilha.desempilha();
					operando1=operando1*operando2;
					doublePilha.empilha(operando1);
					break;
				case '-':
					operando1=doublePilha.desempilha(); operando2=doublePilha.desempilha();
					operando1=operando2-operando1;
					doublePilha.empilha(operando1);
					break;
				case '+':
					operando1=doublePilha.desempilha(); operando2=doublePilha.desempilha();
					operando1=operando1+operando2;
					doublePilha.empilha(operando1);
					break;
				case '/':
					operando1=doublePilha.desempilha(); operando2=doublePilha.desempilha();
					operando1=operando2/operando1;
					doublePilha.empilha(operando1);
					break;
				default: 
				doublePilha.empilha(dadosConvertidos.FilaDeIgualdades.pesquisa(c).valor);
			}//switch
		}//for
		return doublePilha.retornaTopo();
	}
}