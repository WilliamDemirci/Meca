package comwilliamdemirci.github.meca;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {
    private Switch themeSwitch;
    private ConstraintLayout mainLayout;
    private JoystickView joystickRight;
    private ImageView turnAroundLeftButton;
    private ImageView turnAroundRightButton;
    private SeekBar speedBar;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        setTheme();
        sendData();
    }

    private void sendData() {
        getMove();
        getSpeed();
    }

    private void getSpeed() {
        getSpeedBarValue();
    }

    private void getSpeedBarValue() {
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                i++;
                Log.d("Speed", "Seekbar : " + String.valueOf(progress) + " | i = " + String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getMove() {
        getJoystickMove();
        getTurnAroundAction();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void getTurnAroundAction() {
        // on turn around left button click
        turnAroundLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    turnAroundLeftButton.setImageResource(R.drawable.ic_turn_around_pressed_512dp);
                    Log.d("Move", "Turn around left pressed");
                }
                else {
                    turnAroundLeftButton.setImageResource(R.drawable.ic_turn_around_released_512dp);
                    Log.d("Move", "Turn around left released");
                }
                return true;
            }
        });

        // on turn around right button click
        turnAroundRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    turnAroundRightButton.setImageResource(R.drawable.ic_turn_around_pressed_512dp);
                    Log.d("Move", "Turn around right pressed");
                }
                else {
                    turnAroundRightButton.setImageResource(R.drawable.ic_turn_around_released_512dp);
                    Log.d("Move", "Turn around right released");
                }
                return true;
            }
        });
    }

    private void getJoystickMove() {
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onMove(int angle, int strength) {
                i++;
                if((angle > 337) || (angle <= 22) && (strength > 0)) { // right
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - right" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 22) && (angle <= 67) && (strength > 0)) { // up right
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - up right" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 67) && (angle <= 112) && (strength > 0)) { // up
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - up" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 112) && (angle <= 157) && (strength > 0)) { // up left
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - up left" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 157) && (angle <= 202) && (strength > 0)) { // left
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - left" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 202) && (angle <= 247) && (strength > 0)) { // down left
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - down left" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 247) && (angle <= 292) && (strength > 0)) { // down
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - down" +  " | i = " +String.valueOf(i));
                }
                else if((angle > 292) && (angle <= 337) && (strength > 0)) { // down right
                    Log.d("Move", "Joystick : " + String.valueOf(angle) + "° - down right" +  " | i = " +String.valueOf(i));
                }
            }
        });
    }

    private void setTheme() { // change the theme if Switch is checked
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mainLayout.setBackgroundResource(R.color.colorBackgroundDark);
                }
                else {
                    mainLayout.setBackgroundResource(R.color.colorBackgroundLight);
                }
            }
        });
    }

    private void findViewById() { // find view elements
        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        themeSwitch = (Switch) findViewById(R.id.themeSwitch);
        joystickRight = (JoystickView) findViewById(R.id.joystick);
        turnAroundLeftButton = (ImageView) findViewById(R.id.turnAroundLeftButton);
        turnAroundRightButton = (ImageView) findViewById(R.id.turnAroundRightButton);
        speedBar = (SeekBar) findViewById(R.id.speedBar);
    }
}