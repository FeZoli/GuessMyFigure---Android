package guessmyfigure.minux.hu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class GuesserPreferences extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}

}
