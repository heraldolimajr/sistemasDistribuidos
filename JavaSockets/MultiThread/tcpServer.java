/*
---------------------------------------------------------
:: Sockets Multithread - JAVA
:: Prof. Heraldo Gonçalves Lima Junior
:: Curso: Sistemas para a Internet
:: Disciplina: Sistemas Distribuídos
:: Contato: heraldo.junior@ifsertao-pe.edu.br
---------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TcpServer {

	public static void main(String[] args) {
		System.out.println("------------------------------");
		System.out.println(":: Servidor TCP Iniciado... ::");
		System.out.println("------------------------------");
		
		int port = 4800;
		
		try {
			ServerSocket socketServidor = new ServerSocket(4800);
			Socket socketCliente = socketServidor.accept();
			
			PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			
			EscutaThread escuta = new EscutaThread(in);
			escuta.start();
			
			String entrada;
			
			while(true) {
				entrada = JOptionPane.showInputDialog(null, "Mensagem para o cliente", "Servidor", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Servidor: "+entrada);
				out.println(entrada);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
