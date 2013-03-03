package com.kristianmatthews.braintraining;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class KeypadActivity extends Dialog {

	protected static final String TAG = "Brain Training";

	private final View keys[] = new View[13];
	private View keypad;
	private final GameView gameView;

	public KeypadActivity(Context context, GameView gameView) {
		super(context);
		this.gameView = gameView;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.keypad_title);
		// TODO: Fix set content view
		setContentView(R.layout.activity_keypad);
		findViews();
		setListeners();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		int number = 0;
		switch (keyCode) {
		case KeyEvent.KEYCODE_0:
			number = 0;
			break;
		case KeyEvent.KEYCODE_1:
			number = 1;
			break;
		case KeyEvent.KEYCODE_2:
			number = 2;
			break;
		case KeyEvent.KEYCODE_3:
			number = 3;
			break;
		case KeyEvent.KEYCODE_4:
			number = 4;
			break;
		case KeyEvent.KEYCODE_5:
			number = 5;
			break;
		case KeyEvent.KEYCODE_6:
			number = 6;
			break;
		case KeyEvent.KEYCODE_7:
			number = 7;
			break;
		case KeyEvent.KEYCODE_8:
			number = 8;
			break;
		case KeyEvent.KEYCODE_9:
			number = 9;
			break;
		case KeyEvent.KEYCODE_DEL:
			number = 10;
			break;
		case KeyEvent.KEYCODE_MINUS:
			number = 11;
			break;
		case KeyEvent.KEYCODE_POUND:
			number = 12;
			break;
		default:
			return super.onKeyDown(keyCode, keyEvent);
		}
		returnResult(number);
		return true;
	}

	// Return result
	private void returnResult(int number) {
		gameView.setNextNumber(number);
		dismiss();
	}

	private void findViews() {
		keypad = findViewById(R.id.keypad);
		keys[0] = findViewById(R.id.keypad_0);
		keys[1] = findViewById(R.id.keypad_1);
		keys[2] = findViewById(R.id.keypad_2);
		keys[3] = findViewById(R.id.keypad_3);
		keys[4] = findViewById(R.id.keypad_4);
		keys[5] = findViewById(R.id.keypad_5);
		keys[6] = findViewById(R.id.keypad_6);
		keys[7] = findViewById(R.id.keypad_7);
		keys[8] = findViewById(R.id.keypad_8);
		keys[9] = findViewById(R.id.keypad_9);
		keys[10] = findViewById(R.id.keypad_delete);
		keys[11] = findViewById(R.id.keypad_negative);
		keys[12] = findViewById(R.id.keypad_hash);
		
	}

	private void setListeners() {
		for (int i = 0; i < keys.length; i++) {
			final int t = i;
			keys[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					returnResult(t);
				}
			});
		}
		keypad.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				returnResult(-1);
			}
		});
	}

}
