package phdhtl.bomoncntt.hang_k60_actionbar_custum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

import phdhtl.bomoncntt.hang_k60_actionbar_custum.helper.DatabaseHelper;
import phdhtl.bomoncntt.hang_k60_actionbar_custum.helper.MylistAdapter;
import phdhtl.bomoncntt.hang_k60_actionbar_custum.model.SinhVien;

import static phdhtl.bomoncntt.hang_k60_actionbar_custum.LoginAcivity.MyPreferences;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    ArrayList<SinhVien> arrayListSV=null;// chứa các phần tử trong csdl
    MylistAdapter adapter=null; //adapter custom
    private ListView lvsv = null;
    ArrayList<SinhVien> StudentCheckedItemList;// mảng arraylist chứa ác phần tủe mà đc check
    SharedPreferences pref; //khai báo
    SharedPreferences.Editor editor; //chỉnh sửa dữ liệu


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        StudentCheckedItemList =new ArrayList<>();
        mydb = new DatabaseHelper(this);
       mydb.insertData("001","hang","nu","khoá 60");
//        mydb.insertData("002","nhan","nu","khoá 59");
//        mydb.insertData("003","tai","nam","khoá 61");

        arrayListSV=new ArrayList<>();// khởi tạo mảng lưu các đối tượng
        lvsv = findViewById(R.id.lvsinhvien);// ảnh xạ  từ listview sang java
        Cursor cursor= mydb.showData();// đổ dữ liệu từ trong sql
        while (cursor.moveToNext()){
            SinhVien sv = new SinhVien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            arrayListSV.add(sv);
        }
        // có 3 phần từ sinh viên
        // truyền arraya qua constructor
        adapter = new MylistAdapter(this, arrayListSV);
        lvsv.setAdapter(adapter);
        lvsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.sinhvien_list_item_checkbox);
                Log.v("nhan","ok");
                boolean checkChecked=false;
                if (itemCheckbox.isChecked()) {
                    itemCheckbox.setChecked(false);
                    checkChecked = false;
                } else {
                    itemCheckbox.setChecked(true);
                    checkChecked = true;
                }
                SinhVien sv=new SinhVien();
                sv.setMasv(arrayListSV.get(position).getMasv());
                sv.setTensv(arrayListSV.get(position).getTensv());
                sv.setGt(arrayListSV.get(position).getGt());
                sv.setLop(arrayListSV.get(position).getLop());
                Log.v("ojectsinhvien",""+sv);
                addCheckListItem(sv, checkChecked);
                //check xem đã làm đúng việc check hay không check và đưa ra danh sách đnag check
                Log.d("listreal",""+getStudent());
            }
        });
    }
    private String getStudent(){
        StringBuffer b=new StringBuffer();
        if (StudentCheckedItemList!=null) {
            int size = StudentCheckedItemList.size();//lấy kích mà đã chọn
            for (int i = 0; i < size; i++) {
                SinhVien sv = StudentCheckedItemList.get(i);
                b.append(sv.getMasv());
                b.append(" ");
            }
        }
        return b.toString().trim();
    }
    private void addCheckListItem(SinhVien user,boolean add){
        if (StudentCheckedItemList!=null){
            boolean accountExist=false;
            int existPostion=-1;// vị trí chưa chọn
            int size=StudentCheckedItemList.size();//lấy kích mà đã chọn
            for (int i=0;i<size;i++){
                SinhVien sv=StudentCheckedItemList.get(i);
                if (sv.getMasv().equals(user.getMasv())){
                    accountExist=true;//đã tồn tại
                    existPostion=i;
                    break;
                }
            }
            if (add){//checked
                if (!accountExist){//chưa có trong mảng
                    StudentCheckedItemList.add(user);//đây là thằng sinh viên mình tìm
                }
            }else {// bỏ đc check thì kiểm tra xem có trong trang mạng không
                if (accountExist)//mục đang chọn có xuất hiện trong trang mảng(bỏ dấu check)
                {
                    if (existPostion!=-1){
                        StudentCheckedItemList.remove(existPostion);
                    }
                }
            }
        }else {

        }

    }
    // cho biết là tham chiếu đến menu chính đã tạo ea actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.item_action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId=item.getItemId();
        if (itemId==R.id.menu_add){
            Log.v("add","ok");
            //MỞ cửa sổ infoSVActivity
            Intent in=new Intent(this,infoSVActivity.class);
            in.putExtra("Flag","ADD");
            in.putExtra("MASV","");
            in.putExtra("TENSV","");
            in.putExtra("GT","");
            in.putExtra("LOP","");
            startActivity(in);
        }else if (itemId==R.id.menu_xoa){

            if (StudentCheckedItemList!=null){// chọn ít nhất 1 phần tử
                int size=StudentCheckedItemList.size();
                if (size!=1){
                    Toast.makeText(this,"chọn ít nhất 1 phần tử để xoá",Toast.LENGTH_LONG).show();
                    Log.v("trạng thái","chọn ít nhất 1 phần tử để xoá");
                }else {//chọn ít nhất 1 phần tử
                    for (int i = 0; i<StudentCheckedItemList.size();i++){
                        SinhVien sv=StudentCheckedItemList.get(i);
                        Integer delete=mydb.delete(sv.getMasv());// xoá trong csdl
                        if(delete>0){ //xoá thành công
                            //xoá listView (xoá arraylist)
                            StudentCheckedItemList.remove(i);
                            size=StudentCheckedItemList.size();//update lại kích thước sau khi xoá
                        }else {
                            Toast.makeText(this,"Xoá thất bại",Toast.LENGTH_LONG).show();
                            Log.v("trạng thái","Xoá thất bại");
                        }
                    }
                    arrayListSV.clear(); //xoá hết trên arraylist
                    Cursor cursor=mydb.showData();
                    while (cursor.moveToNext()){
                        SinhVien sv=new SinhVien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                        arrayListSV.add(sv);
                    }
                    adapter=new MylistAdapter(this,arrayListSV);
                    lvsv.setAdapter(adapter);
                }
            }
            Log.v("delete","ok");
        }else if (itemId==R.id.menu_sua) {
            Log.v("edit","ok");

            if(StudentCheckedItemList!=null){// chọn ít nhất một phần tử
                int size=StudentCheckedItemList.size();//LẤy số thứ tụ đã check
                if (size!=1)
                {
                    Toast.makeText(this,"chọn 1 phần tử để sửa",Toast.LENGTH_LONG).show();
                    Log.v("trạng thái","chọn 1 phần tử để sửa");
                }else {
                    SinhVien sv=StudentCheckedItemList.get(0);
                    Intent in = new Intent(this, infoSVActivity.class);
                    in.putExtra("Flag", "EDIT");
                    in.putExtra("MASV", sv.getMasv());
                    in.putExtra("TENSV", sv.getTensv());
                    in.putExtra("GT", sv.getGt());
                    in.putExtra("LOP", sv.getLop());
                    startActivity(in);
                }
            }
        }else {
            editor=pref.edit();// chỉnh sửa file MYPREFS.xml
            editor.remove("USERNAME");
            editor.remove("PASSWORD");
            editor.commit();
            finish();
            Intent in=new Intent(getApplicationContext(),LoginAcivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

}