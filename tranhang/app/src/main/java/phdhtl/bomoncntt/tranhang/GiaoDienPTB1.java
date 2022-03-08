package phdhtl.bomoncntt.tranhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import phdhtl.bomoncntt.tranhang.model.bai1;

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
        x.setEnabled(false);
        btngiai=(Button)findViewById(R.id.btngiai);
        btnlamlai=(Button)findViewById(R.id.btnlamlai);
        btngiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float a = Float.parseFloat(hsa.getText().toString());
                    float b = Float.parseFloat(hsb.getText().toString());
                    bai1 pt = new bai1(a, b);
                    x.setText(pt.giaiPT());

                } catch(Exception e) {
                    if (hsa.length() == 0) {
                        Toast.makeText(getApplicationContext(), "nhập hệ số a", Toast.LENGTH_SHORT).show();
                        hsa.requestFocus();
                    } else  if(hsa.length()!=0){
                        Toast.makeText(getApplicationContext(),"nhập hệ số b",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        hsa.requestFocus();
                    }
                }
            }
        });
        btnlamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsa.requestFocus();
                hsa.setText("");
                hsb.setText("");
                x.setText("");
            }
        });

    }
}