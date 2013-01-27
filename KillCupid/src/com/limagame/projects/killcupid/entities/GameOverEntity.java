package com.limagame.projects.killcupid.entities;

import org.andengine.entity.Entity;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

import android.graphics.Typeface;

import com.limagame.projects.killcupid.manager.ResourcesManager;

public class GameOverEntity extends Entity {

	private ResourcesManager resourcesManager;
	private Font mFont;

	public GameOverEntity(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
		_createEntity();
	}

	private void _createEntity() {
		this.mFont = FontFactory.create(
				resourcesManager.activity.getFontManager(),
				resourcesManager.activity.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 64);
		this.mFont.load();

		final VertexBufferObjectManager vertexBufferObjectManager = resourcesManager.activity
				.getVertexBufferObjectManager();

		final Text centerText = new Text(200, 200, this.mFont, "Game Over!",
				new TextOptions(HorizontalAlign.CENTER),
				vertexBufferObjectManager);

		attachChild(centerText);

	}
}
