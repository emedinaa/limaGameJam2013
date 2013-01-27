package com.limagame.projects.killcupid.view.ui;

import java.util.List;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;
import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.util.KillCupidConst;

public class Player extends GameObject {

	private static final long TIME_ANGRY = 1000L;

	public static final int LIVES = 10;
	private boolean angry, cry;
	private long angry_time;
	private List<ElementEnemy> lstEnemy;

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public Player(final TiledTextureRegion pTiledTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager,
			List<ElementEnemy> lstEnemy) {
		super(0, 0, pTiledTextureRegion, pVertexBufferObjectManager);
		// pTiledTextureRegion.set
		// setRotation(90);

		this.lstEnemy = lstEnemy;

		float posX = -pTiledTextureRegion.getWidth();
		float posY = (float) (GameActivity.CAMERA_HEIGHT - KillCupidConst.posElementsY);
		// - pTiledTextureRegion.getHeight() + 10);

		setPosition(posX, posY);
		angry = false;
		cry = false;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {
		if (!angry) {
			setRotation((float) (Math.cos(System.currentTimeMillis() / 100)));
		} else {
			if (System.currentTimeMillis() - angry_time > TIME_ANGRY) {
				angry = false;
			}
			setRotation((float) (Math.cos(System.currentTimeMillis())));
		}

		// this.mPhysicsHandler.setVelocityX(100);
		// this.mPhysicsHandler.setVelocityY(100);
		// OutOfScreenX();
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			ResourcesManager.getInstance().activity.sndGrito.play();
			float minX = getX() - getWidth() * 2;
			float maxX = getX() + getWidth() * 2.5f;
			if (!lstEnemy.isEmpty()) {
				for (ElementEnemy e : lstEnemy) {
					if (e.isInLoveMode() && e.getX() >= minX
							&& e.getX() <= maxX) {
						e.setNormalMode();
					}
				}
			}
			return true;
		}
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void angryEnemy() {
		angry = true;
		angry_time = System.currentTimeMillis();
		ResourcesManager.getInstance().engine.vibrate(100);
	}

}
