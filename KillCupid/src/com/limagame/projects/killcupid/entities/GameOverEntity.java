package com.limagame.projects.killcupid.entities;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.limagame.projects.killcupid.manager.ResourcesManager;

public class GameOverEntity extends Entity {

	public GameOverEntity() {
	}

	public void setGame(boolean win) {
		Sprite spGameOver = null;
		if (!win) {
			spGameOver = new Sprite(0, 0,
					ResourcesManager.getInstance().activity.mGameWin,
					ResourcesManager.getInstance().vbom);
		} else {
			spGameOver = new Sprite(0, 0,
					ResourcesManager.getInstance().activity.mGameWin,
					ResourcesManager.getInstance().vbom);
		}
		attachChild(spGameOver);
	}

}
