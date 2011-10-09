package de.vogella.android.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class BallDrawable extends View {
	private ShapeDrawable bDrawable;
	
	public BallDrawable(Context context){
		super(context);
		
		int x = 10;
		int y = 10;
		int width = 300;
		
		bDrawable = new ShapeDrawable(new OvalShape());
		bDrawable.getPaint().setColor(0xff74AC23);
		bDrawable.setBounds(x, y, x + width, y + width);
	}
	
	protected void onDraw(Canvas canvas){
		bDrawable.draw(canvas);
	}

}
