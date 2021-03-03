package com.test.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Test extends ApplicationAdapter {
	ShapeRenderer renderer;
	SpriteBatch batch;
	List<float[]> list;
	Timer timer;
	int height;
	int width;

	class Helper extends TimerTask{
		public void run() {
			int x = (int)(Math.random() * (height - 200) + 100);
			int y = (int)(Math.random() * (width - 200) + 100);
			float r = (float)Math.random();
			float g = (float)Math.random();
			float b = (float)Math.random();
			float[] temp = {x, y, 100, r, g, b, 1};
			list.add(temp);
		}
	}

	@Override
	public void create () {
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();
		list = new ArrayList<float[]>();
		timer = new Timer("timer");
		TimerTask task = new Helper();
		timer.scheduleAtFixedRate(task, 0, 500);
		height = Gdx.graphics.getWidth();
		width = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		tick();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		for(float[] i : list){
			renderer.setColor(i[3], i[4], i[5], i[6]);
			renderer.circle(i[0], i[1], i[2]);
		}
		renderer.end();
	}

	public void tick () {
		for(int x = 0; x < list.size(); x++){
			float[] i = list.get(x);
			i[2] += 5;
			i[6] -= 0.01;
			if(i[6] <= 0){
				list.remove(x);
				x--;
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
