/**
 * 
 */
package com.limagame.projects.killcupid.view.ui;

import java.util.Random;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.limagame.projects.killcupid.GameActivity;
import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.util.KillCupidConst;
import com.limagame.projects.killcupid.util.Utils;

/**
 * @author notebookqd
 * 
 */
public class ElementEnemy extends GameObject {

	public boolean destroy;

	private boolean inLoveMode;
	private boolean touched;

	private float velX;
	private float _posX, _posY;
	public static final int ENEMY_BEAR = 100;
	public static final int ENEMY_PONY = 101;
	public static final int ENEMY_RABBIT = 102;

	public static final int ENEMY_DATA[] = { ENEMY_BEAR, ENEMY_PONY,
			ENEMY_RABBIT };

	private Player player;
	private Sprite _entity=null;
	private Boolean _change=false;
	

	public ElementEnemy(Player player, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0, pTiledTextureRegion, pVertexBufferObjectManager);

		this.player = player;

		inLoveMode = false;
		touched = false;

		velX = Utils.getRandomBetweem(30, 200);

		float random = new Random().nextFloat();

		if (random < 0.5) {
			_posX = -pTiledTextureRegion.getWidth();
			this.mPhysicsHandler.setVelocityX(velX);
			this.setFlippedHorizontal(true);
		} else {
			_posX = GameActivity.CAMERA_WIDTH;
			this.mPhysicsHandler.setVelocityX(-velX);
		}

		/*
		 * _posY = GameActivity.CAMERA_HEIGHT - pTiledTextureRegion.getHeight()
		 * + 10;
		 */
		_posY = GameActivity.CAMERA_HEIGHT - KillCupidConst.posElementsY;
		setPosition(_posX, _posY);

		destroy = false;

	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown() && !touched) {
			int rnd = new Random()
					.nextInt(ResourcesManager.getInstance().activity.sndHits.length);
			ResourcesManager.getInstance().activity.sndHits[rnd].play();
			touched = true;
			player.angryEnemy();
			revertSpeed();
			return true;
		}
		return false;
	}

	@Override
	public void move() {
		if (!destroy) {
			if (!inLoveMode) {
				setRotation((float) (Math.cos(System.currentTimeMillis() / 100)));
			} else {
				setRotation((float) (Math.cos(System.currentTimeMillis())));
			}
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


	public void activeLoveMode() 
	{
		if(!_change)
		{
			_change=true;
			if(_entity==null)
			{
				_entity=new Sprite(0, 0, ResourcesManager.getInstance().activity.mHeartComplete, 
					ResourcesManager.getInstance().activity.getVertexBufferObjectManager());
			}
			//_entity=new Enti
			attachChild(_entity);
			
		}
		//mTextureRegion=GameActivity
		//mTextureRegion=ResourcesManager.getInstance().activity.mEnemyTiledTextureRegionBear2;
		
		if (player.getX() > getX()) {

			this.mPhysicsHandler.setVelocityX(velX * 1.3f);
			setFlippedHorizontal(true);
		} else if (player.getX() < getX()) {
			this.mPhysicsHandler.setVelocityX(-velX * 1.3f);
			setFlippedHorizontal(false);
		}
		inLoveMode = true;
	}

	public void setNormalMode() {
		
		if(_change)
		{
			if(_entity!=null)
			{
				detachChild(_entity);
			}
			_change=false;
		}
		inLoveMode = false;
		if (this.mPhysicsHandler.getVelocityX() > 0) {
			this.mPhysicsHandler.setVelocityX(-velX);
			setFlippedHorizontal(false);
		} else {
			this.mPhysicsHandler.setVelocityX(velX);
			setFlippedHorizontal(true);
		}
	}
}
