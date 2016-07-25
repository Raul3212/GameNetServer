package br.gamenet.model;

import java.util.Random;

import jplay.Sprite;
import jplay.Window;

public class Meteoro extends Sprite{

	private Random rnd;
	private double velocidade;
	
	public Meteoro(double x, double y) {
		super(Meteoro.class.getResource("../resources/meteoro.png").toString().substring(6));
		this.rnd = new Random();
		this.x = x;
		this.y = y;
		this.velocidade = 1.5;
	}

	public void setVelocidade(double velocidade){
		this.velocidade = Math.abs(velocidade);
	}
	
	public void atualizar(Window window){
		this.y += this.velocidade;
		if(this.y > 800){
			this.y = 0;
			this.x = rnd.nextInt(window.getWidth()-this.width);
		}
	}
	
	public void resetarPosicao(Window window){
		this.y = 0;
		this.x = rnd.nextInt(window.getWidth()-this.width);
	}
	
}
