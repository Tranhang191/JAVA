package phdhtl.bomoncntt.hang_k60_actionbar_custum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import phdhtl.bomoncntt.hang_k60_actionbar_custum.helper.DatabaseHelper;

public class infoSVActivity extends AppCompatActivity {
    Spinner spinerlop;
    ArrayList<String> arrayListlop;
    String lop="";
    EditText txtmasv,txttensv;
    Button btnluu,btnlamlai;
    private RadioGroup radiogroupsex;
    private RadioButton radioButtonsex;
    DatabaseHelper mydb=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_svactivity);

        mydb=new DatabaseHelper(this);
        btnluu=(Button)findViewById(R.id.btnluu);
        btnlamlai=(Button)findViewById(R.id.btnlamlai);
        txtmasv=(EditText)findViewById(R.id.txtmasv);
        txttensv=(EditText)findViewById(R.id.txttensv);
        radiogroupsex=(RadioGroup)findViewById(R.id.radiogroupsex);
        spinerlop=findViewById(R.id.spinerlop);
        //b1
        arrayListlop=new ArrayList<String>();
        arrayListlop.add("khoá 59");
        arrayListlop.add("khoá 60");
        arrayListlop.add("khoá 61");
        arrayListlop.add("khoá 62");
        //b2
        ArrayAdapter<String> adapterlop = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListlop);
        adapterlop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerlop.setAdapter(adapterlop);
        spinerlop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lop=arrayListlop.get(position).toString();
                Log.v("lop",lop);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lop="";
                Log.v("lop",lop);
            }

        });
        Intent intent=getIntent();// lấy data từ mainActivity gửi sang  gồm 4 biến
        String masv=intent.getStringExtra("MASV");
        String tensv=intent.getStringExtra("TENSV");
        String gt=intent.getStringExtra("GT");
        String lop=intent.getStringExtra("LOP");
        String Flag=intent.getStringExtra("Flag");
        Log.v("thamso",masv+tensv+gt+lop);
        if (!masv.equals("")){
            txtmasv.setText(masv);
            txttensv.setText(tensv);
//            if(arrayListlop.get(vt).getGt().equalsIgnoreCase("Nam")){
//                radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNam);
//                radiogroupsex.check(radioButtonsex.getId());
//            }else {
//                radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNu);
//                radiogroupsex.check(radioButtonsex.getId());
//            }
//            selectValue(spinerlop,arrayLissv.get(position).getLop());
            int selectedid=radiogroupsex.getCheckedRadioButtonId();
            radioButtonsex=findViewById(selectedid);
            String GT=radioButtonsex.getText().toString();
            radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNam);
            radiogroupsex.check(radioButtonsex.getId());
            spinerlop.setSelection(0);



            //set giới tính và lớp ra spinner
        }
        if (Flag.equals("ADD")) {
            setTitle("Thêm mới");
        }else {
            setTitle("Sữa dữ liệu");
        }
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (Flag.equals("ADD")) {// Đang nhấn nút icon ADD

                     int selectedId = radiogroupsex.getCheckedRadioButtonId();
                     radioButtonsex = (RadioButton) findViewById(selectedId);

                     boolean Inserted = mydb.insertData(txtmasv.getText().toString(), txttensv.getText().toString(), radioButtonsex.getText().toString(),lop);
                     if (Inserted) {
                         Toast.makeText(infoSVActivity.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(infoSVActivity.this, "Data is failed", Toast.LENGTH_SHORT).show();
                     }


                 }else {
                     boolean Inserted = mydb.insertData(txtmasv.getText().toString(), txttensv.getText().toString(), radioButtonsex.getText().toString(), lop);
                     if (Inserted) {
                         Toast.makeText(infoSVActivity.this, "Data is update", Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(infoSVActivity.this, "Data is update failed", Toast.LENGTH_SHORT).show();
                     }
                 }
                finish();// đóng của hiện tại
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                in.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);// xoá đi cửa sổ parent
                startActivity(in);// mở lên của sổ mainactivity
            }
        });
    }
}
