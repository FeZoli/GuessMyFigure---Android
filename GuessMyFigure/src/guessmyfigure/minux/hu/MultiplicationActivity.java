package guessmyfigure.minux.hu;

import java.util.Random;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MultiplicationActivity extends MainActivity {
	
	private int number1;
	private int number2;
	private int myGuess;
	private TextView topTextView;
	private TextView workTextView;
	private Button sendTipButton;
	private Button requestNewTipButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplication);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multiplication_activity_actions, menu);
		startMultiplication();
		return true;
	}
	
	public void sendTip(View view)
	{
		EditText tipText = (EditText) findViewById(R.id.MultiplicationTipTextField);
		sendTipButton = (Button) findViewById(R.id.MultiplicationSendTipButton);
		requestNewTipButton = (Button) findViewById(R.id.MultiplicationNewTipButton);
	
		if (tipText.getText().length() == 0) {
			return;
		}

		int userGuess = Integer.parseInt(tipText.getText().toString());

		if (userGuess != myGuess) {
			workTextView.setText(number1 + " × " + number2 
			+ System.getProperty("line.separator")
			+ userGuess + " " + res.getString(R.string.msg_rounder_tip_failed));
		} else {
			sendTipButton.setVisibility(View.INVISIBLE);
			requestNewTipButton.setVisibility(View.VISIBLE);
			workTextView.setText(
					number1 + " × " + number2 + " = " + userGuess 
					+ " " + res.getString(R.string.msg_rounder_tip_success));
		}
		
		tipText.setText("");
		
	}
	
	public void requestNewTip(View view) {
		workTextView.setText("");
		sendTipButton.setVisibility(View.VISIBLE);
		requestNewTipButton.setVisibility(View.INVISIBLE);		
		startMultiplication();
	}
	
	
	private void startMultiplication() {

		Random randomGenerator = new Random();
		
		number1 = 0;
		while (number1 == 0) {
			number1 = randomGenerator.nextInt(10);
		}
		
		number2 = 0;
		while (number2 == 0) {
			number2 = randomGenerator.nextInt(10);
		}
		
		myGuess = number1 * number2;

		String msgTop = res.getString(R.string.msg_multiplication_top);

		topTextView = (TextView) findViewById(R.id.MultiplicationTopTextView);
		topTextView.setText(msgTop);
		
		String msgWork = Integer.toString(number1) + " × " + Integer.toString(number2) + " = ";
		workTextView = (TextView) findViewById(R.id.MultiplicationWorkMessageTextView);
		workTextView.setText(msgWork);
	}
	

}
