package udp;

import java.net.*;

public class ServidorUDP {
	public static void main(String[] args)throws Exception {
		//criando socket do servidor com a porta de entrada
		DatagramSocket servidorSocket = new DatagramSocket(1429);
			System.out.println("Servidor UDP aguardando na porta 1429");
	
	
			
		byte[] dadosRecebidos = new byte[1024];
		byte[] dadosEnviados = new byte[1024];
		
		while(true) {
			//declara o pacote a ser recebido
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			
			//recebe o pacote do cliente
			servidorSocket.receive(pacoteRecebido);
			System.out.println("Pacote recebido do cliente");
			
			//recupera os dados de endereço a porta do cliente para enviar a mensagem de volta 
			String palavra = new String(pacoteRecebido.getData());
			InetAddress enderecoIP = pacoteRecebido.getAddress();
			int porta = pacoteRecebido.getPort();
			//transforma a palavra em maiuscula
			String sentencaCapturada = palavra.toUpperCase();
			dadosEnviados = sentencaCapturada.getBytes();
			
			//monta o pacote com o endereço IP e porta do cliente
			DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, enderecoIP, porta);
			System.out.println("Pacote devolvido ao Cliente");
			//envia pacote para o cliente
			servidorSocket.send(pacoteEnviado);
		}
	}
	
}
