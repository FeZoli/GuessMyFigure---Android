package guessmyfigure.minux.hu;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		res = getResources();
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
		default:
			return super.onOptionsItemSelected(item);
		}
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

		Random randomGenerator = new Random();
		myGuess = randomGenerator.nextInt(100);

		String msgTop = String.format(res.getString(R.string.msg_guesser_top),
				"0-100");
		msgGuesserTop = (TextView) findViewById(R.id.msg_guesser_top);
		msgGuesserTop.setText(msgTop);
	}

	private void doSettings() {
		Intent intent = new Intent(this, GuesserPreferences.class);
		startActivity(intent);
	}
}
