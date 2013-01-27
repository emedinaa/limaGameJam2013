package com.limagame.projects.killcupid.view.ui;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;

public class Player extends GameObject {

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
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {

		// this.mPhysicsHandler.setVelocityX(100);
		// this.mPhysicsHandler.setVelocityY(100);
		// OutOfScreenX();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void OutOfScreenX() {

		if (mY > GameActivity.CAMERA_HEIGHT) { // OutOfScreenX (right)
			mY = 0;
		} else if (mY < 0) { // OutOfScreenX (left)
			mY = GameActivity.CAMERA_HEIGHT;
		}
		/*
		 * if (mX > AndEngineTutorialActivity.CAMERA_WIDTH) { // OutOfScreenX
		 * (right) mX = 0; } else if (mX < 0) { // OutOfScreenX (left) mX =
		 * AndEngineTutorialActivity.CAMERA_WIDTH; }
		 */
	}
}
