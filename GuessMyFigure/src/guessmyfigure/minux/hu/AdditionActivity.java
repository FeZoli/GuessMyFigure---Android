package guessmyfigure.minux.hu;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class AdditionActivity extends MainActivity {

	private int number1;
	private int number2;
	private int myGuess;
	private TextView topTextView;
	private TextView workTextView;
	private Button sendTipButton;
	private Button requestNewTipButton;
	
	private final static String ADDITION_SYMBOL = "+";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addition);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addition_activity_actions, menu);
		startAddition();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void sendTip(View view) {
		EditText tipText = (EditText) findViewById(R.id.AdditionTipTextField);
		sendTipButton = (Button) findViewById(R.id.AdditionSendTipButton);
		requestNewTipButton = (Button) findViewById(R.id.AdditionNewTipButton);

		if (tipText.getText().length() == 0) {
			return;
		}

		int userGuess = Integer.parseInt(tipText.getText().toString());

		if (userGuess != myGuess) {
			workTextView.setText(number1 + " " + ADDITION_SYMBOL + " " + number2
					+ System.getProperty("line.separator") + userGuess + " "
					+ res.getString(R.string.msg_tip_failed));
		} else {
			sendTipButton.setVisibility(View.INVISIBLE);
			requestNewTipButton.setVisibility(View.VISIBLE);
			workTextView.setText(number1 + " " + ADDITION_SYMBOL + " "
					+ number2 + " = " + userGuess
					+ " " + res.getString(R.string.msg_tip_success));
		}
		tipText.setText("");
	}

	public void requestNewTip(View view) {
		workTextView.setText("");
		sendTipButton.setVisibility(View.VISIBLE);
		requestNewTipButton.setVisibility(View.INVISIBLE);
		startAddition();
	}

	private void startAddition() {

		Random randomGenerator = new Random();

		number1 = 0;
		while (number1 == 0) {
			number1 = randomGenerator.nextInt(10);
		}

		number2 = 0;
		while (number2 == 0) {
			number2 = randomGenerator.nextInt(10);
		}

		myGuess = number1 + number2;

		String msgTop = res.getString(R.string.msg_how_much_is_result);

		topTextView = (TextView) findViewById(R.id.AdditionTopTextView);
		topTextView.setText(msgTop);

		String msgWork = Integer.toString(number1) + " " + ADDITION_SYMBOL + " "
				+ Integer.toString(number2) + " = ";
		workTextView = (TextView) findViewById(R.id.AdditionWorkMessageTextView);
		workTextView.setText(msgWork);
	}
}
