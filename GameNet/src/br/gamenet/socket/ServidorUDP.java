package br.gamenet.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import br.gamenet.model.Jogo;
import br.gamenet.model.Player;

public class ServidorUDP implements Runnable{

	private Jogo jogo;
	private Player player;
	public static int PORTA_PADRAO = 7777;
	
	public ServidorUDP(Jogo jogo) {
		this.jogo = jogo;
		this.player = jogo.player;
	}
	
	@Override
	public void run() {
		
		DatagramSocket servidorUDP;
		try {
			servidorUDP = new DatagramSocket(PORTA_PADRAO);
			System.out.println("Servidor ativo na porta " + PORTA_PADRAO);
			DatagramPacket pkg = new DatagramPacket(new byte[1], 1);
			
			while(true){
				servidorUDP.receive(pkg);
				if(pkg != null){
					if(new String(pkg.getData()).equals("D")){
						Jogo.player.inverterDirecao();
					}
					else if(new String(pkg.getData()).equals("L")){
						Jogo.player.moverEsquerda(jogo.getWindow());
					}
					else if(new String(pkg.getData()).equals("R")){
						Jogo.player.moverDireita(jogo.getWindow());
					}
					else if(new String(pkg.getData()).equals("A")){
						Jogo.lancarTiro();
					}
					//System.out.println("RECEBIDO: " + new String(pkg.getData()));
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
