package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class ClienteUDP {
	public static void main(String[] args) throws Exception {
		//cria o stream do teclado
		BufferedReader cadeiaUsuario = new BufferedReader(new InputStreamReader(System.in));
		//declara o socket cliente
		DatagramSocket clienteSocket = new DatagramSocket();
		//obtem o endereço IP do servidor com o DNS
		InetAddress enderecoIP = InetAddress.getByName("localhost");
		
		byte[] enviaDados = new byte[1024];
		byte[] recebeDados = new byte[1024];
		
		System.out.printf("Digite uma frase em minusculo: ");
		//lê a linha do teclado
		String palavra = cadeiaUsuario.readLine();
		enviaDados = palavra.getBytes();
		//cria pacote com o dado, o endereço do servidor e a porta do servidor
		DatagramPacket enviaPacote = new DatagramPacket (enviaDados, enviaDados.length, enderecoIP, 1429);
		//envia o pacote
		clienteSocket.send(enviaPacote);
		//declara o pacote a ser recebido
		DatagramPacket recebePacote = new DatagramPacket(recebeDados, recebeDados.length);
		//recebe o pacote do servidor
		clienteSocket.receive(recebePacote);
		//separa somente o dado recebido
		String palavraModificada = new String(recebePacote.getData());
		//mostra a palavra
		System.out.println("Datagrama UDP recebido!! " + palavraModificada);
		//fecha o socketCliente
		clienteSocket.close();
	
	}
}
