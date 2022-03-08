package phdhtl.bomoncntt.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import phdhtl.bomoncntt.th2.model.PTB1;

public class GiaoDienPTB1 extends AppCompatActivity {
EditText hsa,hsb,x;
Button btngiai,btnlamlai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giaodienb1);
        hsa=(EditText)findViewById(R.id.txta);
        hsb=(EditText)findViewById(R.id.txtb);
        x=(EditText)findViewById(R.id.txtx);
        btngiai=(Button)findViewById(R.id.btngiai);
        btnlamlai=(Button)findViewById(R.id.btnlamlai);
        btngiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float a = Float.parseFloat(hsa.getText().toString());
                    float b = Float.parseFloat(hsb.getText().toString());
                    PTB1 p = new PTB1(a,b);
                    x.setText(p.giaiPT());
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Lỗi nhập liệu",Toast.LENGTH_SHORT).show();
                }
            }
        });
            btnlamlai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hsa.setText("");
                    hsb.setText("");
                    x.setText("");
                }
            });

    }
}