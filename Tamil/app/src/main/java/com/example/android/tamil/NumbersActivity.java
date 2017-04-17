package com.example.android.tamil;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        words.add(new Word("one", "ஒன்று", R.drawable.number_one, R.raw.numbers_one));
        words.add(new Word("two", "இரண்டு", R.drawable.number_two, R.raw.numbers_two));
        words.add(new Word("three", "மூன்று", R.drawable.number_three, R.raw.numbers_three));
        words.add(new Word("four", "நான்கு", R.drawable.number_four, R.raw.numbers_four));
        words.add(new Word("five", "ஐந்து", R.drawable.number_five, R.raw.numbers_five));
        words.add(new Word("six", "ஆறு", R.drawable.number_six, R.raw.numbers_six));
        words.add(new Word("seven", "ஏழு", R.drawable.number_seven, R.raw.numbers_seven));
        words.add(new Word("eight", "எட்டு", R.drawable.number_eight, R.raw.numbers_eight));
        words.add(new Word("nine", "ஒன்பது", R.drawable.number_nine, R.raw.numbers_nine));
        words.add(new Word("ten", "பத்து", R.drawable.number_ten, R.raw.numbers_ten));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        listView.setAdapter(adapter);
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