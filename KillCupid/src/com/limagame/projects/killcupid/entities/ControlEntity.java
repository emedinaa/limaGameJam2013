package com.limagame.projects.killcupid.entities;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.limagame.projects.killcupid.manager.ResourcesManager;

public class ControlEntity extends Entity {

	private Sprite spBar;
	private ResourcesManager resourcesManager;

	public ControlEntity(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
		_createEntity();
	}

	private void _createEntity() {
<<<<<<< HEAD
		/*spBar = new Sprite(0, 0, resourcesManager.activity.,
				resourcesManager.activity.getVertexBufferObjectManager());*/
=======
		spBar = new Sprite(0, 0,
				resourcesManager.activity.mPlayerTiledTextureRegion,
				resourcesManager.activity.getVertexBufferObjectManager());
>>>>>>> 2417b2c51c9efa468b9d8ff3abe5c819b734d6db
	}

}
