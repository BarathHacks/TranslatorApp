package com.example.android.tamil;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create a list of words
        final ArrayList<com.example.android.tamil.Word> words = new ArrayList<com.example.android.tamil.Word>();
        words.add(new Word("red", "சிவப்பு", R.drawable.color_red, R.raw.colors_red));
        words.add(new Word("mustard yellow", "மஞ்சள்", R.drawable.color_mustard_yellow,
                R.raw.colors_yellow));
        words.add(new Word("dusty yellow", "மணல் மஞ்சள்", R.drawable.color_dusty_yellow,
                R.raw.colors_dusty_yellow));
        words.add(new Word("green", "பச்சை", R.drawable.color_green, R.raw.colors_green));
        words.add(new Word("brown", "பழுப்பு", R.drawable.color_brown, R.raw.colors_brown));
        words.add(new Word("gray", "சாம்பல்", R.drawable.color_gray, R.raw.colors_grey));
        words.add(new Word("black", "கருப்பு", R.drawable.color_black, R.raw.colors_balck));
        words.add(new Word("white", "வெள்ளை", R.drawable.color_white, R.raw.colors_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();
                // Get the {@link Word} object at the given position the user clicked on
                com.example.android.tamil.Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());

                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}