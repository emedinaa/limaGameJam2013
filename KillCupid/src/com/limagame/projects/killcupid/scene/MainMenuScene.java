package com.limagame.projects.killcupid.scene;

import org.andengine.entity.sprite.Sprite;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene {

	public MainMenuScene() {

	}

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);

		Sprite spBgMainMenu = new Sprite(0, 0,
				ResourcesManager.getInstance().activity.mMainMenuBg,
				ResourcesManager.getInstance().vbom);
		attachChild(spBgMainMenu);

	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {

	}

}
