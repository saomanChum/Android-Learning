package hk.edu.cuhk.ie.iems5722.a1_1155132116;

import android.app.ActionBar;
import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity{
    private ListView msgListView;
    private EditText inputText;
    private Toolbar toolbar;
    private ImageButton imageButton;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList=new ArrayList<Msg>();
    private SimpleDateFormat df=new SimpleDateFormat("hh:mm", Locale.CHINA);
    ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMsg();
        requestWindowFeature(Window.FEATURE_NO_TITLE);//软件全屏显示、自定义标题（使用按钮）和其他需求
        setContentView(R.layout.chatactivity);

        toolbar=(Toolbar)findViewById(R.id.toolbar_chat);
        msgAdapter = new MsgAdapter(ChatActivity.this, R.layout.chatitem, msgList);
        inputText=(EditText)findViewById(R.id.input_text);
        imageButton=(ImageButton)findViewById(R.id.send);
        msgListView=(ListView)findViewById(R.id.chat_list);
        msgListView.setAdapter(msgAdapter);

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    Msg msg=new Msg(content,Msg.TYPE_SEND, df.format(new Date()));
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initMsg(){
        Msg msg1=new Msg("I miss u.", Msg.TYPE_SEND, df.format(new Date()));
        msgList.add(msg1);
        Msg msg2=new Msg("I miss u too.", Msg.TYPE_RECEIVED, df.format(new Date()));
        msgList.add(msg2);

    }
}
