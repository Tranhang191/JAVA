package phdhtl.bomoncntt.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GiaoDienChinh extends AppCompatActivity {
TextView click_ptb1, click_ptb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giaodienchinh);
        Log.d( "start", "thu 1");
        click_ptb1=(TextView)findViewById(R.id.click_ptb1);
        click_ptb2=(TextView)findViewById(R.id.click_ptb2);
        click_ptb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                Intent in=new Intent(getApplicationContext(),GiaoDienPTB1.class);
                startActivity(in);
            }
        });

        click_ptb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                Intent in = new Intent(getApplicationContext(), GiaoDienPTB2.class);
                startActivity(in);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d( "onCreate", "thu 2");
        click_ptb1.setText("ax+b=0");
    }
}