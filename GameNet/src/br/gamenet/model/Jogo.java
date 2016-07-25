package br.gamenet.model;

import java.util.ArrayList;
import java.util.List;

import jplay.Collision;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Sound;
import jplay.Time;
import jplay.Window;

public class Jogo {

	public static Player player;
	public static Tiro tiro;
	private List<Meteoro> meteoros;
	private Window window;
	private Keyboard keyboard;
	private Time time;
	private boolean moverAutomatico;
	public Jogo(Player player, Window window, Keyboard keyboard, Mouse mouse, boolean moverAutomatico){
		this.player = player;
		this.window = window;
		this.keyboard = keyboard;
		this.time = new Time(0, 0, 1, window.getWidth()/2 - 20, 20, true);
		this.tiro = new Tiro(-50, -50);
		this.meteoros = new ArrayList<>();
		this.moverAutomatico = moverAutomatico;
	}
	
	public void atualizar(){
		for(Meteoro m : this.meteoros){
			m.atualizar(window);
			
			if(Collision.collided(m, player)){
				m.resetarPosicao(window);
				player.setLife(player.getLife() - 10);
			}
			
			if(Collision.collided(m, tiro)){
				new Sound("resources/explosao.wav").play();
				m.resetarPosicao(window);
				tiro.resetarPosicao();
				player.setPoints(player.getPoints() + 10);
			}
			
			if(time.getSecond() % 30 == 0){
				time.setSecond(time.getSecond() + 1);
				this.aumentarDificuldade();
			}
			if(!moverAutomatico){
				if(keyboard.keyDown(Keyboard.LEFT_KEY)){
					Jogo.player.moverEsquerda(window);
				}
				if(keyboard.keyDown(Keyboard.RIGHT_KEY)){
					Jogo.player.moverDireita(window);
				}
			}
			else{
				if(keyboard.keyDown(Keyboard.ENTER_KEY)){
					Jogo.player.inverterDirecao();
				}
				player.mover(window);
			}
		}
		
		if(keyboard.keyDown(Keyboard.SPACE_KEY)){
			lancarTiro();
		}
		
		tiro.atualizar();
				
		
	}
	
	public static void lancarTiro(){
		new Sound("resources/tiro.wav").play();
		tiro.x = player.x+20;
		tiro.y = player.y;
	}
	
	public void addMeteoro(Meteoro meteoro){
		this.meteoros.add(meteoro);
	}
	
	public void draw(){
		player.draw();
		tiro.draw();
		time.draw();
		for(Meteoro m : this.meteoros){
			m.draw();
		}
	}
	
	public void aumentarDificuldade(){
		ArrayList<Meteoro> meteorosAux = new ArrayList<>();
		meteorosAux.addAll(meteoros);
		meteorosAux.add(new Meteoro(-50, -50));
		meteoros = meteorosAux;
	}

	public boolean getMoverAutomatico(){
		return this.moverAutomatico;
	}
	
	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
	
}
