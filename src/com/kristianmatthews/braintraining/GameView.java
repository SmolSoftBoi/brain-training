package com.kristianmatthews.braintraining;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class GameView extends View {

	private static final String TAG = "Brain Training";

	private final GameActivity game;

	public GameView(Context context) {
		super(context);
		this.game = (GameActivity) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldWidth,
			int oldHeight) {
		Log.d(TAG, "On Size Changed Width: " + width);
		Log.d(TAG, "On Size Changed Height: " + height);
		super.onSizeChanged(width, height, oldWidth, oldHeight);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// Background
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.game_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// Board

		// Box colour
		Paint box = new Paint();
		box.setColor(getResources().getColor(R.color.game_box));
		// Box
		canvas.drawRect(getWidth() / 10f, (getHeight() / 10f) * 2,
				(getWidth() / 10f) * 9, (getHeight() / 10f) * 3, box);

		// Text

		// Text colour
		Paint text = new Paint(Paint.ANTI_ALIAS_FLAG);
		text.setColor(getResources().getColor(R.color.game_text));
		text.setStyle(Style.FILL);
		text.setTextSize(getHeight() / 10f * 0.8f);
		text.setTextScaleX((getWidth() / 10f) / (getHeight() / 10f));
		text.setTextAlign(Paint.Align.CENTER);

		// Text
		FontMetrics fm = text.getFontMetrics();
		// X Centre
		float x = (getWidth() / 10f) / 2;
		// Y Centre
		float y = (getHeight() / 10f) / 2 - (fm.ascent + fm.descent) / 2;
		// TODO: Text code
		canvas.drawText(this.game.getGameQuestion() + "=",
				(getWidth() / 10f) * 5, ((getHeight() / 10f) * 1) + y, text);
		canvas.drawText(this.game.getUserAnswer(),
				(getWidth() / 10f) * 5, ((getHeight() / 10f) * 2) + y, text);
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(motionEvent);

		game.showKeypad();
		Log.d(TAG, "On Touch Event");
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		Log.d(TAG, "On Key Down Key Code: " + keyCode);
		Log.d(TAG, "On Key Down Key Event: " + keyEvent);
		switch (keyCode) {
		case KeyEvent.KEYCODE_0:
			setNextNumber(0);
			break;
		case KeyEvent.KEYCODE_1:
			setNextNumber(1);
			break;
		case KeyEvent.KEYCODE_2:
			setNextNumber(2);
			break;
		case KeyEvent.KEYCODE_3:
			setNextNumber(3);
			break;
		case KeyEvent.KEYCODE_4:
			setNextNumber(4);
			break;
		case KeyEvent.KEYCODE_5:
			setNextNumber(5);
			break;
		case KeyEvent.KEYCODE_6:
			setNextNumber(6);
			break;
		case KeyEvent.KEYCODE_7:
			setNextNumber(7);
			break;
		case KeyEvent.KEYCODE_8:
			setNextNumber(8);
			break;
		case KeyEvent.KEYCODE_9:
			setNextNumber(9);
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			game.showKeypad();
			break;
		default:
			return super.onKeyDown(keyCode, keyEvent);
		}
		return true;
	}

	public void setNextNumber(int number) {
		// GameActivity.setNumber(number);
		invalidate();
	}

}
