import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

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
                
                // Entrada de dados para a realização das operações
                System.out.println("Digite a expressao");
                expressao = scanner.next();
                dadosConvertidos = calculadora.converte(expressao);
                representacaoXML = xstream.toXML(dadosConvertidos);
                
                System.out.println(representacaoXML + "\n\n");
                
                // Enviando os dados para o servidor
                socketSaidaServer.writeBytes(representacaoXML + "\n");
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
