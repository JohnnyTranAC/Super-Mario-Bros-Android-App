package com.example.supermariobros;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supermariobros.View.CustomView;

public class Game_Activity extends AppCompatActivity implements View.OnTouchListener{
    private CustomView GameView;
    private int x=0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        /*View declarations*/
        GameView = findViewById(R.id.customView);

        /*button declarations*/
        ImageButton right_button = findViewById(R.id.right_button);
        right_button.setOnTouchListener(this);
        ImageButton left_button = findViewById(R.id.left_button);
        left_button.setOnTouchListener(this);
        ImageButton jump_button = findViewById(R.id.jump_button);
        jump_button.setOnTouchListener(this);
        ImageButton jump2_button = findViewById(R.id.jump2_button);
        jump2_button.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.right_button:
                GameView.direction_facing(true);
                GameView.check_for_enemies();
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    x = 1;
                    handler.post(runnable);
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(runnable);
                    return true;
                }
                break;

            case R.id.left_button:
                GameView.direction_facing(false);
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    x = -1;
                    handler.post(runnable);
                    return true;
                }

                if(event.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(runnable);
                    return true;
                }
                break;

            case R.id.jump_button: case R.id.jump2_button:
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    GameView.jumping();
                    return true;
                }
                break;

            default:
                break;
        }
        return false;
    }
    /*as long as a button is held down it will move mario that direction*/
    private Runnable runnable = new Runnable() {
        private int time;
        @Override
        public void run() {
            //do the move here
            //call runnable again
            GameView.move(x,0,false, GameView.Pieces[0]);
            GameView.moving(false);
            TextChange(); //updates the score whenever mario moves
            if(GameView.big){
                time = 100;
            }else{ time = 175;}
            handler.postDelayed(this, time);
        }
    };
    /*Changing the text scores on the screen*/
    public void TextChange(){
        //score text
        TextView score_value = findViewById(R.id.score_text);
        String string_score = Long.toString(GameView.Score);
        score_value.setText(getResources().getString(R.string.Score_Status, string_score));

        //lives text
        TextView lives_value = findViewById(R.id.lives_text);
        String string_lives = Long.toString(GameView.lives);
        lives_value.setText(getResources().getString(R.string.Lives_Status, string_lives));
    }
}/*EOF*/