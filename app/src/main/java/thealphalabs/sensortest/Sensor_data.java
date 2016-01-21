package thealphalabs.sensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by yeol on 16. 1. 18.
 */
public class Sensor_data {
    private static final String ACCEL_DATA= "ACCEL_DATA";
    private static final String GYRO_DATA= "GYRO_DATA";
    private static final String MAG_DATA= "MAG_DATA";
    private static final String QUARTERNION_DATA= "QUART_DATA";

    public Sensor accel;
    public Sensor gyro;
    public Sensor mag;
    public Sensor quart;

    public TextView accel_x, accel_y, accel_z;
    public TextView mag_x, mag_y, mag_z;
    public TextView gyro_x, gyro_y, gyro_z;
    public TextView filter_x,filter_y,filter_z;

    public SensorManager mSensorManager;
    public DecimalFormat round;

    private float magnetic[] =null;
    private float acceleration[] = null;

    private float rotation[]= new float[9];
    private float orientation[] = new float[3];

    private static LowpassFilter lowpass=new LowpassFilter();

    public Sensor_data(Context context){
        mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accel=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro=mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mag=mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        round=new DecimalFormat("0.0000");
  //      quart=mSensorManager.getDefaultSensor(Sensor.)
    }

    public void registerListener(){
        mSensorManager.registerListener(accelEventListener,accel,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(gyroEventListener,gyro,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(magEventListener,mag,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegisterListener(){
        mSensorManager.unregisterListener(accelEventListener);
        mSensorManager.unregisterListener(gyroEventListener);
        mSensorManager.unregisterListener(magEventListener);

    }
    public SensorEventListener accelEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float newMat[]=new float[16];
            accel_x.setText(""+round.format(event.values[0]));
            accel_y.setText(""+round.format(event.values[1]));
            accel_z.setText(""+round.format(event.values[2]));

          //  acceleration=lowpass.lowPass(event.values,0.05f);
            acceleration=event.values.clone();
            if(magnetic != null){
                mSensorManager.getRotationMatrix(rotation,null,acceleration,magnetic);
                mSensorManager.getOrientation(rotation,orientation);
                filter_x.setText(""+round.format(orientation[0]));
                filter_y.setText(""+round.format(orientation[1]));
                filter_z.setText("" + round.format(orientation[2]));
            }
         /*   filter_x.setText(""+round.format(acceleration[0]));
            filter_y.setText(""+round.format(acceleration[1]));
            filter_z.setText("" + round.format(acceleration[2]));*/
           // mSensorManager.getRotationMatrix(newMat,null,acceleration,orientation);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public SensorEventListener gyroEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            gyro_x.setText(""+round.format(event.values[0]));
            gyro_y.setText(""+round.format(event.values[1]));
            gyro_z.setText(""+round.format(event.values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public SensorEventListener magEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            mag_x.setText(""+round.format(event.values[0]));
            mag_y.setText("" + round.format(event.values[1]));
            mag_z.setText("" + round.format(event.values[2]));

            magnetic=event.values.clone();
            if(acceleration != null){
                mSensorManager.getRotationMatrix(rotation,null,acceleration,magnetic);
                mSensorManager.getOrientation(rotation,orientation);
                filter_x.setText(""+round.format(orientation[0]));
                filter_y.setText(""+round.format(orientation[1]));
                filter_z.setText("" + round.format(orientation[2]));
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public SensorEventListener quartEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public SensorEventListener pedoEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
