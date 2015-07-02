package com.a21170949.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity implements OnClickListener {
   private static final String TAG = "Sudoku";
   
   /** chama a actividade quando e iniciada */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      // listeners para os botoes do menu
      View continueButton = findViewById(R.id.continue_button);
      continueButton.setOnClickListener(this);
      View newButton = findViewById(R.id.new_button);
      newButton.setOnClickListener(this);
      View aboutButton = findViewById(R.id.about_button);
      aboutButton.setOnClickListener(this);
      View exitButton = findViewById(R.id.exit_button);
      exitButton.setOnClickListener(this);
   }

   @Override
   protected void onResume() {
      super.onResume();
      Music.play(this, R.raw.main);
   }

   @Override
   protected void onPause() {
      super.onPause();
      Music.stop(this);
   }

   public void onClick(View v) {
      switch (v.getId()) {
      case R.id.continue_button:
         startGame(Game.DIFFICULTY_CONTINUE);
         break;
      case R.id.about_button:
         Intent i = new Intent(this, about.class);
         startActivity(i);
         break;
      case R.id.new_button:
         openNewGameDialog();
         break;
      case R.id.exit_button:
         finish();
         break;
      }
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.settings:
         startActivity(new Intent(this, Prefs.class));
         return true;
      }
      return false;
   }

   /** Pergunta a dificuldade do jogo **/
   private void openNewGameDialog() {
      new AlertDialog.Builder(this)
           .setTitle(R.string.new_game_title)
           .setItems(R.array.difficulty,
            new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialoginterface,
                     int i) {
                  startGame(i);
               }
            })
           .show();
   }

   /** come�a o jogo com a dificuldade escolhida **/
   private void startGame(int i) {
      Log.d(TAG, "clicked on " + i);
      Intent intent = new Intent(this, Game.class);
      intent.putExtra(Game.KEY_DIFFICULTY, i);
      startActivity(intent);
   }
}