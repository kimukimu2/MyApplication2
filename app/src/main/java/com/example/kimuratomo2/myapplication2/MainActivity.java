package com.example.kimuratomo2.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION = 1000;
    Spinner spinner1;
    Spinner spinner2;
    Button button;
    filetest filewrite;
    TextView countView;
    Counter counter=new Counter(countView);
    int n;
    String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
            strat_app(new filetest());
        } else {

        }

    }

    public void strat_app(filetest writer){
        filewrite=writer;
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
            //Spinnerのドロップダウンアイテムが選択された時
            public void onItemSelected(AdapterView parent, View viw, int arg2, long arg3) {
                Spinner spinner = (Spinner)parent;
                text2 = (String)spinner.getSelectedItem();
            }
            //Spinnerのドロップダウンアイテムが選択されなかった時
            public void onNothingSelected(AdapterView parent) {
            }
        });
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
            //Spinnerのドロップダウンアイテムが選択された時
            public void onItemSelected(AdapterView parent, View viw, int arg2, long arg3) {
                Spinner spinner = (Spinner)parent;
                String item2 = (String)spinner.getSelectedItem();
                n=Integer.parseInt(item2);
            }
            //Spinnerのドロップダウンアイテムが選択されなかった時
            public void onNothingSelected(AdapterView parent) {
            }
        });


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new button_number(filewrite));

    }




    class button_number implements OnClickListener{
        filetest writer;
        button_number(filetest file){
            writer=file;
        }
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        button_kaisatu button[] = new button_kaisatu[6];

        @Override
        public void onClick(View v) {
            Button btn[] = new Button[n];
            if(n>6){n=0;}
            for (int count = 0; count < n; count++) {
                button[count] = new button_kaisatu(writer, count + 1, counter);
            }
            if (n == 1) {
                view = layoutInflater.inflate(R.layout.one_button, null, false);
                setContentView(view);
            } else if (n == 2) {
                view = layoutInflater.inflate(R.layout.two_button, null, false);
                setContentView(view);
            } else if (n == 3) {
                view = layoutInflater.inflate(R.layout.three_button, null, false);
                setContentView(view);
            } else if (n == 4) {
                view = layoutInflater.inflate(R.layout.four_button, null, false);
                setContentView(view);
            } else if (n == 5) {
                view = layoutInflater.inflate(R.layout.five_button, null, false);
                setContentView(view);
            } else if (n == 6) {
                view = layoutInflater.inflate(R.layout.six_button, null, false);
                setContentView(view);
            } else {
                n=0;
            }
            if (n==0){
                counter.clear();
                strat_app(writer);
            }else {
                for (int count = 0; count < n; count++) {
                    if (count == 0) {
                        btn[count] = (Button) view.findViewById(R.id.button1);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                    if (count == 1) {
                        btn[count] = (Button) view.findViewById(R.id.button2);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                    if (count == 2) {
                        btn[count] = (Button) view.findViewById(R.id.button3);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                    if (count == 3) {
                        btn[count] = (Button) view.findViewById(R.id.button4);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                    if (count == 4) {
                        btn[count] = (Button) view.findViewById(R.id.button5);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                    if (count == 5) {
                        btn[count] = (Button) view.findViewById(R.id.button6);
                        btn[count].setOnClickListener(button[count]);
                        btn[count].setText(text2 + " " + (count + 1));
                    }
                }
                Button end = (Button) view.findViewById(R.id.button_end);
                end.setOnClickListener(new press_end(writer));
                Button back = (Button) view.findViewById(R.id.button_back);
                back.setOnClickListener(new press_back(writer));

                countView=(TextView) findViewById(R.id.textView3);
                countView.setText("現在のカウント回数:"+counter.get());
            }
        }
    }

    class press_end implements OnClickListener {
        private filetest write;
        press_end(filetest writer){
            write=writer;
        }
        @Override
        public void onClick(View v) {
            write.fileclose();
            finish();
        }
    }
    class press_back implements OnClickListener{
        private  filetest write;
        press_back(filetest writer){write=writer;}
        @Override
        public  void onClick(View v){
            write.fileclose();
            counter.clear();
            strat_app(new filetest());

        }
    }

    public class Counter {
        TextView text;
        Counter(TextView counter){
            text=counter;
        }
        private int count=0;
        void plus(String str){
            count++;
            updateCountView(str);
        }
        void clear(){
            count=0;
        }
        int get(){
            return count;
        }
        private void updateCountView(String str) {
            TextView countView = (TextView) findViewById(R.id.textView3);
            countView.setText("現在のカウント回数："+get());
            TextView hyouzi = (TextView) findViewById(R.id.textView4);
            hyouzi.setText(str);
        }

    }





    public void checkPermission() {
        // 既に許可している
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
        }
        // 拒否していた場合
        else{
            requestLocationPermission();
        }
    }

    // 許可を求める
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);

        } else {
            Toast toast = Toast.makeText(this, "アプリ実行に許可が必要です",Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, REQUEST_PERMISSION);

        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "何もできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}

