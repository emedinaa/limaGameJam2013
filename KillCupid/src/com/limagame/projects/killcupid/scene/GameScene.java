package com.limagame.projects.killcupid.scene;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;

import com.limagame.projects.killcupid.manager.SceneManager.SceneType;
import com.limagame.projects.killcupid.view.ui.CupidEnemy;
import com.limagame.projects.killcupid.view.ui.Player;

public class GameScene extends BaseScene {

	private IUpdateHandler render;
	private Player oPlayer;
	private CupidEnemy enemy;
	
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
    	
    	render=new IUpdateHandler()
    	{

			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				Log.v("console", "render");
			//	showProjectil();
			}

			private void showProjectil() 
			{
					int offX = (int) (enemy.getX());
					int offY = (int) (enemy.getY());

					final Sprite projectile;
					// position the projectile on the player
					projectile = createSp(offX, offY, resourcesManager.activity.mPlayerTiledTextureRegionProjectile);
					addProjectile(projectile);
					//this.attachChild(projectile);

					int realX = (int) (resourcesManager.camera.getWidth() + projectile.getWidth() / 2.0f);
					float ratio = (float) offY / (float) offX;
					int realY = (int) ((realX * ratio) + projectile.getY());

					int offRealX = (int) (realX - projectile.getX());
					int offRealY = (int) (realY - projectile.getY());
					float length = (float) Math.sqrt((offRealX * offRealX)
							+ (offRealY * offRealY));
					float velocity = 480.0f / 1.0f; // 480 pixels / 1 sec
					float realMoveDuration = length / velocity;

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

	public Sprite createSp(int dx,int dy,   TiledTextureRegion pTiledTextureRegion)
	{
		Sprite sp=new Sprite(dx, dy, pTiledTextureRegion, resourcesManager.vbom);
		
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
