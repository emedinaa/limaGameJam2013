/**
 * 
 */
package com.limagame.projects.killcupid.view.ui;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * @author notebookqd
 *
 */
public class CupidBossEnemy extends GameObject 
{
	public CupidBossEnemy(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) 
	{
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.PerleDevelopment.AndEngine.tutorial.objects.GameObject#move()
	 */
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
