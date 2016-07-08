package br.gamenet.main;

import java.awt.Color;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

import br.gamenet.model.Jogo;
import br.gamenet.model.Meteoro;
import br.gamenet.model.Player;
import br.gamenet.model.Tiro;
import br.gamenet.socket.ServidorUDP;
import jplay.Collision;
import jplay.GameImage;
import jplay.GameObject;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Window;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Random rnd = new Random();
		
		Window window = new Window(800, 600);
		window.setFullScreen();
		Keyboard keyboard = window.getKeyboard();
		Mouse mouse = window.getMouse();
		
		Player player = new Player(650, 650);
		Jogo jogo = new Jogo(player, window, keyboard, mouse, true);
		
		jogo.addMeteoro(new Meteoro(rnd.nextInt(1300), -10));
		
		GameImage gameOver = new GameImage("resources/gameOver.jpg");
		gameOver.x = window.getWidth()/2 - gameOver.width/2;
		gameOver.y = window.getHeight()/2 - gameOver.height/2;
				
		ServidorUDP servidor = new ServidorUDP(jogo);
		Thread thread = new Thread(servidor);
		thread.start();
		
		while(true){
						
			if(keyboard.keyDown(Keyboard.ESCAPE_KEY)){
				window.setVisible(false);
				break;
			}
			
			if(Jogo.player.getLife() <= 0){
				break;
			}
			
			
			
			window.clear(Color.BLACK);
				
				window.drawText("VIDA: " + Jogo.player.getLife(), 50, 20, Color.WHITE);
				window.drawText("PONTOS: " + Jogo.player.getPoints(), 1250, 20, Color.WHITE);				
				jogo.draw();
				jogo.atualizar();
			
			window.update();
			
		}
		
		window.clear(Color.BLACK);
		
			gameOver.draw();
			window.update();
		
		window.delay(5000);
		
		window.exit();
		
	}

}
