package com.example.kimuratomo2.myapplication2;

import android.view.View.OnClickListener;
import android.view.View;

/**
 * Created by kimuratomo2 on 2017/10/21.
 */

public class button_kaisatu implements OnClickListener {
    private filetest write;
    int i;
    private MainActivity.Counter counter;
    button_kaisatu(filetest file,int n,MainActivity.Counter count){
        write=file;
        i=n;
        counter=count;
    }

    @Override
    public void onClick(View v) {
        write.writeFile(i);
        counter.plus(i+"のボタンを入力");
    }
}
