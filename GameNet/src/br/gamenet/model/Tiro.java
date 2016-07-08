package br.gamenet.model;

import jplay.Sprite;

public class Tiro extends Sprite{

	public Tiro(){
		super("resources/tiro2.png");
		this.x = -50;
		this.y = -50;
	}
	
	public Tiro(double x, double y) {
		super("resources/tiro2.png");
		this.x = x;
		this.y = y;
	}

	public void atualizar(){
		if(this.y > -100)
			this.y -= 4;
	}
	
	public void resetarPosicao(){
		this.x = -50;
		this.y = -50;
	}
	
}
