package phdhtl.bomoncntt.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import phdhtl.bomoncntt.th2.model.PTB2;

public class GiaoDienPTB2 extends AppCompatActivity {
    EditText hsa,hsb,hsc,x;
    Button btngiai;
    Button btnlamlai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_b2);
        hsa=(EditText)findViewById(R.id.txt_a);
        hsb=(EditText)findViewById(R.id.txt_b);
        hsc=(EditText)findViewById(R.id.txt_c);
        x=(EditText)findViewById(R.id.txt_x);
        x.setEnabled(false);
        btngiai=(Button)findViewById(R.id.btn_giai);
        btnlamlai=(Button)findViewById(R.id.btn_lamlai);
        btngiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    float a = Float.parseFloat(hsa.getText().toString());
                    float b = Float.parseFloat(hsb.getText().toString());
                    float c = Float.parseFloat(hsc.getText().toString());
                    PTB2 b2 = new PTB2(a,b,c);
                    x.setText(b2.giaiPTB2());
                } catch (Exception e) {
                    if (hsa.length() == 0) {
                        Toast.makeText(getApplicationContext(), "nhập hệ số a", Toast.LENGTH_SHORT).show();
                    } else if (hsa.length() != 0) {
                        Toast.makeText(getApplicationContext(), "nhập hệ số b và c", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "nhập hệ số c", Toast.LENGTH_SHORT).show();
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
                hsc.setText("");
                x.setText("");
            }
        });
    }
}