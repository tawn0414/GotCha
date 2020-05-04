package multi.android.gotcha.sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import multi.android.gotcha.DB.colorItem;
import multi.android.gotcha.R;

public class colorAdapter extends ArrayAdapter<colorItem> {

    private Context context;
    private List list;
    private ListView listView;

    class ViewHolder{
        public LinearLayout linearLayout;
        public TextView colorName;
    }

    public colorAdapter(Context context, List<colorItem> list, ListView listView){
        super(context,0,list);
        this.context = context;
        this.list = list;
        this.listView = listView;
    }
    public View getView(int position, View convertView, ViewGroup parentViewGroup){
            View rowView = convertView;
            ViewHolder viewHolder;
            String Status;

            if (rowView == null) {
                // 레이아웃을 정의한 XML 파일(R.layout.list_item)을 읽어서 계층 구조의 뷰 객체(rowView)로 변환합니다.
                // rowView 객체는 3개의 TextView로 구성되어 있습니다.
                //
                // 다음 한줄로 구현도 가능합니다.
                // rowView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parentViewGroup, false);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                rowView = layoutInflater.inflate(R.layout.color_row, parentViewGroup, false);
                // view holder의 구성 요소의 값과 한 줄을 구성하는 레이아웃을 연결함.
                //
                // rowView(=R.layout.list_item)에서 주어진 ID(R.id.textview_list_english)를 가진 뷰를 찾습니다.
                // 찾는 뷰의 타입에 따라 findViewById 리턴 결과를 타입 변환해줘야 합니다.
                viewHolder = new ViewHolder();

                viewHolder.linearLayout = (LinearLayout) rowView.findViewById(R.id.colorplace);
                viewHolder.colorName = (TextView) rowView.findViewById(R.id.colorName);

                rowView.setTag(viewHolder);
                Status = "created";
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
                Status = "reused";
            }
            colorItem colorItem = (colorItem) list.get(position);
            viewHolder.linearLayout.setBackgroundColor(colorItem.getColor());
            viewHolder.colorName.setText(colorItem.getName());

            return rowView;
    }
}
