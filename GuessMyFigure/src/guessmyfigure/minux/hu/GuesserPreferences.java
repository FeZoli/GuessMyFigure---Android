package guessmyfigure.minux.hu;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class GuesserPreferences extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	private Resources res;
	
	public static final String KEY_PREF_MIN_VAL = "pref_guesser_min_value";
	public static final String KEY_PREF_MAX_VAL = "pref_guesser_max_value";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		res = getResources();
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.guesser_preferences);
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
		
		String minPref = sharedPref.getString(KEY_PREF_MIN_VAL, "0");
		String maxPref = sharedPref.getString(KEY_PREF_MAX_VAL, "100");
		
		int min = Integer.parseInt(minPref);
		int max = Integer.parseInt(maxPref);

		if (min < max) return;

    	Preference connectionPref = findPreference(key);
        if (key.equals(KEY_PREF_MIN_VAL)) {
            connectionPref.setSummary(res.getString(R.string.guesser_pref_min_error));
        }
        else if (key.equals(KEY_PREF_MAX_VAL)) {
            connectionPref.setSummary(res.getString(R.string.guesser_pref_max_error));
        }
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
}
