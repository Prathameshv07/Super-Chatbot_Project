package com.example.superchatbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 6, 0},
        k = 1,
        d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J\u0012\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\fJ\b\u0010\u001a\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"},
        d2 = {"Lcom/example/superchatbot/stepActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/hardware/SensorEventListener;", "()V", "previousTotalSteps", "", "running", "", "sensorManager", "Landroid/hardware/SensorManager;", "totalSteps", "loadData", "", "onAccuracyChanged", "sensor", "Landroid/hardware/Sensor;", "accuracy", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "resetSteps", "saveData", "Super_Chatbot.app.main"}
)
public class stepActivity extends AppCompatActivity implements SensorEventListener {

    // Added SensorEventListener the MainActivity class
    // Implement all the members in the class MainActivity
    // after adding SensorEventListener

    // we have assigned sensorManger to nullable
    private SensorManager sensorManager;

    // Creating a variable which will give the running status
    // and initially given the boolean value as false
    private boolean running;

    // Creating a variable which will counts total steps
    // and it has been given the value of 0 float
    private float totalSteps;

    // Creating a variable which counts previous total
    // steps and it has also been given the value of 0 float
    private float previousTotalSteps;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.step_activity);

        this.loadData();
        this.resetSteps();

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    protected void onResume() {
        super.onResume();
        this.running = true;

        // Returns the number of steps taken by the user since the last reboot while activated
        // This sensor requires permission android.permission.ACTIVITY_RECOGNITION.
        // So don't forget to add the following permission in AndroidManifest.xml present in manifest folder of the app.
        SensorManager var10000 = this.sensorManager;
        Sensor stepSensor = var10000 != null ? var10000.getDefaultSensor(19) : null;
        if (stepSensor == null) {
            Toast.makeText((Context)this, (CharSequence)"No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            var10000 = this.sensorManager;
            if (var10000 != null) {
                var10000.registerListener((SensorEventListener)this, stepSensor, 2);
            }
        }

    }

    public void onSensorChanged(@Nullable SensorEvent event) {

        // Calling the TextView that we made in activity_main.xml
        // by the id given to that TextView
        TextView tv_stepsTaken = (TextView)this.findViewById(R.id.tv_stepsTaken);
        if (this.running) {
            Intrinsics.checkNotNull(event);
            this.totalSteps = event.values[0];

            // Current steps are calculated by taking the difference of total steps
            // and previous steps
            int currentSteps = (int)this.totalSteps - (int)this.previousTotalSteps;
            Intrinsics.checkNotNullExpressionValue(tv_stepsTaken, "tv_stepsTaken");
            tv_stepsTaken.setText((CharSequence)String.valueOf(currentSteps));
        }

    }

    public final void resetSteps() {
        final ObjectRef tv_stepsTaken = new ObjectRef();
        tv_stepsTaken.element = (TextView)this.findViewById(R.id.tv_stepsTaken);
        ((TextView)tv_stepsTaken.element).setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                Toast.makeText((Context)stepActivity.this, (CharSequence)"Long tap to reset steps", Toast.LENGTH_SHORT).show();
            }
        }));
        ((TextView)tv_stepsTaken.element).setOnLongClickListener((OnLongClickListener)(new OnLongClickListener() {
            public final boolean onLongClick(View it) {
                stepActivity.this.previousTotalSteps = stepActivity.this.totalSteps;

                // When the user will click long tap on the screen,
                // the steps will be reset to 0
                TextView var10000 = (TextView)tv_stepsTaken.element;
                Intrinsics.checkNotNullExpressionValue(var10000, "tv_stepsTaken");
                var10000.setText((CharSequence)String.valueOf(0));

                // This will save the data
                stepActivity.this.saveData();
                return true;
            }
        }));
    }

    private final void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("myPrefs", 0);
        Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", this.previousTotalSteps);
        editor.apply();
    }

    private final void loadData() {

        // In this function we will retrieve data
        SharedPreferences sharedPreferences = this.getSharedPreferences("myPrefs", 0);
        float savedNumber = sharedPreferences.getFloat("key1", 0.0F);

        // Log.d is used for debugging purposes
        Log.d("MainActivity", String.valueOf(savedNumber));
        this.previousTotalSteps = savedNumber;
    }

    public void onAccuracyChanged(@Nullable Sensor sensor, int accuracy) {
    }

    // $FF: synthetic method
    public static final float access$getPreviousTotalSteps$p(stepActivity $this) {
        return $this.previousTotalSteps;
    }

    // $FF: synthetic method
    public static final void access$setTotalSteps$p(stepActivity $this, float var1) {
        // We do not have to write anything in this function for this app
        $this.totalSteps = var1;
    }
}