package phdhtl.bomoncntt.hang_k60_actionbar_custum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import phdhtl.bomoncntt.hang_k60_actionbar_custum.model.SinhVien;

public class LoginAcivity extends AppCompatActivity {
    //ArrayList<SinhVien> StudentCheckedItemList;
    static final int READ_BLOCK_SIZE = 100;
    EditText edtu,edtp;
    Button btnlogin;
    CheckBox ck;
    public  static final  String MyPreferences ="MYPREFS"; // khai báo tên file
    SharedPreferences pref; //khai báo
    SharedPreferences.Editor editor; //chỉnh sửa dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        // code clean
        mapView();
        pref=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        //đọc thông tin SharePreferences
        String username = pref.getString("USERNAME","");
        String password = pref.getString("PASSWORD","");
        if (!username.equals("")&& !password.equals("")){
            Intent in=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(in);
            finish();
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username="1851062657";
                String password="123";
                writeMessage(username,password);// ghi lại lịch sử đăng nhập
                if (edtu.getText().toString().equals(username)  && edtp.getText().toString().equals(password)){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Log.v("admin","Đăng nhập thành công");
                    Intent in=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);

                }else {
                    Toast.makeText(getApplicationContext(), " Tài khoản hoặc mật khẩu sai",Toast.LENGTH_LONG).show();
                    Log.v("admin","Tài khoản hoặc mật khẩu sai");
                }
                if (username.equals("1851062657")&& password.equals("123")){
                    if (ck.isChecked()){
                        //lưu thông tin xuống SharePrefereneces
                        editor=pref.edit();// chỉnh sửa file MYPREFS.xml
                        editor.putString("USERNAME",username);//ghi thông tin vào fileds USERNAME"admin"
                        editor.putString("PASSWORD",password);
                        editor.commit();
                    }else {
                        // xoá preferences
                        editor=pref.edit();
                        editor.clear();
                        editor.commit();
                    }
                }
//                finish();

            }
        });

    }

    private void mapView() {
        edtu=findViewById(R.id.edtusername);
        edtp=findViewById(R.id.edtpassword);
        btnlogin=findViewById(R.id.btnlogin);
        ck=(CheckBox)findViewById(R.id.checkBoxRemember);
    }
    private void writeMessage(String u, String p){
        try{
            FileOutputStream fileout= openFileOutput("datalogin.txt",MODE_APPEND);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(u + "," + p + ";\n");//dấu chấm phẩy là tạo ra csv: taokk ra cột và donnhf trong excel
            outputWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void readText(){
        try {
            FileInputStream fileIn = openFileInput("datalogin.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0){
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }
            InputRead.close();
            //textmsg.setText(s);
            Log.v("contentssss",s);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
