package com.limagame.projects.killcupid.scene;

import org.andengine.entity.sprite.Sprite;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;
import com.limagame.projects.killcupid.manager.SceneManager.SceneType;

public class CreditsScene extends BaseScene {

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		Sprite s = new Sprite(0, 0,
				ResourcesManager.getInstance().activity.mCreditsBg,
				ResourcesManager.getInstance().vbom);
		attachChild(s);
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().createScene(SceneManager.GAMESCENEID);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_CREDITS;
	}

	@Override
	public void disposeScene() {

	}

}
