package thealphalabs.sensortest;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {

    private static final String TAG="SensorTest";
    private int accelXValue;
    private int accelYValue;
    private int accelZValue;

    private int gyroX;
    private int gyroY;
    private int gyroZ;

    public SensorManager mSensorManager;
    private static Sensor_data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new Sensor_data(this);
        setTextView();
    }

    private void setTextView() {
        data.gyro_x=(TextView)findViewById(R.id.gyro_x);
        data.gyro_y=(TextView)findViewById(R.id.gyro_y);
        data.gyro_z=(TextView)findViewById(R.id.gyro_z);
        data.accel_x=(TextView)findViewById(R.id.accel_x);
        data.accel_y=(TextView)findViewById(R.id.accel_y);
        data.accel_z=(TextView)findViewById(R.id.accel_z);
        data.mag_x=(TextView)findViewById(R.id.mag_x);
        data.mag_y=(TextView)findViewById(R.id.mag_y);
        data.mag_z=(TextView)findViewById(R.id.mag_z);
        data.filter_x=(TextView)findViewById(R.id.filter_x);
        data.filter_y=(TextView)findViewById(R.id.filter_y);
        data.filter_z=(TextView)findViewById(R.id.filter_z);
    }

    //리스너 등록
    protected void onResume() {
        super.onResume();
        data.registerListener();
    }

    //리스너 해제
    protected void onPause() {
        super.onPause();
        data.unRegisterListener();
    }
}
