package guessmyfigure.minux.hu;

import java.util.Random;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuesserActivity extends MainActivity {

	private int myGuess;
	private Button tipButton;
	private TextView msgGuesserTop;
	
	private static int minValue;
	private static int maxValue;
	
	private SharedPreferences sharedPref;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		createFigureToBeGuessed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.guesser_activity_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_guesser_settings:
			this.doSettings();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void sendTip(View view) {

		EditText tipText = (EditText) findViewById(R.id.edit_message);
		TextView msgView = (TextView) findViewById(R.id.view_msg);
		tipButton = (Button) findViewById(R.id.TipButton);

		if (tipText.getText().length() == 0) {
			return;
		}

		int userGuess = Integer.parseInt(tipText.getText().toString());

		if (userGuess < myGuess) {
			msgView.setText(userGuess + " " + res.getString(R.string.msg_less_than));
		} else if (userGuess > myGuess) {
			msgView.setText(userGuess + " " + res.getString(R.string.msg_more_than));
		} else {
			tipButton.setEnabled(false);
			msgView.setText(userGuess + " " + res.getString(R.string.msg_found));
		}

		tipText.setText("");
	}

	public void newGuess(View view) {
		createFigureToBeGuessed();
		TextView msgView = (TextView) findViewById(R.id.view_msg);
		msgView.setText(res.getString(R.string.msg_found_a_new));
		tipButton.setEnabled(true);
	}

	private void createFigureToBeGuessed() {
		
		int diff = 100;
		
		minValue = Integer.parseInt(sharedPref.getString(
				GuesserPreferences.KEY_PREF_MIN_VAL, "0"));
		maxValue = Integer.parseInt(sharedPref.getString(
				GuesserPreferences.KEY_PREF_MAX_VAL, "100"));

		// HACK !!!
		if (minValue > maxValue) {
			minValue = 0;
			maxValue = 100;
		}
		
		diff = maxValue - minValue;
		if (diff < 0) diff = 100;
		// HACK END
		
		Random randomGenerator = new Random();
		myGuess = minValue + randomGenerator.nextInt(diff);

		String m = minValue + "-" + maxValue;
		
		String msgTop = String.format(res.getString(R.string.msg_guesser_top),
				m);
		msgGuesserTop = (TextView) findViewById(R.id.msg_guesser_top);
		msgGuesserTop.setText(msgTop);
	}

	private void doSettings() {
		
		Intent intent = new Intent(this, GuesserPreferences.class);
		startActivity(intent);
	}
}
