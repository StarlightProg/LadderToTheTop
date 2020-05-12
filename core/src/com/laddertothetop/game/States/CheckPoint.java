package com.laddertothetop.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class CheckPoint {
    int x=200;
    int y=21;
    int AmountHp=4;
    String state;

    public CheckPoint(){
        FileHandle fileReader = Gdx.files.internal("Savee.txt");
        String text = fileReader.readString();
       // fileReader.
       // System.out.println(text);
//        FileHandle fileWriter = Gdx.files.local("Savee.txt");
//        fileWriter.writeString("200"+"\n",true);
//        fileWriter.writeString("21"+"\n",true);
//        fileWriter.writeString("4"+"\n",true);
    }

    public void setCheckPoint(int x,int y, int AmountHp, State state){
        this.x=x;
        this.y=y;
        this.AmountHp=AmountHp;
        this.state=state.toString();
    }

    public void loadCheckpoint(){

    }
}
