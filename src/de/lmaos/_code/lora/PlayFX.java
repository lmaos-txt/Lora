package de.lmaos._code.lora;

import name.panitz.game.framework.fx.GameApplication;

public class PlayFX extends GameApplication {

	public PlayFX() {
		super(new Lora<>());
	}
	public static void main(String[] args){
		launch();
	}
}
