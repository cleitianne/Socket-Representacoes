import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import com.thoughtworks.xstream.XStream;

import Expressoes.CalculadorDeExpressao;
import Expressoes.DadosConvertidosParaCalculoDaExpressao;

public class CalculadoraClientSocket {

    public static void main(String[] args) {
        String expressao, result, representacaoXML;
        CalculadorDeExpressao calculadora = new CalculadorDeExpressao();
        DadosConvertidosParaCalculoDaExpressao dadosConvertidos;
        XStream xstream = new XStream();
		
        try {
            System.out.println("Cliente rodando");
            
            
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Conexão com o Servidor
                Socket clientSocket = new Socket("localhost", 9090);
                DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
                
                // Entrada de dados com a expressão matematica
                System.out.println("Digite a expressao");
                expressao = scanner.next();
                
                //Representando a expressão em um estrutura de dados
                dadosConvertidos = calculadora.converte(expressao);
                
                //Serializando o objeto complexo para XML
                representacaoXML = xstream.toXML(dadosConvertidos);
                
                // Enviando os dados para o servidor
                socketSaidaServer.writeBytes(representacaoXML + "\n");
                //Sinaliza o FIM do xml para o server (Acordado entre cliente e servidor)
                socketSaidaServer.writeBytes("FIM"+"\n");
                socketSaidaServer.flush();

                // Recebendo a resposta do servidor socket
                BufferedReader messageFromServer = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                result = messageFromServer.readLine();

                System.out.println("resultado=" + result);
                //Encerrando a conexão com o server
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
