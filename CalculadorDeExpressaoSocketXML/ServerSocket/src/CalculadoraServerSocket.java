import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import Expressoes.CalculadorDeExpressao;
import Expressoes.DadosConvertidosParaCalculoDaExpressao;
import Expressoes.Fila;
import Expressoes.FilaDeIgualdades;
import Expressoes.NoFilaDeIgualdades;
import Expressoes.Nodo;

public class CalculadoraServerSocket {

	public static void main(String[] args) throws NumberFormatException, Exception {
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;
		BufferedReader socketEntrada;
		
		String  result;
        CalculadorDeExpressao calculadora = new CalculadorDeExpressao();
        DadosConvertidosParaCalculoDaExpressao dadosConvertidos;
		
		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.addPermission(NoTypePermission.NONE);
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.allowTypeHierarchy(List.class);
		xstream.allowTypeHierarchy(String.class);
		xstream.allowTypeHierarchy(DadosConvertidosParaCalculoDaExpressao.class);
		xstream.allowTypeHierarchy(FilaDeIgualdades.class);
		xstream.allowTypeHierarchy(Fila.class);
		xstream.allowTypeHierarchy(Nodo.class);
		xstream.allowTypeHierarchy(NoFilaDeIgualdades.class);
		xstream.ignoreUnknownElements();
		
		String dadosXml = "";
		try {
			//Criação do servidor socket, colocando-o para "escutar" chamadas na porta 9090
			welcomeSocket = new ServerSocket(9090);
			

			System.out.println("Servidor no ar");
			while (true) {

				Socket connectionSocket = welcomeSocket.accept();
			
				System.out.println("Nova conexão");

				// Interpretando dados do cliente
				socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				
				String linha = "";
				while(true){
					linha = socketEntrada.readLine();
					//LER linha a linha até encotrar o fim dos dados (Previamente acordado com o cliente)
					if(linha.equals("FIM"))
						break;
					dadosXml += linha + "\n";
				}

				System.out.println(dadosXml);
				dadosConvertidos = (DadosConvertidosParaCalculoDaExpressao)xstream.fromXML(dadosXml);
				
				
				result = String.valueOf(calculadora.calculaExp(dadosConvertidos));
				

				// Enviando dados para o cliente
				socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
				socketOutput.writeBytes(result + '\n');
				System.out.println(result);
				socketOutput.flush();
				socketOutput.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
