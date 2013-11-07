package guessmyfigure.minux.hu;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuesserActivity extends MainActivity {

	private int myGuess;
	private Button tipButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		createFigureToBeGuessed();
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
			msgView.setText(userGuess + " kisebb, mint amire gondoltam.");
		} else if (userGuess > myGuess) {
			msgView.setText(userGuess + " nagyobb, mint amire gondoltam.");
		} else {
			tipButton.setEnabled(false);
			msgView.setText(userGuess + ". Igen. KITALÁLTAD!!! Ügyes vagy!");
		}

		tipText.setText("", TextView.BufferType.NORMAL);
	}

	public void newGuess(View view) {
		createFigureToBeGuessed();
		TextView msgView = (TextView) findViewById(R.id.view_msg);
		msgView.setText("Megvan, gondoltam egy újat. Próbálkozz!");
		tipButton.setEnabled(true);
	}

	private void createFigureToBeGuessed() {

		Random randomGenerator = new Random();
		myGuess = randomGenerator.nextInt(100);
	}

}
