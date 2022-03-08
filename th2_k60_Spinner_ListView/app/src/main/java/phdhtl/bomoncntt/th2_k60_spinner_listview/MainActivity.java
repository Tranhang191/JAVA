package phdhtl.bomoncntt.th2_k60_spinner_listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import phdhtl.bomoncntt.th2_k60_spinner_listview.helper.DatabaseHelper;
import phdhtl.bomoncntt.th2_k60_spinner_listview.model.SinhVien;

public class MainActivity extends AppCompatActivity {
    EditText txtmasv,txttensv;
    Button btnthem,btnsua,btnluu,btnlamlai,btnxoa;
    Spinner spinnerlop;
    ListView lvsv;
    RadioGroup radiogroupsex;
    RadioButton radioButtonsex;
    ArrayList<String> arrayListlop;
    ArrayList<SinhVien> arrayListsv;//nguồn data SinhVien
    DatabaseHelper mydb;
    ArrayAdapter<SinhVien> adaptersv=null;//trung gian chuyển hoá Data Sinh viên
    int thaotac=0;
    String lop="";
    int vt=-1;//chưa chọn 1 vị trí nào cả trong listview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DatabaseHelper(this);//gọi
        mydb.insertData("001","Hang","Nữ","khoá 60");
         //Log.d("ok thành công",""+mydb.insertData("001","Hang","Nữ","khoá 60"));
         //Toast.makeText(MainActivity.this,"ok"+mydb.insertData("001","Hang","Nữ","khoá 60"));
        //DatabaseHelper.Database_name="sfad";//sai vì nó là biến hằng
       boolean kq=mydb.insertData("001","nhã","nam","khoá60");
        if (kq){
            Toast.makeText(getApplicationContext(),"insert thành công",Toast.LENGTH_LONG);
        }else {
            Toast.makeText(getApplicationContext(),"insert thất bại",Toast.LENGTH_LONG);
        }

        txtmasv=findViewById(R.id.txtmasv);
        txttensv=findViewById(R.id.txttensv);
        spinnerlop=findViewById(R.id.spinnerlop);
        spinnerlop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lop=parent.getItemAtPosition(position).toString();
                lop=arrayListlop.get(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lop="";
            }
        });
        //b1
        arrayListlop=new ArrayList<String>();
        thaotac=0;
        arrayListlop.add("Khoá 59");
        arrayListlop.add("Khoá 60");
        arrayListlop.add("Khoá 61");
        arrayListlop.add("Khoá 62");
        //b2
        ArrayAdapter<String> adapterlop=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayListlop);
        adapterlop.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerlop.setAdapter(adapterlop);
        lvsv=(ListView) findViewById(R.id.listviewsinhvien);
        arrayListsv=new ArrayList<SinhVien>();//bị ẩn arraylist
        //arrayListsv.add("01-nha-nam-khoa59");
//        adaptersv=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListsv);
//        lvsv.setAdapter(adaptersv);
        showDataListView();
        //su kien click của listview
        lvsv.setAdapter(adaptersv);
        lvsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vt=position;//lưu lại cái vị trí
//                //Log.v("ListView",""+position);
//                String str=arrayListsv.get(position).toString();
//                Log.v("ListView",str);
//                String a[]=str.split("-");
//                Log.v("mang cat",a[0]);
                txtmasv.setText(arrayListsv.get(position).getMasv());
                txttensv.setText(arrayListsv.get(position).getTensv());
                if (arrayListsv.get(position).getGt().equalsIgnoreCase("Nam")){
                    radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNam);
                    radiogroupsex.check(radioButtonsex.getId());
                }else {
                    radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNu);
                    radiogroupsex.check(radioButtonsex.getId());
                }
                selectValue(spinnerlop,arrayListsv.get(position).getLop());
                //sửa dữ liệu

                btnthem.setEnabled(false);//nút sáng lên
                btnsua.setEnabled(true);
                btnluu.setEnabled(false);


            }
        });
        //sự kiện longclick
        lvsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //show thông báo có muốn xoá k
                //position vi trí mà chọn trên listview

            showD(position);
                return false;
            }
        });


        radiogroupsex=(RadioGroup)findViewById(R.id.radiogroupex);
        btnthem=findViewById(R.id.btnthem);
        btnluu=findViewById(R.id.btnluu);
        btnlamlai=findViewById(R.id.btnlamlai);
        btnsua=findViewById(R.id.btnsua);
        btnxoa=findViewById(R.id.btnxoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show thông báo có muốn xoá k
                if(vt==-1) {
                    Toast.makeText(getApplicationContext(), "chưa chọn phần tử nào để xoá", Toast.LENGTH_SHORT).show();
                }else {
                    showD(vt);
                }

            }
        });
        //?
        btnthem.setEnabled(true);//nút sáng lên
        btnsua.setEnabled(false);
        btnluu.setEnabled(false);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sữa dữ liệu

                btnthem.setEnabled(false);//nút sáng lên
                btnsua.setEnabled(false);
                btnluu.setEnabled(true);
                txtmasv.setEnabled(false);

                thaotac=1;//người sử dụng đang nhấn thao tác sửa
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thaotac==0){
                    String masv=txtmasv.getText().toString();
                    String tensv=txttensv.getText().toString();
                    int selectedid=radiogroupsex.getCheckedRadioButtonId();
                    radioButtonsex=findViewById(selectedid);
                    String gt=radioButtonsex.getText().toString();
                    String full=masv+"-"+tensv+"-"+radioButtonsex.getText().toString()+"-"+lop;
                    //arrayListsv.add(full);
                    //adaptersv.notifyDataSetChanged();
                    boolean insertedKq=mydb.insertData(masv,tensv,gt,lop);
                    if (insertedKq){
                        Toast.makeText(MainActivity.this,"Insert Thành công",Toast.LENGTH_SHORT).show();
                        arrayListsv.clear();
                        showDataListView();
                    }else
                    {
                        Toast.makeText(MainActivity.this,"Insert Không  Thành công",Toast.LENGTH_SHORT).show();
                        Log.d("Inserted","không thành công");
                    }

                    btnthem.setEnabled(true);//nút sáng lên
                    btnsua.setEnabled(false);
                    btnluu.setEnabled(false);
                    resetView();
                }else //sửa thông tin//thao tác =1
                {

                    //arrayListsv.remove(vt);
                    String masv=txtmasv.getText().toString();
                    String tensv=txttensv.getText().toString();
                    //lấy giới tinh
                    int selectedid=radiogroupsex.getCheckedRadioButtonId();
                    radioButtonsex=findViewById(selectedid);
                    String gt=radioButtonsex.getText().toString();
                    String full=masv+"-"+tensv+"-"+radioButtonsex.getText().toString()+"-"+lop;
                    boolean updatedKp=mydb.update(masv,tensv,gt,lop);
                    if (updatedKp){

                        Toast.makeText(MainActivity.this,"update Thành công",Toast.LENGTH_SHORT).show();
//                        arrayListsv.clear();
//                        showDataListView();
                        SinhVien sv=new SinhVien(masv,tensv,gt,lop);
                        arrayListsv.set(vt,sv);
                        adaptersv.notifyDataSetChanged();//bao listView có nguồn dữ liệu thay đổi và tự động update
                    }else
                    {
                        Toast.makeText(MainActivity.this,"update Không  Thành công",Toast.LENGTH_SHORT).show();
                        Log.d("updated","không thành công");
                    }
                    btnthem.setEnabled(true);//nút sáng lên
                    btnsua.setEnabled(false);
                    btnluu.setEnabled(false);
                    txtmasv.setEnabled(true);
                    resetView();
                }
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnthem.setEnabled(false);//nút sáng lên
                btnsua.setEnabled(false);
                btnluu.setEnabled(true);
                txtmasv.setEnabled(true);
                resetView();
            }
        });
        btnlamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnthem.setEnabled(true);//nút sáng lên
                btnsua.setEnabled(false);
                btnluu.setEnabled(false);
                txtmasv.setEnabled(true);
                resetView();
            }
        });
    }
    private void resetView(){
        thaotac=0;

        txtmasv.requestFocus();
        txtmasv.setText("");
        txttensv.setText("");
        radioButtonsex=(RadioButton)findViewById(R.id.radioButtonNam);
        radiogroupsex.check(radioButtonsex.getId());
         spinnerlop.setSelection(0);

         vt=-1;
    }
    private void selectValue(Spinner spinner,Object value ){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(value)){
                spinner.setSelection(i);
                break;
            }
        }
    }
    private void showD(int i){
        //parter buider
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xoá không?");
        //duong yes
        builder.setPositiveButton("Có",(dialog, which) -> {
            //remode 1 phần từ trong mảng arratlist
//            String ar[]=arrayListsv.get(i).split("-");
//            String masv=ar[0];
            Integer deletekq=mydb.delete(arrayListsv.get(i).getMasv());
            if(deletekq==0) {
                Toast.makeText(MainActivity.this,"delete Thành công",Toast.LENGTH_SHORT).show();
                arrayListsv.remove(i);//xoá 1 phần tử trong mảng
                adaptersv.notifyDataSetChanged();//

            }else {
                Toast.makeText(MainActivity.this,"delete không Thành công",Toast.LENGTH_SHORT).show();
            }


            //vt=-1;//trả lại trạng thái ban đầu
            dialog.dismiss();//đóng cửa sổ thông báo
            resetView();

        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//đóng cửa sổ thông báo
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    // xây dựng 1 showDataListView từ Sql
    private void showDataListView(){
        Cursor cursor=mydb.showData();
        //trả về vùng địa chỉ data
        if (cursor.getCount()==0){
            return;
        }else {
            while (cursor.moveToNext()){
                String masv=cursor.getString(0);//lấy dữ liệu cột masv
                String tensv=cursor.getString(1);
                String gt=cursor.getString(2);
                String lop=cursor.getString(3);
                //String full=masv+"-"+tensv+"-"+gt+"-"+lop;
                SinhVien sv=new SinhVien(masv,tensv,gt,lop);
                arrayListsv.add(sv);

            }
            adaptersv=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListsv);
            lvsv.setAdapter(adaptersv);
        }
    }
}