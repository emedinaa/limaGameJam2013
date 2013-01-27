package com.limagame.projects.killcupid.entities;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;

public class GameOverEntity extends Entity {

	public Sprite spGameOver;

	public GameOverEntity() {
	}

	public void setGame(boolean win) {
		ITextureRegion tex = null;
		if (!win) {
			tex = ResourcesManager.getInstance().activity.mGameLose;
		} else {
			tex = ResourcesManager.getInstance().activity.mGameWin;
		}

		spGameOver = new Sprite(0, 0, tex, ResourcesManager.getInstance().vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				SceneManager.getInstance()
						.createScene(SceneManager.MENUSCENEID);
				return true;
			}
		};

		attachChild(spGameOver);

	}
}
