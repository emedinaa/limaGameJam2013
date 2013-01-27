package com.limagame.projects.killcupid.manager;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

import com.limagame.projects.killcupid.scene.BaseScene;
import com.limagame.projects.killcupid.scene.CreditsScene;
import com.limagame.projects.killcupid.scene.GameScene;
import com.limagame.projects.killcupid.scene.MainMenuScene;
import com.limagame.projects.killcupid.scene.SplashScene;

public class SceneManager {

	public static final int SPLASHSCENEID = 100;
	public static final int MENUSCENEID = 101;
	public static final int GAMESCENEID = 102;
	public static final int LOADINGSCENEID = 103;
	public static final int CREDITSSCENEID = 104;
	// ---------------------------------------------
	// SCENES
	// ---------------------------------------------

	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------

	private BaseScene currentScene;

	private static final SceneManager INSTANCE = new SceneManager();

	private SceneType currentSceneType = SceneType.SCENE_SPLASH;

	private Engine engine = ResourcesManager.getInstance().engine;

	public enum SceneType {
		SCENE_SPLASH, SCENE_MENU, SCENE_GAME, SCENE_LOADING, SCENE_CREDITS
	}

	// ---------------------------------------------
	// CLASS LOGIC
	// ---------------------------------------------

	public void setScene(BaseScene scene) {
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}

	// ---------------------------------------------
	// GETTERS AND SETTERS
	// ---------------------------------------------

	public static SceneManager getInstance() {
		return INSTANCE;
	}

	public SceneType getCurrentSceneType() {
		return currentSceneType;
	}

	public BaseScene getCurrentScene() {
		return currentScene;
	}

	public Scene createScene(int $name) {
		BaseScene scene = null;

		if (currentScene != null) {
			currentScene.disposeScene();
		}

		switch ($name) {
		case SPLASHSCENEID:
			scene = new SplashScene();
			break;
		case GAMESCENEID:
			scene = new GameScene();
			break;
		case MENUSCENEID:
			scene = new MainMenuScene();
			break;
		case CREDITSSCENEID:
			scene = new CreditsScene();
			break;
		default:
			break;
		}

		currentScene = scene;
		setScene(currentScene);
		return currentScene;
	}

	/*
	 * public Scene createSplashScene() {
	 * ResourcesManager.getInstance().loadSplashScreen(); splashScene = new
	 * SplashScene(); currentScene = splashScene; return splashScene; }
	 * 
	 * public void disposeSplashScene() {
	 * ResourcesManager.getInstance().unloadSplashScreen();
	 * splashScene.disposeScene(); splashScene = null; }
	 */

}
