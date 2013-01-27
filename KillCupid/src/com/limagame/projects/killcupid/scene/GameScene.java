package com.limagame.projects.killcupid.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.limagame.projects.killcupid.GameActivity;
import com.limagame.projects.killcupid.entities.ControlEntity;
import com.limagame.projects.killcupid.entities.GameOverEntity;
import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;
import com.limagame.projects.killcupid.manager.SceneManager.SceneType;
import com.limagame.projects.killcupid.view.ui.CupidEnemy;
import com.limagame.projects.killcupid.view.ui.ElementEnemy;
import com.limagame.projects.killcupid.view.ui.Player;
import com.limagame.projects.killcupid.view.ui.ProjectilSprite;

public class GameScene extends BaseScene {

	public static final long TIME_ENEMY_CREATE = 1000L;

	private IUpdateHandler render;
	private Player oPlayer;
	private CupidEnemy enemy;
	private List<ProjectilSprite> projectilesToBeAdded;
	private int _count = 0;

	private List<ElementEnemy> listEnemy;
	private long lastEnemyCreated;

	private Music bgMusic;

	private boolean ready;

	private ControlEntity controlEntity;
	private GameOverEntity gameOverEntity;

	@Override
	public void createScene() {

		this.setTouchAreaBindingOnActionDownEnabled(true);

		loadMusic();

		ready = false;

		lastEnemyCreated = System.currentTimeMillis();

		listEnemy = new ArrayList<ElementEnemy>();

		Sprite s = new Sprite(0, 0, resourcesManager.activity.mbgTiledTexture,
				resourcesManager.vbom);
		s.setZIndex(-1);
		attachChild(s);

		// --------------------------------------------------------

		/* Create the sprite and add it to the scene. */
		oPlayer = new Player(
				resourcesManager.activity.mPlayerTiledTextureRegionRotoman,
				resourcesManager.vbom, listEnemy);
		oPlayer.setZIndex(1000);
		this.attachChild(oPlayer);

		oPlayer.mPhysicsHandler.setVelocityX(70);

		enemy = new CupidEnemy(0, 0,
				resourcesManager.activity.mPlayerTiledTextureRegionCupid,
				resourcesManager.vbom);
		this.attachChild(enemy);
		// displayUtils=new DisplayUtils(this);

		controlEntity = new ControlEntity();
		controlEntity.setZIndex(5000);
		this.attachChild(controlEntity);

		gameOverEntity = new GameOverEntity();
		gameOverEntity.setZIndex(9999);
		gameOverEntity.setVisible(false);
		this.attachChild(gameOverEntity);

		projectilesToBeAdded = new ArrayList<ProjectilSprite>();

		render = new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				// Log.v("console", "render");

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

				if (!controlEntity.isAlive() || oPlayer.score >= 20) {
					_stopGame();
				}
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

					_projectile.set_angle(_projectile.get_angle() + 1);

					if (_projectile.getY() >= (GameActivity.CAMERA_HEIGHT - 50)) {
						removeSprite(_projectile, projectiles);
						continue;
					}
					if (_projectile.getX() <= 0
							|| _projectile.getX() >= GameActivity.CAMERA_WIDTH) {
						removeSprite(_projectile, projectiles);
						continue;
					}

					for (ElementEnemy e : listEnemy) {
						if (_projectile.isVisible() && !e.isInLoveMode()
								&& e.collidesWith(_projectile)) {
							_projectile.setVisible(false);
							e.activeLoveMode();
						}
					}

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
				if (enemy.get_rigth() == true) {
					projectile.set_vX(10);
				} else {
					projectile.set_vX(-10);
				}
				projectile.set_vY(3);

				ResourcesManager.getInstance().activity.sndArrow.play();

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

		setEvents();
	}

	private void setEvents() {
		// TODO Auto-generated method stub
		this.setOnSceneTouchListener(new IOnSceneTouchListener() {

			@Override
			public boolean onSceneTouchEvent(Scene pScene,
					TouchEvent pSceneTouchEvent) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case MotionEvent.ACTION_DOWN:
					final float touchX = pSceneTouchEvent.getX();
					final float touchY = pSceneTouchEvent.getY();
					Log.v("console", "touchX touchY " + touchX + " " + touchY);

					break;
				case MotionEvent.ACTION_MOVE:

					break;
				case MotionEvent.ACTION_UP:

					break;
				default:
					break;
				}
				/*
				 * if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
				 * final float touchX = pSceneTouchEvent.getX(); final float
				 * touchY = pSceneTouchEvent.getY(); //shootProjectile(touchX,
				 * touchY);
				 * 
				 * 
				 * return true; }
				 */
				return false;
			}

		});
		// setOnSceneTouchListener

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
		SceneManager.getInstance().createScene(SceneManager.MENUSCENEID);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		if (bgMusic != null) {
			bgMusic.stop();
			bgMusic.release();
		}
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
			} else {
				if (controlEntity.isAlive() && e.isInLoveMode()
						&& e.collidesWith(oPlayer)) {

					float enX = e.getX() + e.getWidth() * 0.2f;
					float enY = e.getY() + e.getHeight() * 0.2f;

					float pX = oPlayer.getX() + oPlayer.getWidth() * 0.2f;
					float pY = oPlayer.getY() + oPlayer.getHeight() * 0.2f;

					RectF rectEnemy = new RectF(enX, enY, enX + e.getWidth()
							* 0.3f, enY + e.getHeight() * 0.3f);

					RectF rectPlayer = new RectF(pX, pY, pX
							+ oPlayer.getWidth() * 0.3f, pY
							+ oPlayer.getHeight() * 0.3f);

					if (rectEnemy.intersect(rectPlayer)) {
						resourcesManager.activity.sndHitToPlayer.play();
						e.setVisible(false);
						e.destroy = true;
						controlEntity.removeLive();
					}

				}
			}
		}

		if (!tmpEnemy.isEmpty()) {
			for (ElementEnemy e : tmpEnemy) {
				listEnemy.remove(e);
				detachChild(e);
			}
		}

	}

	private void _startingLevel() {
		if (oPlayer.getX() + oPlayer.width * 0.5f >= GameActivity.CAMERA_WIDTH / 2) {
			oPlayer.setRotation(0);
			oPlayer.mPhysicsHandler.setVelocityX(0);
			ready = true;
			enemy.execute();
			this.registerTouchArea(oPlayer);
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
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionBear
					.deepCopy();
			break;
		case ElementEnemy.ENEMY_PONY:
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionPony
					.deepCopy();
			break;
		case ElementEnemy.ENEMY_RABBIT:
			tmpTiledTextureRegion = resourcesManager.activity.mEnemyTiledTextureRegionRabbit
					.deepCopy();
			break;
		default:
		}

		if (tmpTiledTextureRegion != null) {
			ElementEnemy e = new ElementEnemy(oPlayer, tmpTiledTextureRegion,
					resourcesManager.vbom);

			listEnemy.add(e);

			float rnd = new Random().nextFloat();

			if (rnd < 0.5f) {
				e.setZIndex(500);
			} else {
				e.setZIndex(1500);
			}

			this.attachChild(e);
			this.registerTouchArea(e);
		}

	}

	private void loadMusic() {
		try {
			bgMusic = MusicFactory.createMusicFromAsset(
					resourcesManager.activity.getMusicManager(),
					resourcesManager.activity, "audio/intro_parte2_MP3.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (bgMusic != null) {
			bgMusic.setLooping(true);
		}
	}

	// remove enemies

	public void removeSprite(final ProjectilSprite _sprite,
			Iterator<ProjectilSprite> it) {
		resourcesManager.activity.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				removeChildSp(_sprite);

			}
		});
		it.remove();
	}

	public void removeChildSp(Sprite sp) {
		this.detachChild(sp);
	}

	private void _stopGame() {

		for (ElementEnemy e : listEnemy) {
			this.unregisterTouchArea(e);
			detachChild(e);
		}

		this.unregisterTouchArea(oPlayer);

		resourcesManager.engine.clearUpdateHandlers();
		resourcesManager.engine.unregisterUpdateHandler(render);

		if (!controlEntity.isAlive()) {
			gameOverEntity.setGame(false);
		} else if (controlEntity.isAlive() && oPlayer.score >= 20) {
			gameOverEntity.setGame(true);
		}

		this.registerTouchArea(gameOverEntity.spGameOver);
		gameOverEntity.setVisible(true);
	}

}
