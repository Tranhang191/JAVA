package phdhtl.bomoncntt.th2_k60_spinner_listview.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import phdhtl.bomoncntt.th2_k60_spinner_listview.model.SinhVien;

public class DatabaseHelper extends SQLiteOpenHelper {
    //biến hằng khai báo bằng final
    public static final String Database_name="students.db";
    public static final String Table_name="student_table";
    public static final String col_masv="masv";
    public static final String col_tensv="tensv";
    public static final String col_gt="gt";
    public static final String col_lop="lop";
    //chạy đầu tiên
    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }
//kế tiếp
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+Table_name+"(masv Text primary key,tensv TEXT,gt TEXT,lop TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Table_name);
        onCreate(db);
    }
    public boolean insertData(String masv,String tensv,String gt,String lop){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_masv,masv);
        cv.put(col_tensv,tensv);
        cv.put(col_gt,gt);
        cv.put(col_lop,lop);
        long result=db.insert(Table_name,null,cv);
        if(result==-1){
            return false;
        }else {
            return true; //insert thành công
        }
    }
    //insert data
    public boolean insertData(SinhVien sv){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_masv,sv.getMasv());
        cv.put(col_tensv,sv.getTensv());
        cv.put(col_gt,sv.getGt());
        cv.put(col_lop,sv.getLop());
        long result=db.insert(Table_name,null,cv);
        if(result==-1){
            return false;
        }else {
            return true; //insert thành công
        }
    }
    public Cursor showKTData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+ Table_name,null);
        return cursor;
    }
    //ham hiện thi toàn bộ dữ liệu trong table
    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+ Table_name,null);
        return cursor;
    }

    public Integer delete(String masv){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Table_name,"masv=?",new String[]{masv});
        return db.delete(Table_name,"masv=?",new String[]{masv});
    }
    public  Boolean update(String masv,String tensv,String gt,String lop){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_masv,masv);
        cv.put(col_tensv,tensv);
        cv.put(col_gt,gt);
        cv.put(col_lop,lop);
        //update student_table set masv='',...where masv='masv'
        int kq=db.update(Table_name,cv,"masv=?",new String[]{masv});
        if(kq==-1){
            return false;
        }else {
            return true; //insert thành công
        }
    }

}
