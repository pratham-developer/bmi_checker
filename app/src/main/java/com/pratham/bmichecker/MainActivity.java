package com.pratham.bmichecker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tview;
        EditText editw;
        EditText edith;
        EditText editout;
        Button but1;

        editw = findViewById(R.id.weightbox);
        edith = findViewById(R.id.heightbox);
        editout = findViewById(R.id.out);
        but1 = findViewById(R.id.but);
        tview = findViewById(R.id.result);
        DecimalFormat way = new DecimalFormat("0.00");

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inpw = editw.getText().toString().strip();
                String inph = edith.getText().toString().strip();
                if(inpw.isEmpty() &&  inph.isEmpty()){
                    Toast.makeText(getApplicationContext(),"fill weight and height",Toast.LENGTH_SHORT).show();
                    tview.setText(getString(R.string.hintres));
                    tview.setTextColor(getResources().getColor(R.color.primary));
                    tview.setHintTextColor(getResources().getColor(R.color.tertiary));
                    editout.setText(getString(R.string.hintout));
                }
                else if(inpw.isEmpty()){
                    Toast.makeText(getApplicationContext(),"fill weight",Toast.LENGTH_SHORT).show();
                    tview.setText(getString(R.string.hintres));
                    tview.setTextColor(getResources().getColor(R.color.primary));
                    tview.setHintTextColor(getResources().getColor(R.color.tertiary));
                    editout.setText(getString(R.string.hintout));
                }
                else if(inph.isEmpty()){
                    Toast.makeText(getApplicationContext(),"fill height",Toast.LENGTH_SHORT).show();
                    tview.setText(getString(R.string.hintres));
                    tview.setTextColor(getResources().getColor(R.color.primary));
                    tview.setHintTextColor(getResources().getColor(R.color.tertiary));
                    editout.setText(getString(R.string.hintout));
                }
                else{
                    editw.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    edith.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    float w = Float.parseFloat(inpw);
                    float h = (Float.parseFloat(inph))/100;
                    float bmi = w/(h*h);
                    editout.setText(way.format(bmi));
                    if(bmi>=35.0){
                        tview.setText(getString(R.string.extr));
                        tview.setTextColor(getResources().getColor(R.color.red));
                    }
                    if(bmi<35 && bmi>=30){
                        tview.setText(getString(R.string.ob));
                        tview.setTextColor(getResources().getColor(R.color.orange));
                    }
                    if(bmi<30 && bmi>=25){
                        tview.setText(getString(R.string.over));
                        tview.setTextColor(getResources().getColor(R.color.yellow));
                    }
                    if(bmi<25 && bmi>=18.5){
                        tview.setText(getString(R.string.fit));
                        tview.setTextColor(getResources().getColor(R.color.green));
                    }
                    if(bmi<18.5){
                        tview.setText(getString(R.string.und));
                        tview.setTextColor(getResources().getColor(R.color.blue));
                    }


                }
            }
        });


    }
}