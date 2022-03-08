package phdhtl.bomoncntt.hang_th2_k60;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

import phdhtl.bomoncntt.hang_th2_k60.model.PhepToan;

public class MainActivity extends AppCompatActivity {
    EditText txta,txtb,txtkq;
    Button btn_cong, btn_tru,btn_nhan,btn_chia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txta=findViewById(R.id.txta);
        txtb=findViewById(R.id.txtb);
        txtkq=findViewById(R.id.txtkq);
        txtkq.setEnabled(false);
        btn_cong=findViewById(R.id.btn_cong);
        btn_tru=findViewById(R.id.btn_tru);
        btn_nhan=findViewById(R.id.btn_nhan);
        btn_chia=findViewById(R.id.btn_chia);
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a= Double.parseDouble(txta.getText().toString());
                double b= Double.parseDouble(txtb.getText().toString());
                txtkq.setText(""+ PhepToan.tong(a,b));
                Intent in=new Intent(getApplicationContext(),XulyActivity2.class);
                //làm sao truyền giá trị a,b này sang XulyActivity2
                in.putExtra("hesoa",txta.getText().toString());
                in.putExtra("hesob",txtb.getText().toString());
                in.putExtra("dau","+");
                startActivityForResult(in,100);
            }
        });
        //thực hiện phép trừ
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),XulyActivity2.class);
                //làm sao truyền giá trị a,b này sang XulyActivity2
                in.putExtra("hesoa",txta.getText().toString());
                in.putExtra("hesob",txtb.getText().toString());
                in.putExtra("dau","-");
                startActivityForResult(in,101);
            }
        });
        //thực hiện phép nhân
        btn_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),XulyActivity2.class);
                //làm sao truyền giá trị a,b này sang XulyActivity2
                in.putExtra("hesoa",txta.getText().toString());
                in.putExtra("hesob",txtb.getText().toString());
                in.putExtra("dau","*");
                startActivityForResult(in,102);
            }
        });
        //thực hiện phép chia
        btn_chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),XulyActivity2.class);
                //làm sao truyền giá trị a,b này sang XulyActivity2
                in.putExtra("hesoa",txta.getText().toString());
                in.putExtra("hesob",txtb.getText().toString());
                in.putExtra("dau","/");
                startActivityForResult(in,103);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==100) {
                if (resultCode==RESULT_OK)
                {
                    double kq=data.getDoubleExtra("result",0);
                    txtkq.setText(""+kq);
                } else if (requestCode==RESULT_CANCELED){
                    Toast.makeText(this,"xử lý lỗi",
                            Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode==101) {
                if (resultCode==RESULT_OK)
                {
                    double kq=data.getDoubleExtra("result",0);
                    txtkq.setText(""+kq);
                } else if (requestCode==RESULT_CANCELED){
                    Toast.makeText(this,"xử lý lỗi",
                            Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode==102) {
                if (resultCode==RESULT_OK)
                {
                    double kq=data.getDoubleExtra("result",0);
                    txtkq.setText(""+kq);
                } else if (requestCode==RESULT_CANCELED){
                    Toast.makeText(this,"xử lý lỗi",
                            Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode==103) {
                if (resultCode==RESULT_OK)
                {
                    double kq=data.getDoubleExtra("result",0);
                    txtkq.setText(""+kq);
                } else if (requestCode==RESULT_CANCELED){
                    Toast.makeText(this,"xử lý lỗi",
                            Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex){
            Toast.makeText(this,ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}