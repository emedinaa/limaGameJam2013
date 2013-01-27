package com.limagame.projects.killcupid.view.ui;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;

public class Player extends GameObject {

	private static final long TIME_ANGRY = 1000L;

	public static final int LIVES = 10;
	private boolean angry;
	private long angry_time;

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
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0, pTiledTextureRegion, pVertexBufferObjectManager);
		// pTiledTextureRegion.set
		// setRotation(90);
		float posX = -pTiledTextureRegion.getWidth();
		float posY = (float) (GameActivity.CAMERA_HEIGHT
				- pTiledTextureRegion.getHeight() + 20);
		setPosition(posX, posY);
		angry = false;
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

	// ===========================================================
	// Methods
	// ===========================================================

	public void angryEnemy() {
		angry = true;
		angry_time = System.currentTimeMillis();
	}

}
