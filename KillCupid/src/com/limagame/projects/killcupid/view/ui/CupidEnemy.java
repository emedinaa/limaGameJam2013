package com.limagame.projects.killcupid.view.ui;

import java.util.Random;

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

	private boolean running;
	private float _vx = 110;

	private Boolean _rigth = false;

	public Boolean get_rigth() {
		return _rigth;
	}

	public float get_vx() {
		return _vx;
	}

	private float _posX = 0;
	private float _posY = 0;

	public CupidEnemy(final float pX, final float pY,
			final TiledTextureRegion pTiledTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);

		running = false;

		float rnd = new Random().nextFloat();
		if (rnd > 0.5f) {
			_posX = -pTiledTextureRegion.getWidth();
		} else {
			_posX = GameActivity.CAMERA_WIDTH;
			setFlippedHorizontal(true);
		}
		_posY = 30;

		setPosition(_posX, _posY);

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {
		if (running) {
			// this.mPhysicsHandler.setVelocityY(_vx);
			OutOfScreenX();
			OutOfScreenY();
		}
	}

	public void execute() {
		if (_posX < 0) {
			this.mPhysicsHandler.setVelocityX(_vx);
		} else {
			this.mPhysicsHandler.setVelocityX(-_vx);
		}
		running = true;
	}

	// ===========================================================
	// Methods
	// movimiento
	// ===========================================================

	private void OutOfScreenY() {
		mY = _posY + (float) (2 * Math.cos(System.currentTimeMillis() / 50))
				* 5;
	}

	private void OutOfScreenX() {

		if (mX > GameActivity.CAMERA_WIDTH - width) { // OutOfScreenX
			this.mPhysicsHandler.setVelocity(-_vx);
			setFlippedHorizontal(false);
			_rigth = true;
		} else if (mX < 0) { // OutOfScreenX (left)
			setFlippedHorizontal(true);
			this.mPhysicsHandler.setVelocity(_vx);
			_rigth = false;
		}
	}
}
