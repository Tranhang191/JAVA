package phdhtl.bomoncntt.hang_k60_actionbar_custum.helper;
import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import phdhtl.bomoncntt.hang_k60_actionbar_custum.R;
import phdhtl.bomoncntt.hang_k60_actionbar_custum.model.SinhVien;

public class MylistAdapter extends ArrayAdapter<SinhVien> {

    private final Activity context;
    private final ArrayList<SinhVien> listsv;
    private static class ViewHolder {
        TextView txtMasv;
        TextView txtTensv;
        TextView txtgt;
        TextView txtlop;
    }

    public MylistAdapter(Activity context, ArrayList<SinhVien> data) {
        super(context, R.layout.layout_item_sv,data);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.listsv = data;
        Log.d("listsv",""+listsv);

    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        SinhVien dataModel = getItem(position); // lấy cái oject
        ViewHolder viewHolder;//cục bộ
        final View result;
        //converVeiw biến quản lyd cho các veiw dẫ xuất hiện hoặc đã tồn tại
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            // khác null
            convertView = inflater.inflate(R.layout.layout_item_sv, parent, false);
            //ánh xạ và gán veiw vào biến của thuộc tính
            viewHolder.txtMasv =  convertView.findViewById(R.id.item_txtmasv);
            viewHolder.txtTensv =  convertView.findViewById(R.id.item_txttensv);
            viewHolder.txtgt=convertView.findViewById(R.id.item_txtgt);
            viewHolder.txtlop=convertView.findViewById(R.id.item_txtlop);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // các biến khác null, có nghĩa là nó đã cấp
        viewHolder.txtMasv.setText(dataModel.getMasv());
        viewHolder.txtTensv.setText(dataModel.getTensv());
        viewHolder.txtgt.setText(dataModel.getGt());
        viewHolder.txtlop.setText(dataModel.getLop());
        return convertView;

    };
}