package com.utkarsh.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0=spider and 1=captain
    int active=0;
    //2=unplayed
    boolean gameIsActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPostitions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    public void dropIn(View view){
        ImageView counter=(ImageView)view;
        System.out.println(counter.getTag().toString());
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive) {
            gameState[tappedCounter]=active;
            counter.setTranslationY(-1000f);
            if (active == 0) {
                counter.setImageResource(R.drawable.spider);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.captain);
                active = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(400);
        }
        for(int[] win:winningPostitions){
            if(gameState[win[0]]==gameState[win[1]] &&
                    gameState[win[1]]==gameState[win[2]] &&
                    gameState[win[0]] != 2){
                gameIsActive=false;
                String winner="";
                if(gameState[win[0]]==0){
                    winner="Spiderman";
                }else{
                    winner="Captain America";
                }
                TextView winnerMessage=(TextView)findViewById(R.id.textView1);
                winnerMessage.setText(winner+" has won!!!");
                LinearLayout linLayout=(LinearLayout)findViewById(R.id.mylinearLayout);
                linLayout.setVisibility(View.VISIBLE);
                break;//bcoz after satisfying this condition at the end when someone wins it does not goes to
                //else condition and comes out of the for loop
                }
            else{
                boolean gameIsOver=true;
                for(int i:gameState){
                    if(i==2){
                        gameIsOver=false;
                    }
                }
                if(gameIsOver){
                    TextView winnerMessage=(TextView)findViewById(R.id.textView1);
                    winnerMessage.setText("Its a draw!!!");
                    LinearLayout linLayout=(LinearLayout)findViewById(R.id.mylinearLayout);
                    linLayout.setVisibility(View.VISIBLE);

                }

            }

            }
        }
    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout linLayout=(LinearLayout)findViewById(R.id.mylinearLayout);
        linLayout.setVisibility(View.INVISIBLE);
        active=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout griLayout=(GridLayout)findViewById(R.id.mygridLayout);
        for(int i=0;i<griLayout.getChildCount();i++){
            ((ImageView) griLayout.getChildAt(i)).setImageResource(0);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
