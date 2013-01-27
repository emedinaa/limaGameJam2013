package com.limagame.projects.killcupid.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;

import com.limagame.projects.killcupid.GameActivity;
import com.limagame.projects.killcupid.manager.SceneManager.SceneType;
import com.limagame.projects.killcupid.view.ui.CupidEnemy;
import com.limagame.projects.killcupid.view.ui.ElementEnemy;
import com.limagame.projects.killcupid.view.ui.Player;
import com.limagame.projects.killcupid.view.ui.ProjectilSprite;

public class GameScene extends BaseScene {

	public static final long TIME_ENEMY_CREATE = 5000L;

	private IUpdateHandler render;
	private Player oPlayer;
	private CupidEnemy enemy;
	private List<ProjectilSprite> projectilesToBeAdded;
	private int _count = 0;

	private List<ElementEnemy> listEnemy;
	private long lastEnemyCreated;

	private Music bgMusic;

	private boolean ready;

	@Override
	public void createScene() {

		loadMusics();

		ready = false;

		lastEnemyCreated = System.currentTimeMillis();

		listEnemy = new ArrayList<ElementEnemy>();

		setBackground(new Background(1, 1, 1));

		/* Create the sprite and add it to the scene. */
		oPlayer = new Player(
				resourcesManager.activity.mPlayerTiledTextureRegionRotoman,
				resourcesManager.vbom);
		oPlayer.setZIndex(1000);
		this.attachChild(oPlayer);

		oPlayer.mPhysicsHandler.setVelocityX(30);

		enemy = new CupidEnemy(0, 0,
				resourcesManager.activity.mPlayerTiledTextureRegionCupid,
				resourcesManager.vbom);
		this.attachChild(enemy);
		// displayUtils=new DisplayUtils(this);

		projectilesToBeAdded = new ArrayList<ProjectilSprite>();

		render = new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				Log.v("console", "render");

				if (ready) {
					if (_count > 40) {
						_count = 0;
					}

					if (_count == 0) {
						showProjectil();
					}
					moveProjectile();
					_count++;
					_updateEnemy();
				} else {
					_startingLevel();
				}
				sortChildren();
			}

			private void moveProjectile() {
				// TODO Auto-generated method stub
				Iterator<ProjectilSprite> projectiles = projectilesToBeAdded
						.iterator();
				ProjectilSprite _projectile;
				// _projectile.
				// iterating over all the projectiles (bullets)
				while (projectiles.hasNext()) {
					_projectile = projectiles.next();

					float nposX = _projectile.getX() - _projectile.get_vX();
					float nposY = _projectile.getY() + _projectile.get_vY();

					_projectile.setX(nposX);
					_projectile.setY(nposY);
					_projectile.set_vY(_projectile.get_vY()
							+ _projectile.get_gY());
					// in case the projectile left the screen
					/*
					 * if (_projectile.getX() >= mCamera.getWidth() ||
					 * _projectile.getY() >= mCamera.getHeight() +
					 * _projectile.getHeight() || _projectile.getY() <=
					 * -_projectile.getHeight()) { removeSprite(_projectile,
					 * projectiles); continue; }
					 */

					// if the targets collides with a projectile, remove the
					// projectile and set the hit flag to true
					/*
					 * if (_target.collidesWith(_projectile)) {
					 * removeSprite(_projectile, projectiles); hit = true;
					 * break; }
					 */
				}
			}

			private void showProjectil() {
				int offX = (int) (enemy.getX());
				int offY = (int) (enemy.getY());

				final ProjectilSprite projectile;
				// position the projectile on the player
				projectile = createSp(
						offX,
						offY,
						resourcesManager.activity.mPlayerTiledTextureRegionProjectile);
				addProjectile(projectile);
				projectilesToBeAdded.add(projectile);
				projectile.set_vX(10);
				projectile.set_vY(2);
				// this.attachChild(projectile);

				/*
				 * int realX = (int) (resourcesManager.camera.getWidth() +
				 * projectile.getWidth() / 2.0f); float ratio = (float) offY /
				 * (float) offX; int realY = (int) ((realX * ratio) +
				 * projectile.getY());
				 * 
				 * int offRealX = (int) (realX - projectile.getX()); int
				 * offRealY = (int) (realY - projectile.getY()); float length =
				 * (float) Math.sqrt((offRealX * offRealX) + (offRealY *
				 * offRealY)); float velocity = 480.0f / 1.0f; // 480 pixels / 1
				 * sec float realMoveDuration = length / velocity;
				 */

				// defining a move modifier from the projectile's position to
				// the
				// calculated one
				/*
				 * MoveModifier mod = new MoveModifier(realMoveDuration,
				 * projectile.getX(), realX, projectile.getY(), realY);
				 * projectile.registerEntityModifier(mod.deepCopy());
				 */
				// projectilesToBeAdded.add(projectile);
				/*
				 * // plays a shooting sound shootingSound.play();
				 */

				// showProjectil();
			}

			@Override
			public void reset() {
			}

		};
		resourcesManager.engine.registerUpdateHandler(render);

		if (bgMusic != null) {
			bgMusic.play();
		}

		// return this.mMainScene;
	}

	protected void addProjectile(Sprite projectile) {
		this.attachChild(projectile);
	}

	public ProjectilSprite createSp(float dx, float dy,
			TiledTextureRegion pTiledTextureRegion) {
		ProjectilSprite sp = new ProjectilSprite(dx, dy, 100, 100,
				pTiledTextureRegion, resourcesManager.vbom);
		return sp;
	}

	// ---------------------------------------------------

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

	/**
	 * Hace update a los enemigos para crear nuevos, eliminar los que estŽn
	 * fuera de la pantalla.
	 */
	private void _updateEnemy() {

		if (System.currentTimeMillis() - lastEnemyCreated > TIME_ENEMY_CREATE) {
			_loadEnemy();
			lastEnemyCreated = System.currentTimeMillis();
		}

		List<ElementEnemy> tmpEnemy = new ArrayList<ElementEnemy>();

		for (ElementEnemy e : listEnemy) {
			if (e.destroy) {
				tmpEnemy.add(e);
			}
		}

		if (!tmpEnemy.isEmpty()) {
			for (ElementEnemy e : tmpEnemy) {
				listEnemy.remove(e);
			}
		}

	}

	private void _startingLevel() {
		oPlayer.setRotation((float) (Math.cos(System.currentTimeMillis() / 100)));
		if (oPlayer.getX() + oPlayer.width * 0.5f >= GameActivity.CAMERA_WIDTH / 2) {
			oPlayer.setRotation(0);
			oPlayer.mPhysicsHandler.setVelocityX(0);
			ready = true;
			enemy.execute();
		}
	}

	/**
	 * Carga un nuevo Enemigo
	 */
	private void _loadEnemy() {

		int key = ElementEnemy.ENEMY_DATA[new Random()
				.nextInt(ElementEnemy.ENEMY_DATA.length)];

		TiledTextureRegion tmpTiledTextureRegion = null;

		switch (key) {
		case ElementEnemy.ENEMY_BEAR:
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionBear;
			break;
		case ElementEnemy.ENEMY_PONY:
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionPony;
			break;
		case ElementEnemy.ENEMY_RABBIT:
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionRabbit;
			break;
		default:
		}

		if (tmpTiledTextureRegion != null) {
			ElementEnemy e = new ElementEnemy(tmpTiledTextureRegion,
					resourcesManager.vbom);

			listEnemy.add(e);

			float rnd = new Random().nextFloat();

			if (rnd < 0.5f) {
				e.setZIndex(500);
			} else {
				e.setZIndex(1500);
			}

			this.attachChild(e);
		}

	}

	private void loadMusics() {
		try {
			bgMusic = MusicFactory.createMusicFromAsset(
					resourcesManager.activity.getMusicManager(),
					resourcesManager.activity, "audio/intro_level1.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (bgMusic != null) {
			bgMusic.setVolume(0.5f);
			bgMusic.setLooping(true);
			// music.setLoopCount(yourCount);
			// music.stop();
			// music.pause();
		}
	}

}
