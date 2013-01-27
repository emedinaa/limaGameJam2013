/**
 * 
 */
package com.limagame.projects.killcupid.view.ui;

import java.util.Random;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;
import com.limagame.projects.killcupid.util.Utils;

/**
 * @author notebookqd
 * 
 */
public class ElementEnemy extends GameObject {

	public boolean destroy;

	private boolean inLoveMode;

	private final float velX;
	private float _posX, _posY;
	public static final int ENEMY_BEAR = 100;
	public static final int ENEMY_PONY = 101;
	public static final int ENEMY_RABBIT = 102;

	public static final int ENEMY_DATA[] = { ENEMY_BEAR, ENEMY_PONY,
			ENEMY_RABBIT };

	private Player player;

	public ElementEnemy(Player player, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0, pTiledTextureRegion, pVertexBufferObjectManager);

		this.player = player;

		inLoveMode = false;

		velX = Utils.getRandomBetweem(40, 80);

		float random = new Random().nextFloat();

		if (random < 0.5) {
			_posX = -pTiledTextureRegion.getWidth();
			this.mPhysicsHandler.setVelocityX(velX);
			this.setFlippedHorizontal(true);
		} else {
			_posX = GameActivity.CAMERA_WIDTH;
			this.mPhysicsHandler.setVelocityX(-velX);
		}

		_posY = GameActivity.CAMERA_HEIGHT - pTiledTextureRegion.getHeight()
				+ 10;
		setPosition(_posX, _posY);

		destroy = false;

	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			player.angryEnemy();
			revertSpeed();
		}
		return true;
	}

	@Override
	public void move() {
		if (!destroy) {
			if (inLoveMode) {

			} else {

			}

			setRotation((float) (Math.cos(System.currentTimeMillis() / 100)));
			OutOfScreenX();
		}
	}

	public void OutOfScreenX() {
		if (mX < -width || mX > GameActivity.CAMERA_WIDTH) {
			destroy = true;
			this.mPhysicsHandler.setVelocityX(0);
		}
	}

	public boolean isInLoveMode() {
		return inLoveMode;
	}

	public void setLoveMode(boolean inLoveMode) {
		this.inLoveMode = inLoveMode;
	}

	public void revertSpeed() {
		this.mPhysicsHandler.setVelocityX(this.mPhysicsHandler.getVelocityX()
				* -1);
		if (this.mPhysicsHandler.getVelocityX() > 0) {
			setFlippedHorizontal(true);
		} else {
			setFlippedHorizontal(false);
		}
	}

}
