package com.limagame.projects.killcupid;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.KeyEvent;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.manager.SceneManager;

public class GameActivity extends SimpleBaseGameActivity {

	public Camera camera;
	public static final int CAMERA_WIDTH = 800;
	public static final int CAMERA_HEIGHT = 480;

	// --------------------------------------------------

	// private Camera mCamera;

	// private BitmapTextureAtlas mBitmapTextureAtlas;W

	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

	public TiledTextureRegion mPlayerTiledTextureRegionRotoman;
	public TiledTextureRegion mPlayerTiledTextureRegion;
	public TiledTextureRegion mPlayerTiledTextureRegionCupid;

	public TiledTextureRegion mSnapdragonTextureRegion;
	public TiledTextureRegion mHelicopterTextureRegion;
	public TiledTextureRegion mBananaTextureRegion;
	public TiledTextureRegion mFaceTextureRegion;

	// public TiledTextureRegion mPlayerTiledTextureRegionBear;
	// public TiledTextureRegion mPlayerTiledTextureRegionBear2;

	// public TiledTextureRegion mPlayerTiledTextureRegionRabbit;
	// public TiledTextureRegion mPlayerTiledTextureRegionRabbit2;

	// public TiledTextureRegion mPlayerTiledTextureRegionPonny;
	// public TiledTextureRegion mPlayerTiledTextureRegionPonny2;

	public TiledTextureRegion mPlayerTiledTextureRegionCupidBoss;
	public TiledTextureRegion mPlayerTiledTextureRegionProjectile;

	public TiledTextureRegion mEnemyTiledTextureRegionBear;
	public TiledTextureRegion mEnemyTiledTextureRegionBear2;

	public TiledTextureRegion mEnemyTiledTextureRegionPony;
	public TiledTextureRegion mEnemyTiledTextureRegionPony2;

	public TiledTextureRegion mEnemyTiledTextureRegionRabbit;
	public TiledTextureRegion mEnemyTiledTextureRegionRabbit2;

	public ITextureRegion mHeartBroken;
	public ITextureRegion mHeartComplete;
	public ITextureRegion mbgTiledTexture;
	public ITextureRegion mCreditsBg;
	public ITextureRegion mMainMenuBg;
	public ITextureRegion mGameWin;
	public ITextureRegion mGameLose;
	public ITextureRegion mAngryAvailable;

	public Sound sndGrito;
	public Sound sndHits[];
	public Sound sndHitToPlayer;
	public Sound sndArrow;

	public Font mFont;
	public Font mFontMenu;

	// intro ---
	public ITextureRegion mIntro01;
	public ITextureRegion mIntro02;
	public ITextureRegion mIntro03;
	public ITextureRegion mIntro04;
	public ITextureRegion mIntro05;
	public ITextureRegion mIntro06;
	public ITextureRegion mIntro07;
	public ITextureRegion mIntro08;
	public ITextureRegion mIntro09;

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

		_loadFont();
		_loadSounds();

		ResourcesManager.prepareManager(mEngine, this, camera,
				getVertexBufferObjectManager());

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 800, 480, TextureOptions.DEFAULT);
		this.mPlayerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rotoman2.png", 1, 1);
		this.mPlayerTiledTextureRegionRotoman = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rotoman1.png", 1, 1);
		this.mPlayerTiledTextureRegionCupid = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"cupid1.png", 1, 1);

		this.mEnemyTiledTextureRegionPony = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"pony1.png", 1, 1);
		this.mEnemyTiledTextureRegionPony2 = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"pony2.png", 1, 1);

		this.mEnemyTiledTextureRegionRabbit = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rabit1.png", 1, 1);

		this.mEnemyTiledTextureRegionRabbit2 = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"rabit2.png", 1, 1);

		this.mEnemyTiledTextureRegionBear = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"bear1.png", 1, 1);
		this.mEnemyTiledTextureRegionBear2 = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"bear2.png", 1, 1);

		// juanisimo2.png
		this.mPlayerTiledTextureRegionCupidBoss = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"juanisimo2.png", 1, 1);

		this.mPlayerTiledTextureRegionProjectile = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.mBitmapTextureAtlas, this,
						"flecha120x150.png", 1, 1);

		// Load Enemies

		/*
		 * this.mEnemyTiledTextureRegionBear =
		 * BitmapTextureAtlasTextureRegionFactory
		 * .createTiledFromAsset(this.mBitmapTextureAtlas, this, "bear.png", 1,
		 * 1);
		 * 
		 * this.mEnemyTiledTextureRegionPony =
		 * BitmapTextureAtlasTextureRegionFactory
		 * .createTiledFromAsset(this.mBitmapTextureAtlas, this, "pony.png", 1,
		 * 1);
		 * 
		 * this.mEnemyTiledTextureRegionRabbit =
		 * BitmapTextureAtlasTextureRegionFactory
		 * .createTiledFromAsset(this.mBitmapTextureAtlas, this, "rabit2.png",
		 * 1, 1);
		 */

		// End Load Enemies

		ITexture mTexture = null;

		mTexture = _loadTexture("c2.png");
		this.mHeartBroken = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("c1.png");
		this.mHeartComplete = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("bgGame.jpg");
		this.mbgTiledTexture = TextureRegionFactory
				.extractFromTexture(mTexture);

		mTexture = _loadTexture("CREDITOS.png");
		this.mCreditsBg = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("FONDO_MENU.png");
		this.mMainMenuBg = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("ganaste.png");
		this.mGameWin = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("perdiste.png");
		this.mGameLose = TextureRegionFactory.extractFromTexture(mTexture);

		mTexture = _loadTexture("face_angry.png");
		this.mAngryAvailable = TextureRegionFactory
				.extractFromTexture(mTexture);

		try {
			this.mBitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 0, 1));
			this.mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			// Debug.e(e);
		}

		getEngine().enableVibrator(this);
	}

	private ITexture _loadTexture(final String path) {
		ITexture mTexture = null;
		try {
			mTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open(path);
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}

		mTexture.load();
		return mTexture;
	}

	@Override
	protected Scene onCreateScene() {
		return SceneManager.getInstance().createScene(SceneManager.MENUSCENEID);
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, 60);
	}

	private void _loadSounds() {
		// Cargar grito
		try {
			sndGrito = SoundFactory.createSoundFromAsset(getEngine()
					.getSoundManager(), this, "audio/grito1.mp3");

			sndHitToPlayer = SoundFactory.createSoundFromAsset(getEngine()
					.getSoundManager(), this, "audio/wuiiii_hit.mp3");

			sndArrow = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "audio/flecha_sfx.mp3");

		} catch (IOException e) {
			e.printStackTrace();
		}

		sndHits = new Sound[3];

		try {
			sndHits[0] = SoundFactory.createSoundFromAsset(getEngine()
					.getSoundManager(), this, "audio/hit1.mp3");

			sndHits[1] = SoundFactory.createSoundFromAsset(getEngine()
					.getSoundManager(), this, "audio/hit2.mp3");

			sndHits[2] = SoundFactory.createSoundFromAsset(getEngine()
					.getSoundManager(), this, "audio/hit3.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
		}
		return false;
	}

	private void _loadFont() {

		final ITexture fontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

		this.mFont = FontFactory.create(getFontManager(), getTextureManager(),
				256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 64);
		this.mFont.load();

		this.mFontMenu = new Font(this.getFontManager(), fontTexture,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 40, true,
				Color.WHITE);
		this.mFontMenu.load();

	}
}
