package net.kerul.simplenumb3r5;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class Info extends Activity {
	private WebView webinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		webinfo = (WebView) findViewById(R.id.webinfo);
		// Provide the url path pointing to info.html
		webinfo.loadUrl("file:///android_asset/info.html");
		//webinfo.loadUrl("http://www.bcr.gob.sv");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

}
