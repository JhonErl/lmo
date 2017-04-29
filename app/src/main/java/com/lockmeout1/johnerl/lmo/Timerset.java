package com.lockmeout1.johnerl.lmo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JohnErl on 15/04/2017.
 */

public class Timerset extends LinearLayout /*implements View.OnTouchListener*/ {


  public static final String DEBUG_TAG = "DEBUG_TAG";

  private Button upbtn;
  private Button dowdbtn;
  private TextView display;
  private int value = 0;


  public Timerset(Context context) {
    super(context);
    initializeViews(context);
  }

  public Timerset(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializeViews(context);
  }

  public Timerset(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializeViews(context);
  }

  public Timerset(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initializeViews(context);
  }

  /**
   * Inflates the views in the layout.
   *
   * @param context the current context for the view
   */
  private void initializeViews(Context context) {
    LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.timerset_view, this);

    upbtn = (Button) this.findViewById(R.id.upButton);
    dowdbtn = (Button) this.findViewById(R.id.downButton);
    display = (TextView) this.findViewById(R.id.Display);

    display.setOnTouchListener(new OnTouchListener() {
      float dy = 0;
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {

          case MotionEvent.ACTION_DOWN:
            dy = motionEvent.getRawY();
            Log.e("ACTION_DOWN - dy"," "+dy);
            Log.i("DOWN:view.getY() = ", "" + view.getY());
            Log.i("DOWN:Event.getRawY() = ", "" + motionEvent.getRawY());
            break;

          case MotionEvent.ACTION_MOVE:
            if(dy <= motionEvent.getRawY()) decrement();
            else increment();
            dy = motionEvent.getRawY();
            Log.i("MOVE:view.getY() = ", "" + view.getY());
            Log.i("MOVE:Event.getRawY() = ", "" + motionEvent.getRawY());
            break;

          case MotionEvent.ACTION_UP:
            if(dy <= motionEvent.getRawY()) decrement();
            else increment();
            Log.e("ACTION_UP - dy"," "+dy);
            Log.i("UP:view.getY() = ", "" + view.getY());
            Log.i("UP:Event.getRawY() = ", "" + motionEvent.getRawY());

          default:
            return false;
        }
        return true;
      }
    });

    upbtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        increment();
      }
    });

    dowdbtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        decrement();
      }
    });
  }

  public void increment() {
    if (value >= 60) value = 0;
    else value++;

    display.setText("     " + ((value >= 10) ? value : "0"+value));
  }

  public void decrement() {
    if (value <= 0) value = 60;
    else value--;
    display.setText("     " + ((value >= 10) ? value : "0"+value) );
  }

  public int getvalue() {
    value = Integer.parseInt(display.getText().toString().trim());
    return value;
  }
}
