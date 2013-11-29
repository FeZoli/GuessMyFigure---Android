package guessmyfigure.minux.hu;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
		case R.id.action_addition:
			startAddition();
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

	private void startAddition() {
		Intent intent = new Intent(this, AdditionActivity.class);
		startActivity(intent);
	}	
	
}
