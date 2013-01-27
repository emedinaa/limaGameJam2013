package com.limagame.projects.killcupid;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;
import com.limagame.projects.killcupid.view.ui.CupidEnemy;
import com.limagame.projects.killcupid.view.ui.Player;

public class GameActivity extends SimpleBaseGameActivity {

	public Camera camera;
	public static final int CAMERA_WIDTH = 800;
	public static final int CAMERA_HEIGHT = 480;
	private ResourcesManager resourcesManager;

	// --------------------------------------------------

	// private Camera mCamera;
	private Scene mMainScene;

	// private BitmapTextureAtlas mBitmapTextureAtlas;W

	private BitmapTextureAtlas mBitmapTextureAtlasRotoman;
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

	public TiledTextureRegion mPlayerTiledTextureRegionRotoman;
	public TiledTextureRegion mPlayerTiledTextureRegion;
	public TiledTextureRegion mPlayerTiledTextureRegionCupid;

	public TiledTextureRegion mSnapdragonTextureRegion;
	public TiledTextureRegion mHelicopterTextureRegion;
	public TiledTextureRegion mBananaTextureRegion;
	public TiledTextureRegion mFaceTextureRegion;

	public TiledTextureRegion mPlayerTiledTextureRegionBear;
	public TiledTextureRegion mPlayerTiledTextureRegionRabbit;
	public TiledTextureRegion mPlayerTiledTextureRegionPonny;

	public TiledTextureRegion mPlayerTiledTextureRegionCupidBoss;
	public TiledTextureRegion mPlayerTiledTextureRegionProjectile;

	public TiledTextureRegion mEnemyTiledTextureRegionBear;
	public TiledTextureRegion mEnemyTiledTextureRegionPony;
	public TiledTextureRegion mEnemyTiledTextureRegionRabbit;
	
	public TiledTextureRegion mbgTiledTexture;

	private IUpdateHandler render;
	private Player oPlayer;
	private CupidEnemy enemy;

	// -------------------------------------------------

	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), camera);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		engineOptions.getAudioOptions().setNeedsMusic(true);
		engineOptions.getAudioOptions().setNeedsSound(true);
		return engineOptions;
	}

	@Override
	protected void onCreateResources() {
		ResourcesManager.prepareManager(mEngine, this, camera,
				getVertexBufferObjectManager());
		resourcesManager = ResourcesManager.getInstance();

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 800, 480, TextureOptions.DEFAULT);
		this.mPlayerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rotoman1.png", 1, 1);
		this.mPlayerTiledTextureRegionRotoman = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rotoman2.png", 1, 1);
		this.mPlayerTiledTextureRegionCupid = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"cupid1.png", 1, 1);

		this.mPlayerTiledTextureRegionPonny = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"pony.png", 1, 1);
		this.mPlayerTiledTextureRegionRabbit = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rabit2.png", 1, 1);
		this.mPlayerTiledTextureRegionBear = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"bear.png", 1, 1);

		// juanisimo2.png
		this.mPlayerTiledTextureRegionCupidBoss = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"juanisimo2.png", 1, 1);

		this.mPlayerTiledTextureRegionProjectile = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"flecha120x150.png", 1, 1);

		// Load Enemies

		this.mEnemyTiledTextureRegionBear = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"bear.png", 1, 1);

		this.mEnemyTiledTextureRegionPony = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"pony.png", 1, 1);

		this.mEnemyTiledTextureRegionRabbit = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rabit2.png", 1, 1);
		
		this.mbgTiledTexture = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"bgGame.png", 1, 1);

		// End Load Enemies

		try {
			this.mBitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 0, 1));
			this.mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			// Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		// return SceneManager.getInstance().createSplashScene();
		return SceneManager.getInstance().createScene(SceneManager.GAMESCENEID);
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, 60);
	}

}
