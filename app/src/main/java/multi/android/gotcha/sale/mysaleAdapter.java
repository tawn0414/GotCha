package multi.android.gotcha.sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import multi.android.gotcha.DB.mysaleVO;
import multi.android.gotcha.R;

public class mysaleAdapter extends ArrayAdapter<mysaleVO> {
    private Context context;
    private List<mysaleVO> list;
    private ListView listView;

    class ViewHolder{
        public TextView mySaleName;
        public Button btnDelete;
    }
    public mysaleAdapter(Context context, List<mysaleVO> list, ListView listView){
        super(context,0,list);
        this.context = context;
        this.list = list;
        this.listView = listView;
    }
    public View getView(int position, View convertView, ViewGroup parentViewGroup) {
        View rowView = convertView;
        ViewHolder viewHolder;
        String Status;

        if (rowView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.mysale_row, parentViewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.mySaleName = rowView.findViewById(R.id.mySaleName);
            viewHolder.btnDelete = rowView.findViewById(R.id.mySaleDelete);

            rowView.setTag(viewHolder);
            Status = "created";
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
            Status = "reused";
        }
        mysaleVO mysaleItems = (mysaleVO)list.get(position);
        viewHolder.mySaleName.setText(mysaleItems.getBrand() + "/" + mysaleItems.getModel() + "  " + mysaleItems.getCarNumber());

        return rowView;
    }
}
