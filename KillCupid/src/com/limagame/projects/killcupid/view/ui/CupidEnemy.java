package com.limagame.projects.killcupid.view.ui;

import java.util.Date;

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

	private float _vx=50;
	private float _posX=0;
	private float _posY=0;
	
	public CupidEnemy(final float pX, final float pY, final TiledTextureRegion pTiledTextureRegion, 
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		//pTiledTextureRegion.set
		//setRotation(90);
		_posX=GameActivity.WIDTH-120;//-pTiledTextureRegion.getWidth()-50;
		_posY=20;//-pTiledTextureRegion.getHeight();
		
		setPosition(_posX,_posY);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {

		this.mPhysicsHandler.setVelocityY(20);
		//this.mPhysicsHandler.setVelocityY(_vx);
		//OutOfScreenX();
		OutOfScreenY();
	}

	// ===========================================================
	// Methods
		// movimiento 
	// ===========================================================
	
	private void OutOfScreenY() 
	{
		Date date=new Date();
		mY=_posY+(float)( 2*Math.cos(date.getTime()*002*50) );
		//mX=(float)(Math.sin(Math.random()*10));
		/*if(mX< 0)
		{
			_vx=-_vx;
		}
		if(mX>AndEngineTutorialActivity.CAMERA_WIDTH)
		{
			_vx=-_vx;
		}
		*/
	}

	private void OutOfScreenX() {
		
		
		if (mY > GameActivity.HEIGHT) { // OutOfScreenX (right)
			mY = 0;
		} else if (mY < 0) 
		{ // OutOfScreenX (left)
			mY = GameActivity.HEIGHT;
		}
		/*if (mX > AndEngineTutorialActivity.CAMERA_WIDTH) { // OutOfScreenX (right)
			mX = 0;
		} else if (mX < 0) { // OutOfScreenX (left)
			mX = AndEngineTutorialActivity.CAMERA_WIDTH;
		}*/
	}
}
