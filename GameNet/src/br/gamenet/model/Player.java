package br.gamenet.model;

import jplay.Sprite;
import jplay.Window;

public class Player extends Sprite{

	private int life;
	private int points;
	private double velocidade;
	
	public Player(double x, double y) {
		super(Player.class.getResource("../resources/nave.png").toString().substring(6));
		this.x = x;
		this.y = y;
		this.life = 100;
		this.points = 0;			
		this.velocidade = 1.5;
	}
	
	public void mover(Window window){
		this.x += velocidade;
		if(this.x + this.width >= window.getWidth() || this.x <= 0)
			inverterDirecao();
	}
	
	public void inverterDirecao(){
		this.velocidade *= -1;
	}
	
	public void moverDireita(Window window){
		if(this.x + this.width < window.getWidth())
			this.x += 20;
	}
	
	public void moverEsquerda(Window window){
		if(this.x > 0)
			this.x -= 20;
	}
	
	public int getLife() {
		return life;
	}

	public int getPoints() {
		return points;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
