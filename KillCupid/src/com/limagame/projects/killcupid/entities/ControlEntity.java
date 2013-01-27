package com.limagame.projects.killcupid.entities;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.limagame.projects.killcupid.manager.ResourcesManager;
import com.limagame.projects.killcupid.view.ui.Player;

public class ControlEntity extends Entity {

	private List<Sprite> lstHealthBar;
	private ResourcesManager resourcesManager;
	private int lives;

	public static final int marginX = 10;

	public ControlEntity(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
		_createEntity();
	}

	private void _createEntity() {

/*<<<<<<< HEAD
		spBar = new Sprite(0, 0,
				resourcesManager.activity.mPlayerTiledTextureRegion,
				resourcesManager.activity.getVertexBufferObjectManager());

=======*/
		lives = Player.LIVES;
		lstHealthBar = new ArrayList<Sprite>();

		float x = 0;

		for (int i = 0; i < lives; i++) {
			Sprite s = new Sprite(x, 0, resourcesManager.activity.mHeartBroken,
					resourcesManager.activity.getVertexBufferObjectManager());
			lstHealthBar.add(s);
			attachChild(s);
			x += s.getWidth() + marginX;
		}
	}

	public void removeLive() {
		if (lives > 0) {
			Sprite s1 = lstHealthBar.get(lives - 1);
			Sprite s2 = new Sprite(s1.getX(), s1.getY(),
					resourcesManager.activity.mHeartComplete,
					resourcesManager.activity.getVertexBufferObjectManager());
			lstHealthBar.remove(s1);
			lstHealthBar.add(lives - 1, s2);
			detachChild(s1);
			attachChild(s2);
			s1.dispose();
			lives--;
		}

	}

	public boolean isAlive() {
		return lives > 0;
	}
}
