package com.nndevelopments.paranoidandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class ScreamerService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		sSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sSensorManager.registerListener(sSensorListener, sSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private SensorManager sSensorManager;
	private final SensorEventListener sSensorListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			for( float value: event.values ){
				if(Math.abs(value) > .5){
					return;
				}
			}
			//System.out.println("I'm falling!");
			Log.i("ScreamerService", "I'm falling");
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	@Override
	public void onDestroy() {
		sSensorManager.unregisterListener(sSensorListener);
		super.onDestroy();
	}
	
}
