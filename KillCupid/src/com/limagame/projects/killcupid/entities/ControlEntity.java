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
		/*spBar = new Sprite(0, 0, resourcesManager.activity.,
				resourcesManager.activity.getVertexBufferObjectManager());*/
	}

}
