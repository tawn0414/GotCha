package multi.android.gotcha.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.gotcha.DB.ReplyVO;
import multi.android.gotcha.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Viewholder> {
    Context context;
    int resId;
    List<ReplyVO> data;


    public CommentAdapter(Context context, int resId, List<ReplyVO> data) {
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        TextView row_writer = holder.writer;
        TextView row_date = holder.date;
        TextView row_comment = holder.comment;
        row_writer.setText(data.get(position).getmEM_NICKNAME());
        row_date.setText(data.get(position).getrEPLY_REGDATE());
        row_comment.setText(data.get(position).getrEPLY_CONTENT());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView writer;
        TextView date;
        TextView comment;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            writer = itemView.findViewById(R.id.commentWriter);
            date = itemView.findViewById(R.id.commentDate);
            comment = itemView.findViewById(R.id.commentContent);
        }

    }
}




