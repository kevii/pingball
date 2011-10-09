package de.vogella.android.sensor;

import android.app.Activity;
import de.vogella.android.sensor.BallDrawable;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SensorTest extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private Sensor sensor;
	private boolean color = false; 
	private View view;
	private long lastUpdate;
	private BallDrawable bDrawable;
	private int SENSOR_TYPE = 0;
	
/** Called when the activity is first created. */

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//		view = findViewById(R.id.textView);
//		view.setBackgroundColor(Color.GRAY);
		
		bDrawable = new BallDrawable(this);
		setContentView(bDrawable);
		
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		SENSOR_TYPE = Sensor.TYPE_ACCELEROMETER;
        sensor = sensorManager.getDefaultSensor(SENSOR_TYPE);
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		lastUpdate = System.currentTimeMillis();
		
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == SENSOR_TYPE) {
			float[] values = event.values;
			// Movement
			float x = values[0];
			float y = values[1];
			float z = values[2];
			String message = "TYPE_ACCELEROMETER\n" 
					+ "z:" + values[0] + "\n"
					+ "x:" + values[1] + "\n"
					+ "y:" + values[2];
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//			float accelationSquareRoot = (x * x + y * y + z * z)
//					/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
//			long actualTime = System.currentTimeMillis();
//			if (accelationSquareRoot >= 2) //
//			{
//				if (actualTime - lastUpdate < 200) {
//					return;
//				}
//				lastUpdate = actualTime;
//				Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
//						.show();
//				
//				if (color) {
//					view.setBackgroundColor(Color.GREEN);
//					
//				} else {
//					view.setBackgroundColor(Color.RED);
//				}
//				color = !color;
//			}

		}

	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
		
	}
}