package br.gamenet.model;

import jplay.Sprite;

public class Tiro extends Sprite{

	public Tiro(){
		super(Tiro.class.getResource("../resources/tiro.png").toString());
		this.x = -50;
		this.y = -50;
	}
	
	public Tiro(double x, double y) {
		super(Tiro.class.getResource("../resources/tiro.png").toString());
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
