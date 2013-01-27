package com.limagame.projects.killcupid.entities;

import org.andengine.entity.Entity;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.limagame.projects.killcupid.manager.ResourcesManager;

public class GameOverEntity extends Entity {

	private ResourcesManager resourcesManager;

	public GameOverEntity(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
		_createEntity();
	}

	private void _createEntity() {
		final VertexBufferObjectManager vertexBufferObjectManager = resourcesManager.activity
				.getVertexBufferObjectManager();

		final Text gameOverText = new Text(200, 200,
				ResourcesManager.getInstance().activity.mFont, "Game Over!",
				new TextOptions(HorizontalAlign.CENTER),
				vertexBufferObjectManager);
		gameOverText.setColor(Color.BLUE);

		attachChild(gameOverText);

	}
}
