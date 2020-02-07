package hk.edu.cuhk.ie.iems5722.a1_1155132116;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends BaseAdapter {
    private List<Msg> msgList;
    private LayoutInflater mLayoutInflater;
    private  int layout;//item布局
    public MsgAdapter(Context context, int layout, List<Msg> objects){
        this.msgList=objects;
        this.layout=layout;
        this.mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Msg getItem(int i) {
        return msgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View covertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view;
        Msg msg=getItem(i);
        if(covertView==null) {
            view = mLayoutInflater.inflate(R.layout.chatitem, null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout=view.findViewById(R.id.left_layout);
            viewHolder.rightLayout=view.findViewById(R.id.right_Layout);
            viewHolder.leftMsg=view.findViewById(R.id.left_msg);
            viewHolder.leftDate=view.findViewById(R.id.left_date);
            viewHolder.rightMsg=view.findViewById(R.id.right_msg);
            viewHolder.rightDate=view.findViewById(R.id.right_date);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)covertView.getTag();
            view=covertView;
        }
        if(msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
            viewHolder.leftDate.setText(msg.getTime());
        }else if(msg.getType() == Msg.TYPE_SEND){
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
            viewHolder.rightDate.setText(msg.getTime());
        }
        return view;
    }
    class ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        TextView leftDate;
        TextView rightDate;

    }

}
