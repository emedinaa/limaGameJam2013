package com.limagame.projects.killcupid.scene;

import com.limagame.projects.killcupid.manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene {

	@Override
	public void createScene() {

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
