package cs518.sample.implicitintents;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * Sample code for Implicit Intents
 * Implicit means the OS uses the URI to determine the 
 * activity that is launched. 
 * @author Tricia
 *
 */
public class MainActivity extends Activity {
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.intents, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	public void intentSelected(View view) {
		int position = spinner.getSelectedItemPosition();
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.smbc-comics.com/"));
			break;
		case 1:
			// number entered and dialer started
			intent = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:(514)123-4567"));
			break;
		case 2:
			// number entered, waits for user to send
			intent = new Intent(Intent.ACTION_DIAL,
					Uri.parse("tel:(514)123-4567"));
			break;
		case 3:
			// won't work on avd without installing some software
			// test on real device
			// geo:latitude, longitude, altitude;u=uncertainty
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:45.50,73.5667?z=10"));
			break;
		case 4:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=Paris"));
			break;
		case 5:
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			break;
		case 6:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("content://contacts/people/"));
			break;
		case 7:
			intent = new Intent(Intent.ACTION_EDIT,
					Uri.parse("content://contacts/people/1"));
			break;

		}
		if (intent != null) {
			try {
				startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(this, "No Activity For this intent",
						Toast.LENGTH_LONG).show();
			}

		}
	}
}
