package com.limagame.projects.killcupid.scene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;
import com.limagame.projects.killcupid.manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene {

	public MainMenuScene() {

	}

	@Override
	public void createScene() {

		Sprite spBgMainMenu = new Sprite(0, 0,
				ResourcesManager.getInstance().activity.mMainMenuBg,
				ResourcesManager.getInstance().vbom);
		attachChild(spBgMainMenu);

		final Text gameText = new Text(250, 200,
				ResourcesManager.getInstance().activity.mFontMenu,
				"Jugar Rotoman", new TextOptions(HorizontalAlign.CENTER),
				ResourcesManager.getInstance().vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				SceneManager.getInstance()
						.createScene(SceneManager.GAMESCENEID);
				return true;
			}
		};

		attachChild(gameText);

		final Text creditsText = new Text(250, 250,
				ResourcesManager.getInstance().activity.mFontMenu, "Créditos",
				new TextOptions(HorizontalAlign.CENTER),
				ResourcesManager.getInstance().vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				SceneManager.getInstance().createScene(
						SceneManager.CREDITSSCENEID);
				return true;
			}
		};

		attachChild(creditsText);

		registerTouchArea(gameText);
		registerTouchArea(creditsText);
		setTouchAreaBindingOnActionDownEnabled(true);

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
