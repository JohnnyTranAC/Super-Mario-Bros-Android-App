package com.example.supermariobros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;

        button = findViewById(R.id.play);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameMode();
            }
        });
    }
    /*starts the game when the play button in the main menu is clicked*/
    public void openGameMode(){
        Intent intent = new Intent(this, Game_Activity.class);
        startActivity(intent);
    }
}/*EOF*/
