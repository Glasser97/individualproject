package com.coderglasser.individualproject;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static com.coderglasser.individualproject.DataDao.Properties.Date;

public class SelectActivity extends AppCompatActivity {

    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter=null;
    private ArrayList<Icon> mIcon=null;
    ImageView imageView;
    TextView textView;
    Button outputButton;
    Button inputButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        mContext=SelectActivity.this;
        grid_photo=(GridView) findViewById(R.id.grid_photo);
        //添加Icon进去，总共23个
        mIcon=new ArrayList<Icon>();
        mIcon.add(new Icon(R.drawable.ad,getResources().getString(R.string.guanggao)));
        mIcon.add(new Icon(R.drawable.anjie,getResources().getString(R.string.anjie)));
        mIcon.add(new Icon(R.drawable.baobao,getResources().getString(R.string.baobao)));
        mIcon.add(new Icon(R.drawable.baojian,getResources().getString(R.string.baojian)));
        mIcon.add(new Icon(R.drawable.baoxian,getResources().getString(R.string.baoxian)));
        mIcon.add(new Icon(R.drawable.baoxiao,getResources().getString(R.string.baoxiao)));
        mIcon.add(new Icon(R.drawable.chashuikafei,getResources().getString(R.string.chashuikafei)));
        mIcon.add(new Icon(R.drawable.chuanpiao,getResources().getString(R.string.chuanpiao)));
        mIcon.add(new Icon(R.drawable.daoyou,getResources().getString(R.string.daoyou)));
        mIcon.add(new Icon(R.drawable.dapai,getResources().getString(R.string.dapai)));
        mIcon.add(new Icon(R.drawable.dianfei,getResources().getString(R.string.dianfei)));
        mIcon.add(new Icon(R.drawable.dianying,getResources().getString(R.string.dianying)));
        mIcon.add(new Icon(R.drawable.fangdai,getResources().getString(R.string.fangdai)));
        mIcon.add(new Icon(R.drawable.fangzu,getResources().getString(R.string.fangzu)));
        mIcon.add(new Icon(R.drawable.fanka,getResources().getString(R.string.fanka)));
        mIcon.add(new Icon(R.drawable.feijipiao,getResources().getString(R.string.feijipiao)));
        mIcon.add(new Icon(R.drawable.fuwu,getResources().getString(R.string.fuwu)));
        mIcon.add(new Icon(R.drawable.gonggongqiche,getResources().getString(R.string.gonggongqiche)));
        mIcon.add(new Icon(R.drawable.haiwaidaigou,getResources().getString(R.string.haiwaidaigou)));
        mIcon.add(new Icon(R.drawable.huankuan,getResources().getString(R.string.huankuan)));
        mIcon.add(new Icon(R.drawable.huazhuangpin,getResources().getString(R.string.huazhuangpin)));
        mIcon.add(new Icon(R.drawable.huochepiao,getResources().getString(R.string.huochepiao)));
        mIcon.add(new Icon(R.drawable.huwaishebei,getResources().getString(R.string.huwaishebei)));

        mAdapter = new ImageAdapter(mIcon,mContext);
        grid_photo.setAdapter(mAdapter);
        grid_photo.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int
                            position, long id) {
                        Icon passIcon = mIcon.get(position);
                        //Bundle bundle = new Bundle();
                        //bundle.putInt("image",passIcon.getiId());
                        //bundle.putString("content",passIcon.getiName());
                        LayoutInflater inflater = LayoutInflater.from(SelectActivity.this);
                        View view = inflater.inflate(R.layout.input_window,null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(SelectActivity.this);

                        imageView=(ImageView) view.findViewById(R.id.add_icon);
                        textView=(TextView) view.findViewById(R.id.add_text);
                        inputButton = (Button) view.findViewById(R.id.btn_input);
                        outputButton = (Button) findViewById(R.id.btn_output);
                        textView.setText(passIcon.getiName());
                        imageView.setImageResource(passIcon.getiId());
                        final EditText editText = view.findViewById(R.id.txt_number);
//                        inputButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //计算收入的操作
//                                Toast.makeText(SelectActivity.this,"inputdianji",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        outputButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(SelectActivity.this,"outputdianji",Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        builder.setView(view);
                        builder.setCancelable(true);
                        final AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });
    }

}
