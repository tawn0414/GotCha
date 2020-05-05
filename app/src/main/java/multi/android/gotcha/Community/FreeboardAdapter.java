package multi.android.gotcha.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.gotcha.DB.CommunityVO;
import multi.android.gotcha.R;

public class FreeboardAdapter extends RecyclerView.Adapter<FreeboardAdapter.Viewholder>{
    Context context;
    int resId;
    List<CommunityVO> data;
    List<CommunityVO> Filtereddata;
    OnItemClickListener mListener = null ;
    View view;
    //Intent intent = get;
    /*페이징 시작*/

    /*페이징 끝*/
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public FreeboardAdapter(Context context, int resId, List<CommunityVO> data) {
        this.context = context;
        this.resId = resId;
        this.data = data;
        this.Filtereddata = data;
    }

    public List<CommunityVO> getData() {
        return data;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Log.d("msg",data.size()+"이게 data의 사이즈입니다.");
        view = LayoutInflater.from(context).inflate(resId,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        TextView row_title = holder.title;
        TextView row_writer = holder.writer;
        TextView row_date = holder.date;
        TextView row_hit = holder.hitnum;
        TextView row_comment = holder.commentnum;
        row_title.setText(data.get(position).getBoard_TITLE());
        row_writer.setText(data.get(position).getMem_NICKNAME());
        row_date.setText(data.get(position).getBoard_REGDATE());
        row_hit.setText(Integer.toString(data.get(position).getBoard_HIT()));
        row_comment.setText(Integer.toString(data.get(position).getReplynum()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView title;
        TextView writer;
        TextView date;
        TextView hitnum;
        TextView commentnum;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            writer = itemView.findViewById(R.id.writer);
            date = itemView.findViewById(R.id.date);
            hitnum = itemView.findViewById(R.id.hitnum);
            commentnum = itemView.findViewById(R.id.commentnum);

            //한 row를 눌렀을 때 상세보기
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();//한 row의 위치값을 받아옴
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                    }
                });
            }
        }
}




