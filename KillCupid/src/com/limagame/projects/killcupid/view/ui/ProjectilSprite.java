/**
 * 
 */
package com.limagame.projects.killcupid.view.ui;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;

/**
 * @author notebookqd
 *
 */
public class ProjectilSprite extends Sprite 
{
	private float _gY=0.2f;
	private float _vX=0;
	private float _vY=0;
	private float _posTmpX=0;
	private float _angle;
	private Boolean _rigth=false;
	
	public float get_posTmpX() {
		return _posTmpX;
	}

	public void set_posTmpX(float _posTmpX) {
		this._posTmpX = _posTmpX;
	}

	public float get_posTmpY() {
		return _posTmpY;
	}

	public void set_posTmpY(float _posTmpY) {
		this._posTmpY = _posTmpY;
	}



	private float _posTmpY=0;
	
	public float get_gY() {
		return _gY;
	}

	public void set_gY(float _gY) {
		this._gY = _gY;
	}
	public float get_vX() {
		return _vX;
	}

	public void set_vX(float _vX) {
		this._vX = _vX;
		if(_vX>0)
		{
			_rigth=true;
			setFlippedHorizontal(true);
		}else
		{
			_rigth=false;
			setFlippedHorizontal(false);
		}
	}

	public float get_vY() {
		return _vY;
	}

	public void set_vY(float _vY) {
		this._vY = _vY;
	}


	
	public ProjectilSprite(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom) 
	{
		super(pX, pY, pWidth, pHeight, pTextureRegion, vbom);
		// TODO Auto-generated constructor stub
		//setFlippedHorizontal(false);
		//setFlippedHorizontal(true);
	}
	
	public double  calculateAngle()
	{
		//Point p=new Point(0,0);
		double  angle=Math.atan2((getY()-get_posTmpY()),(getX()-get_posTmpX()));
		
		return angle;
	}

	public float get_angle() {
		return _angle;
	}

	public void set_angle(float _angle) {
		this._angle = _angle;
		if(_rigth)
		{
			setRotation(-1*_angle);
		}else
		{
			setRotation(1*_angle);
		}
	}
	
	
	
	

	





}
