package com.lockmeout1.johnerl.lmo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  public static final long HOUR_IN_MILLIS = 36000000;
  public static final long MINUTE_IN_MILLIS = 60000;
  public static final long SECOND_IN_MILLIS = 1000;

  private Button samplebnt;
  private Timerset mTimerset;
  private Timerset hTimerset;
  private Timerset sTimerset;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sTimerset = (Timerset) findViewById(R.id.STimerset);
    hTimerset = (Timerset) findViewById(R.id.HTimerset);
    mTimerset = (Timerset) findViewById(R.id.MTimerset);
    samplebnt = (Button) findViewById(R.id.sampleButton);

    samplebnt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        long secondsINmillisec = sTimerset.getvalue() * SECOND_IN_MILLIS;
        long minINmillisec = mTimerset.getvalue() * MINUTE_IN_MILLIS;
        long hourINmillisec = hTimerset.getvalue() * HOUR_IN_MILLIS;
        BlockScreen(hourINmillisec + minINmillisec + secondsINmillisec);
      }
    });
  }

  private void BlockScreen(long timeInMillis) {

    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.TYPE_TOAST,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT);

    final WindowManager wm = (WindowManager) getApplicationContext()
            .getSystemService(Context.WINDOW_SERVICE);

    final ViewGroup mTopView = (ViewGroup) getLayoutInflater().inflate(R.layout.blocked_screen, null);
    final TextView clockId = (TextView) mTopView.findViewById(R.id.clockId);

    wm.addView(mTopView, params);

    new CountDownTimer(timeInMillis, 1000) {

      public void onTick(long millisUntilFinished) {

        final long hour = millisUntilFinished / HOUR_IN_MILLIS;
        final long minutes = millisUntilFinished / MINUTE_IN_MILLIS;
        final long secondes = millisUntilFinished / SECOND_IN_MILLIS;

        clockId.setText("                          " + hour + ":" + minutes + ":" + secondes);
      }

      public void onFinish() {
        wm.removeView(mTopView);
      }
    }.start();
  }
}
