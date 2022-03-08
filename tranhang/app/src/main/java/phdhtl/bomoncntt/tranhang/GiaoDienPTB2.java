package phdhtl.bomoncntt.tranhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import phdhtl.bomoncntt.tranhang.model.bai2;

public class GiaoDienPTB2 extends AppCompatActivity {
    EditText hsa,hsb,hsc,x;
    Button btngiai,btnlamlai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_b2);
        hsa=(EditText)findViewById(R.id.txthsa);
        hsb=(EditText)findViewById(R.id.txthsb);
        hsc=(EditText)findViewById(R.id.txtthsc);
        x=(EditText)findViewById(R.id.txthsx);
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
                    bai2 b2 = new bai2(a, b, c);
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