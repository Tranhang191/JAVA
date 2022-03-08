package phdhtl.bomoncntt.th2_k60_actionbar_custum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent in = new Intent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // cho biết đã tham chiếu đến menu trong actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.item_action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId=item.getItemId(); //lấy tất cả các item mà đã đặt trong iteam_actionbar
        if (itemId==R.id.menu_add){// người dùng nút cộng
            Log.v("add","ok");
        }else  if (itemId==R.id.menu_delete){
            Log.v("delete","ok");
        }else {
            Log.v("edit","ok");
        }
        return super.onOptionsItemSelected(item);
    }
}