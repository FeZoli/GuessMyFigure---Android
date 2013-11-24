package guessmyfigure.minux.hu;

import java.util.Random;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	protected Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		res = getResources();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_quit:
			this.finish();
			return true;
		case R.id.action_guesser:
			startGuesser();
			return true;
		case R.id.action_rounding:
			startRounding();
			return true;
		case R.id.action_multiplication:
			startMultiplication();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startGuesser() {
		Intent intent = new Intent(this, GuesserActivity.class);
		startActivity(intent);
	}

	private void startRounding() {
		Intent intent = new Intent(this, RounderActivity.class);
		startActivity(intent);
	}
	
	private void startMultiplication() {
		Intent intent = new Intent(this, MultiplicationActivity.class);
		startActivity(intent);
	}	
}
