package com.kristianmatthews.braintraining;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "Brain Training";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set on click listeners for main buttons
		View continueButton = findViewById(R.id.continue_button);
		continueButton.setOnClickListener(this);
		View newButton = findViewById(R.id.new_button);
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	// #
	public void onClick(View view) {
		switch (view.getId()) {
		// #
		case R.id.new_button:
			openNewGameDialog();
			break;
		// #
		case R.id.about_button:
			Intent i = new Intent(this, AboutActivity.class);
			startActivity(i);
			break;
		// #
		case R.id.exit_button:
			finish();
			break;
		}
	}

	// Difficulty prompt
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.difficulty_title)
				.setItems(R.array.difficulty,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startGame(i);
							}
						}).show();
	}

	// Start game with selected difficulty
	private void startGame(int difficulty) {
		Log.d(TAG, "Difficulty: " + difficulty);
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(GameActivity.KEY_DIFFICULTY, difficulty);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, Preferences.class));
			return true;
			// #
		}
		return false;
	}

}
