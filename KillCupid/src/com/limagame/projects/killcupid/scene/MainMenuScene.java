package com.limagame.projects.killcupid.scene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.limagame.projects.killcupid.GameActivity;
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

		final Text gameText = new Text(50, GameActivity.CAMERA_HEIGHT - 50,
				ResourcesManager.getInstance().activity.mFontMenu,
				"Jugar Rotoman", new TextOptions(HorizontalAlign.LEFT),
				ResourcesManager.getInstance().vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				SceneManager.getInstance()
						.createScene(SceneManager.GAMESCENEID);
				return true;
			}
		};

		gameText.setColor(Color.WHITE);

		attachChild(gameText);

		//

		final Text creditsText = new Text(GameActivity.CAMERA_WIDTH - 220,
				GameActivity.CAMERA_HEIGHT - 50,
				ResourcesManager.getInstance().activity.mFontMenu, "Créditos",
				new TextOptions(HorizontalAlign.RIGHT),
				ResourcesManager.getInstance().vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				SceneManager.getInstance().createScene(
						SceneManager.CREDITSSCENEID);
				return true;
			}
		};

		creditsText.setColor(Color.WHITE);

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
