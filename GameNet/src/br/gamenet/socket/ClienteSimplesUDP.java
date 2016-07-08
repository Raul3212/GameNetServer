package br.gamenet.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteSimplesUDP implements Runnable{

	public static void main(String[] args) {
		Thread thread = new Thread(new ClienteSimplesUDP());
		thread.start();
	}
	
	@Override
	public void run() {

		BufferedReader leitor =
				new BufferedReader(new InputStreamReader(System.in));

		while(true){
			DatagramSocket cliente;
			try {
			
				cliente = new DatagramSocket();
				InetAddress ipServidor = InetAddress.getByName("localhost");
				byte[] sendData = new byte[1];


				String comando = leitor.readLine();
				sendData = comando.getBytes();

				DatagramPacket pkg = new DatagramPacket(sendData, sendData.length, ipServidor, ServidorUDP.PORTA_PADRAO);
				cliente.send(pkg);
			
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
