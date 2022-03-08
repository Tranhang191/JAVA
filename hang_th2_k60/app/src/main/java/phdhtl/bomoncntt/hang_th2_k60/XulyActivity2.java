package phdhtl.bomoncntt.hang_th2_k60;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import phdhtl.bomoncntt.hang_th2_k60.model.PhepToan;

public class XulyActivity2 extends AppCompatActivity {
    double kq=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuly2);
        // show icon back home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //đọc thông tin hesoa, hesob
        Intent in=getIntent();
        String a=in.getStringExtra("hesoa");
        String b=in.getStringExtra("hesob");
        String dau=in.getStringExtra("dau");
        //if(dau==0)//sai
        try {

            if (dau.equals("+")) {
                double hsa = Double.parseDouble(a);
                double hsb = Double.parseDouble(b);
                kq = PhepToan.tong(hsa, hsb);
            } else if ((dau.equals("-"))) {
                double hsa = Double.parseDouble(a);
                double hsb = Double.parseDouble(b);
                kq = PhepToan.tru(hsa, hsb);
            }
        }catch (Exception ie){

        }
            try {

                if (dau.equals("*")) {
                    double hsa = Double.parseDouble(a);
                    double hsb = Double.parseDouble(b);
                    kq = PhepToan.nhan(hsa, hsb);
                } else if ((dau.equals("/"))) {
                    double hsa = Double.parseDouble(a);
                    double hsb = Double.parseDouble(b);
                    kq = PhepToan.chia(hsa, hsb);
                }
            }catch (Exception ie){

        }
        Intent resultIntent=new Intent();
        resultIntent.putExtra("result",kq);//data trả về cho hàm main
        setResult(RESULT_OK, resultIntent);
        finish();//đóng activity
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}