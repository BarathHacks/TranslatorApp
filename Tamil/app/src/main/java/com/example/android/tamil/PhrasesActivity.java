package com.example.android.tamil;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "நீ எங்கே போகிறாய்?",
                R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "உங்கள் பெயர் என்ன?",
                R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "என் பெயர்...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "நீ எப்படி இருக்கிறாய்?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "நான் நன்றாக இருகிறேன்", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "நீ வருகிறாயா?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "ஆம், நான் வருகிறேன்", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "நான் வருகிறேன்.", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "வா, போகலாம்.", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "இங்கே வா.", R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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
                Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());

                // Start the audio file
                mMediaPlayer.start();
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