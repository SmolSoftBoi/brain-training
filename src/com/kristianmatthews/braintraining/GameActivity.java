package com.kristianmatthews.braintraining;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import java.util.Random;

public class GameActivity extends Activity {

	private static final String TAG = "Brain Training";

	public static final String KEY_DIFFICULTY = "com.kristianmatthews.braintraining.difficulty";
	public static final int DIFFICULTY_NOVICE = 0;
	public static final int DIFFICULTY_EASY = 1;
	public static final int DIFFICULTY_MEDIUM = 2;
	public static final int DIFFICULTY_GURU = 3;

	// Game variables
	private String gameQuestion;
	private int gameAnswer;
	private String userAnswer;

	private GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "On Create");

		int difficulty = getIntent().getIntExtra(KEY_DIFFICULTY,
				DIFFICULTY_NOVICE);
		setGameQuestionAnswer(difficulty);
		gameView = new GameView(this);
		setContentView(gameView);
		gameView.requestFocus();
	}

	// Generate game using difficulty
	private void setGameQuestionAnswer(int difficulty) {
		gameQuestion = "";
		userAnswer = "";

		int terms;
		int termNumbers[];
		String termOperators[];

		// TODO: Continue last game
		switch (difficulty) {
		case DIFFICULTY_GURU:
			// TODO: Guru game
			terms = randomNumber(4, 6);
			termNumbers = new int[terms];
			termOperators = new String[terms - 1];
			break;
		case DIFFICULTY_MEDIUM:
			// TODO: Medium game
			terms = randomNumber(2, 4);
			termNumbers = new int[terms];
			termOperators = new String[terms - 1];
			break;
		case DIFFICULTY_EASY:
			// TODO: Easy game
			terms = randomNumber(2, 3);
			termNumbers = new int[terms];
			termOperators = new String[terms - 1];
			break;
		case DIFFICULTY_NOVICE:
		default:
			// TODO: Novice game
			terms = 2;
			termNumbers = new int[terms];
			termOperators = new String[terms - 1];
			break;
		}

		for (int i = 0; i < termNumbers.length; i++) {
			termNumbers[i] = randomNumber(1, 9);
			gameQuestion = gameQuestion + termNumbers[i];
			if (i < termOperators.length) {
				termOperators[i] = randomOperator();
				gameQuestion = gameQuestion + termOperators[i];
			}
		}
		Log.d(TAG, "Game Question: " + gameQuestion);

		for (int i = 0; i < termOperators.length; i++) {
			if (termOperators[i].equals("/")) {
				gameAnswer = termNumbers[i] / termNumbers[i + 1];
				termNumbers[i] = gameAnswer;
				termNumbers[i + 1] = gameAnswer;
				termOperators[i] = "";
			}
		}
		for (int i = 0; i < termOperators.length; i++) {
			if (termOperators[i].equals("*")) {
				gameAnswer = termNumbers[i] * termNumbers[i + 1];
				termNumbers[i] = gameAnswer;
				termNumbers[i + 1] = gameAnswer;
				termOperators[i] = "";
			}
		}
		for (int i = 0; i < termOperators.length; i++) {
			if (termOperators[i].equals("+")) {
				gameAnswer = termNumbers[i] + termNumbers[i + 1];
				termNumbers[i] = gameAnswer;
				termNumbers[i + 1] = gameAnswer;
				termOperators[i] = "";
			}
		}
		for (int i = 0; i < termOperators.length; i++) {
			if (termOperators[i].equals("-")) {
				gameAnswer = termNumbers[i] - termNumbers[i + 1];
				termNumbers[i] = gameAnswer;
				termNumbers[i + 1] = gameAnswer;
				termOperators[i] = "";
			}
		}
		Log.d(TAG, "Game Answer: " + gameAnswer);
		
		for (int i = 0; i < String.valueOf(gameAnswer).length(); i++) {
			userAnswer = userAnswer + "?";
		}
	}

	private int randomNumber(int minimum, int maximum) {
		Random random = new Random();
		return random.nextInt(maximum - minimum + 1) + minimum;
	}

	private String randomOperator() {
		switch (randomNumber(1, 4)) {
		case 1:
			return "+";
		case 2:
			return "-";
		case 3:
			return "*";
		case 4:
			return "/";
		default:
			return "";
		}
	}

	// Return game question
	protected String getGameQuestion() {
		return gameQuestion;
	}

	// Return user answer
	protected String getUserAnswer() {
		return userAnswer;
	}

	// Set number
	protected boolean setNumber(int value) {
		setNumber(value);
		return true;
	}

	// Open keypad
	protected void showKeypad() {
		Log.d(TAG, "Show Keypad");
		Dialog v = new KeypadActivity(this, gameView);
		v.show();
	}

}
