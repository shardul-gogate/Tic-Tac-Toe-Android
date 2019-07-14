package com.shardulgogate.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int currTurn;
    static int playerWin;

    static int[][] gameMatrix;

    static {
        currTurn=0;
        playerWin=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListeners();
        initMatrix();
    }

    View.OnClickListener onClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int pressedButtonId=v.getId();
            if(pressedButtonId==R.id.restartBtn) {
                confirmRestart();
                return;
            }
            Button pressed=(Button)findViewById(pressedButtonId);
            int currPlayer=(currTurn%2)+1;
            currTurn++;
            if(currPlayer==1)
                pressed.setBackgroundResource(R.drawable.cross);
            else
                pressed.setBackgroundResource(R.drawable.circle);
            pressed.setClickable(false);
            switch(pressedButtonId) {
                case R.id.lefttop:
                    gameMatrix[0][0]=currPlayer;
                    break;
                case R.id.midtop:
                    gameMatrix[0][1]=currPlayer;
                    break;
                case R.id.righttop:
                    gameMatrix[0][2]=currPlayer;
                    break;
                case R.id.leftmid:
                    gameMatrix[1][0]=currPlayer;
                    break;
                case R.id.center:
                    gameMatrix[1][1]=currPlayer;
                    break;
                case R.id.rightmid:
                    gameMatrix[1][2]=currPlayer;
                    break;
                case R.id.leftbottom:
                    gameMatrix[2][0]=currPlayer;
                    break;
                case R.id.midbottom:
                    gameMatrix[2][1]=currPlayer;
                    break;
                case R.id.rightbottom:
                    gameMatrix[2][2]=currPlayer;
                    break;
            }
            evalMatrix();
        }
    };

    public  void confirmRestart() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmRestart);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    public void restartGame() {
        currTurn=0;
        playerWin=0;
        resetMatrix();
        Button resetBtn=(Button)findViewById(R.id.lefttop);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.midtop);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.righttop);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.leftmid);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.center);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.rightmid);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.leftbottom);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.midbottom);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
        resetBtn=(Button)findViewById(R.id.rightbottom);
        resetBtn.setBackgroundResource(R.drawable.empty);
        resetBtn.setClickable(true);
    }

    public void addListeners() {
        Button newBtn=(Button)findViewById(R.id.lefttop);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.midtop);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.righttop);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.leftmid);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.center);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.rightmid);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.leftbottom);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.midbottom);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.rightbottom);
        newBtn.setOnClickListener(onClickListener);
        newBtn=(Button)findViewById(R.id.restartBtn);
        newBtn.setOnClickListener(onClickListener);
    }

    public void initMatrix() {
        gameMatrix=new int[3][];
        for(int i=0;i<3;i++) {
            gameMatrix[i]=new int[3];
            for(int j=0;j<3;j++) {
                gameMatrix[i][j]=0;
            }
        }
    }

    public void resetMatrix() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                gameMatrix[i][j]=0;
            }
        }
    }

    boolean checkRow() {
        for(int i=0;i<3;i++) {
            if(gameMatrix[i][0]!=0 && gameMatrix[i][0]==gameMatrix[i][1] && gameMatrix[i][1]==gameMatrix[i][2]) {
                playerWin=gameMatrix[i][0];
                return true;
            }
        }
        return false;
    }

    boolean checkCols() {
        for(int i=0;i<3;i++) {
            if(gameMatrix[0][i]!=0 && gameMatrix[0][i]==gameMatrix[1][i] && gameMatrix[1][i]==gameMatrix[2][i]) {
                playerWin=gameMatrix[0][i];
                return true;
            }
        }
        return false;
    }

    boolean checkDiags() {
        if(gameMatrix[0][0]!=0 && gameMatrix[0][0]==gameMatrix[1][1] && gameMatrix[1][1]==gameMatrix[2][2]) {
            playerWin=gameMatrix[0][0];
            return true;
        }
        if(gameMatrix[0][2]!=0 && gameMatrix[0][2]==gameMatrix[1][1] && gameMatrix[1][1]==gameMatrix[2][0]) {
            playerWin=gameMatrix[0][2];
            return true;
        }
        return false;
    }

    public void evalMatrix() {
        if(checkRow() || checkCols() || checkDiags()) {
            if(playerWin==1) {
                showDiag(1);
            }
            else {
                showDiag(2);
            }
        }
        if(currTurn==9 && playerWin==0) {
            showDiag(0);
        }
    }

    public void showDiag(int win) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(win==0) {
            builder.setMessage(R.string.gameDraw);
        }
        else if(win==1) {
            builder.setMessage(R.string.playerCross);
        }
        else {
            builder.setMessage(R.string.playerCircle);
        }
        builder.setNeutralButton(R.string.newGame, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });
        builder.show();
    }
}

