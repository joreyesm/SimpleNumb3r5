package net.kerul.simplenumb3r5;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Locale;

import org.w3c.dom.Text;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class Evaluacion extends Activity implements OnClickListener {
	// initialize all widgets
	private ImageView imageNumber;
	private ImageButton btnfirst, btnsound, btnnext;
	private EditText word;
	private TextView gradelbl, failedlbl;
	private double grade = 0;
	private int failed = 0;
	// Sound arrays
	private String[] spellingSoundFile = {"1-a.mp3", "2-we.mp3", "3-my.mp3", "4-is.mp3", "5-to.mp3", "6-go.mp3", "7-he.mp3", "8-do.mp3", "9-you.mp3", "10-are.mp3", "11-fan.mp3", "12-net.mp3", "13-cat.mp3", "14-red.mp3", "15-map.mp3", "16-nap.mp3", "17-sad.mp3", "18-see.mp3", "19-fun.mp3", "20-and.mp3", "21-ear.mp3", "22-sun.mp3", "23-egg.mp3", "24-like.mp3", "25-rose.mp3", "26-nest.mp3", "27-fire.mp3", "28-four.mp3", "29-move.mp3", "30-rain.mp3", "31-camel.mp3", "32-insect.mp3", "33-tools.mp3", "34-plant.mp3", "35-friend.mp3"};
	// Images arrays
	private String[] spellingImages = {"a", "we","my","is","to","go","he","do_8","you", "are", "fan", "net", "cat", "red", "map", "nap", "sad", "see", "fun", "and", "ear", "sun", "egg", "like", "rose", "nest", "fire", "four", "move", "rain", "camel", "insect", "tools", "plant", "friend"};
			 
	
	// Define variables to track screen number, start from 0
	private int screenNumber = 0;
	
	// Define sound controller
	private MediaPlayer mp;
	
	//Define an array for the sound files
	private String[] soundfile = spellingSoundFile;
	
	// Define an array for the image files
	private String[] images = spellingImages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluacion);
		imageNumber = (ImageView)findViewById(R.id.imagenumber);
		
		gradelbl = (TextView)findViewById(R.id.gradelbl);
		gradelbl.setText("Grade: " + grade);
		failedlbl = (TextView)findViewById(R.id.failedlbl);
		failedlbl.setText("Failed: " + failed);
		word = (EditText)findViewById(R.id.word);
		
		// Create the object for the button
		btnfirst = (ImageButton)findViewById(R.id.btnfirst);
		//btnfirst.setEnabled(false);
		btnfirst.setOnClickListener(this); // Add listener to the button

		btnsound = (ImageButton)findViewById(R.id.btnsound);
		btnsound.setOnClickListener(this); // Add listener to the button

		btnnext = (ImageButton)findViewById(R.id.btnnext);
		btnnext.setOnClickListener(this); // Add listener to the button

		changeNumber(screenNumber);
		enableNavigation();		
	}
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.btnprevious) {
			screenNumber--;
			changeNumber(screenNumber);
			// Call the method PlaySound()
			playSound(soundfile[screenNumber].toString());
			enableNavigation();
		} else {
			if(arg0.getId() == R.id.btnnext) {
				if (!word.getText().toString().trim().equals(images[screenNumber])) {
					failed++;
					failedlbl.setText("Failed: " + failed);
				}
				screenNumber++;
				if (screenNumber == (images.length - 1) && (failed != images.length)) {
					grade = (images.length - failed) / images.length;
					gradelbl.setText("Grade: " + grade);
				}
				changeNumber(screenNumber);
				word.setText("");
				// Call the method PlaySound()
				playSound(soundfile[screenNumber].toString());
				enableNavigation();
			} else {
				if(arg0.getId() == R.id.btnsound) {
					// Call the method PlaySound()
					playSound(soundfile[screenNumber].toString());
				} else {
					if(arg0.getId() == R.id.btnfirst) {
						screenNumber = 0;
						changeNumber(screenNumber);
						// Call the method PlaySound()
						playSound(soundfile[screenNumber].toString());
						enableNavigation();
					}  else {
						if(arg0.getId() == R.id.btnlast) {
							screenNumber = images.length - 1;
							changeNumber(screenNumber);
							// Call the method PlaySound()
							playSound(soundfile[screenNumber].toString());
							enableNavigation();
						}
					}
				}
			}
			
		} // 1st if
	}
	private void playSound(String soundName) {
		if(mp != null && mp.isPlaying()) { // If the MediaPLayer is playing a sound, stop it to play new voice
			mp.stop();
			mp.release();
		} 
		try {
			mp = new MediaPlayer();
			AssetFileDescriptor afd = getAssets().openFd(soundName);
			// Set the sound source file
			mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());

			mp.prepare(); // prepare for playback
			mp.start(); // play the sound
		} catch (IOException e) {
			Log.i("Error playing sound:", e.toString());
		}
	} // playSound

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.evaluacion, menu);
		return true;
	}
	
	private void enableNavigation() {
		if (screenNumber == 0) {
			btnnext.setEnabled(true);
			btnfirst.setEnabled(false);
		} else if(screenNumber == (images.length - 1)) {
			btnnext.setEnabled(false);
			btnfirst.setEnabled(true);
			
		} else {
			btnnext.setEnabled(true);
			btnfirst.setEnabled(true);
		}
	} // enableNavigation()

	private void changeNumber(int screen) {
		int id = getResources().getIdentifier(images[screen], "drawable", getPackageName());
		imageNumber.setImageResource(id);
	} // changeNumber()
	
} // end activity
