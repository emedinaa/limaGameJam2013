package com.limagame.projects.killcupid.view.ui;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;

public class CupidEnemy extends GameObject {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	private float _vx = 60;
	private float _posX = 0;
	private float _posY = 0;

	public CupidEnemy(final float pX, final float pY,
			final TiledTextureRegion pTiledTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);

		_posX = GameActivity.CAMERA_WIDTH - pTiledTextureRegion.getWidth();
		_posY = 30;

		setPosition(_posX, _posY);
		this.mPhysicsHandler.setVelocityX(_vx);

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {
		// this.mPhysicsHandler.setVelocityY(_vx);
		OutOfScreenX();
		OutOfScreenY();
	}

	// ===========================================================
	// Methods
	// movimiento
	// ===========================================================

	private void OutOfScreenY() {
		mY = _posY + (float) (2 * Math.cos(System.currentTimeMillis() / 50))
				* 5;
		// mX=(float)(Math.sin(Math.random()*10));
		/*
		 * if(mX< 0) { _vx=-_vx; } if(mX>AndEngineTutorialActivity.CAMERA_WIDTH)
		 * { _vx=-_vx; }
		 */
	}

	private void OutOfScreenX() {

		if (mX > GameActivity.CAMERA_WIDTH - width) { // OutOfScreenX
			// (right)
			this.mPhysicsHandler.setVelocity(-_vx);
		} else if (mX < 0) { // OutOfScreenX (left)
			// mX = AndEngineTutorialActivity.CAMERA_WIDTH;
			this.mPhysicsHandler.setVelocity(_vx);
		}
		/*
		 * if (mX > AndEngineTutorialActivity.CAMERA_WIDTH) { // OutOfScreenX
		 * (right) mX = 0; } else if (mX < 0) { // OutOfScreenX (left) mX =
		 * AndEngineTutorialActivity.CAMERA_WIDTH; }
		 */
	}
}
