package com.limagame.projects.killcupid.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;

import com.limagame.projects.killcupid.manager.SceneManager.SceneType;
import com.limagame.projects.killcupid.view.ui.CupidEnemy;
import com.limagame.projects.killcupid.view.ui.Player;
import com.limagame.projects.killcupid.view.ui.ProjectilSprite;

public class GameScene extends BaseScene {

	private IUpdateHandler render;
	private Player oPlayer;
	private CupidEnemy enemy;
	private List<ProjectilSprite>projectilesToBeAdded;
	private int _count=0;
	
	@Override
	public void createScene() 
	{
		setBackground(new Background(1, 1, 1));
		
		final float centerX = 20;//(CAMERA_WIDTH - this.mPlayerTiledTextureRegion.getWidth()) / 2;
		final float centerY = 20;

		/* Create the sprite and add it to the scene. */
		oPlayer = new Player(0, 0, resourcesManager.activity.mPlayerTiledTextureRegionRotoman,
				resourcesManager.vbom);
		this.attachChild(oPlayer);

		enemy = new CupidEnemy(0, 0, resourcesManager.activity.mPlayerTiledTextureRegionCupid,
				resourcesManager.vbom);
		this.attachChild(enemy);
    	//displayUtils=new DisplayUtils(this);
    	
		projectilesToBeAdded = new ArrayList<ProjectilSprite>();
		
    	render=new IUpdateHandler()
    	{

			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				Log.v("console", "render");
				if(_count>40)
				{
					_count=0;
				}
				
				if(_count==0)
				{
					showProjectil();
				}
				moveProjectile();
				_count++;
			}

			private void moveProjectile() 
			{
				// TODO Auto-generated method stub
				Iterator<ProjectilSprite> projectiles = projectilesToBeAdded.iterator();
				ProjectilSprite _projectile;
				//_projectile.
				// iterating over all the projectiles (bullets)
				while (projectiles.hasNext())
				{
					_projectile = projectiles.next();
					
					float nposX=_projectile.getX()-_projectile.get_vX();
					float nposY=_projectile.getY()+_projectile.get_vY();
					
					_projectile.setX(nposX);
					_projectile.setY(nposY);
					_projectile.set_vY(_projectile.get_vY()+_projectile.get_gY());
					// in case the projectile left the screen
					/*if (_projectile.getX() >= mCamera.getWidth()
							|| _projectile.getY() >= mCamera.getHeight()
									+ _projectile.getHeight()
							|| _projectile.getY() <= -_projectile.getHeight()) {
						removeSprite(_projectile, projectiles);
						continue;
					}*/

					// if the targets collides with a projectile, remove the
					// projectile and set the hit flag to true
					/*if (_target.collidesWith(_projectile)) {
						removeSprite(_projectile, projectiles);
						hit = true;
						break;
					}*/
				}
			}

			private void showProjectil() 
			{
					int offX = (int) (enemy.getX());
					int offY = (int) (enemy.getY());

					final ProjectilSprite projectile;
					// position the projectile on the player
					projectile = createSp(offX, offY, resourcesManager.activity.mPlayerTiledTextureRegionProjectile);
					addProjectile(projectile);
					projectilesToBeAdded.add(projectile);
					projectile.set_vX(10);
					projectile.set_vY(2);
					//this.attachChild(projectile);

					/*int realX = (int) (resourcesManager.camera.getWidth() + projectile.getWidth() / 2.0f);
					float ratio = (float) offY / (float) offX;
					int realY = (int) ((realX * ratio) + projectile.getY());

					int offRealX = (int) (realX - projectile.getX());
					int offRealY = (int) (realY - projectile.getY());
					float length = (float) Math.sqrt((offRealX * offRealX)
							+ (offRealY * offRealY));
					float velocity = 480.0f / 1.0f; // 480 pixels / 1 sec
					float realMoveDuration = length / velocity;*/

					// defining a move modifier from the projectile's position to the
					// calculated one
					/*MoveModifier mod = new MoveModifier(realMoveDuration,
							projectile.getX(), realX, projectile.getY(), realY);
					projectile.registerEntityModifier(mod.deepCopy());
					*/
					//projectilesToBeAdded.add(projectile);
					/* // plays a shooting sound
					shootingSound.play();
				*/
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
    		
    	};
    	resourcesManager.engine.registerUpdateHandler(render);
		
		//return this.mMainScene;
	}
	
	protected void addProjectile(Sprite projectile) {
		// TODO Auto-generated method stub
		this.attachChild(projectile);
	}

	public ProjectilSprite createSp(float dx,float dy, TiledTextureRegion pTiledTextureRegion)
	{
		ProjectilSprite sp=new ProjectilSprite(dx, dy, 100, 100, pTiledTextureRegion, resourcesManager.vbom);
		return sp;
	}
	
	//---------------------------------------------------

	@Override
	public void onBackKeyPressed() {

	}

	@Override
	public SceneType getSceneType() {
		return null;
	}

	@Override
	public void disposeScene() {

	}

}
