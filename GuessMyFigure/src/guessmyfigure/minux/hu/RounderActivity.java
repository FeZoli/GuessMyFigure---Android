package guessmyfigure.minux.hu;

import java.util.Random;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RounderActivity extends MainActivity {

	private int myGuess;
	private TextView topTextView;
	private TextView workTextView;
	private Button sendTipButton;
	private Button requestNewTipButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rounder);
		startRounder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.rounder_activity_actions, menu);
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
	
	private void startRounder() {

		Random randomGenerator = new Random();
		
		myGuess = 0;
		while (myGuess == 0 || myGuess % 10 == 0) {
			myGuess = randomGenerator.nextInt(100);
		}
		
		String msgTop = String.format(res.getString(R.string.msg_rounder_top),
				"10-re");
		topTextView = (TextView) findViewById(R.id.RounderTopTextView);
		topTextView.setText(msgTop);
		
		String msgWork = Integer.toString(myGuess);
		workTextView = (TextView) findViewById(R.id.RounderWorkMessageTextView);
		workTextView.setText(msgWork);
	}
	
	public void sendTip(View view) {
		
		EditText tipText = (EditText) findViewById(R.id.RounderTipTextField);
		sendTipButton = (Button) findViewById(R.id.RounderSendTipButton);
		requestNewTipButton = (Button) findViewById(R.id.RounderNewTipButton);
	
		if (tipText.getText().length() == 0) {
			return;
		}

		int userGuess = Integer.parseInt(tipText.getText().toString());

		if (userGuess != ((myGuess+5) / 10) * 10) {
			workTextView.setText(myGuess 
			+ System.getProperty("line.separator")
			+ userGuess + " " + res.getString(R.string.msg_rounder_tip_failed));
		} else {
			sendTipButton.setVisibility(View.INVISIBLE);
			requestNewTipButton.setVisibility(View.VISIBLE);
			workTextView.setText(userGuess + " " + res.getString(R.string.msg_rounder_tip_success));
		}
		
		tipText.setText("");
	}
	
	public void requestNewTip(View view) {
		workTextView.setText("");
		sendTipButton.setVisibility(View.VISIBLE);
		requestNewTipButton.setVisibility(View.INVISIBLE);		
		startRounder();
	}

	private void doSettings() {
	//	Intent intent = new Intent(this, RounderPreferences.class);
	//	startActivity(intent);
	}

}
