package com.coderglasser.individualproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,TheSum.OnFragmentInteractionListener {
    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    public String[] months;
    private Context mContext = null;
    public double input=0;
    public double output=0;
    public double rest=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //连接到数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"Record_db",null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        DataDao dataDao = daoSession.getDataDao();
        //连接数据库完成

        //设定工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //悬浮小圆球
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SelectActivity.class);
                startActivityForResult(intent,1);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ListView 和 MyAdapter 线性显示账单

        mContext = MainActivity.this;
        bindViews();
        //倒序从数据库中取值
        mData = dataDao.queryBuilder().orderDesc(DataDao.Properties.Id).list();
        mAdapter = new MyAdapter((List<Data>) mData,mContext);
        //往listAdapter中填充数据库数据
        list_one.setAdapter(mAdapter);
        list_one.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 0, 0, R.string.remove);
            }
        });

        //the sum fragment add
        TheSum theSum = new TheSum();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.the_sum_container,theSum);
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            Date date = new Date(bundle.getLong("dataId"));
            Data data = new Data(bundle.getLong("dataId"),bundle.getInt("image"),bundle.getString("content"),
                    bundle.getString("mount"),date);
            //连接到数据库
            DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"Record_db",null);
            DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
            DaoSession daoSession = daoMaster.newSession();
            DataDao dataDao = daoSession.getDataDao();
            //连接数据库完成
            dataDao.insert(data);
            mAdapter.add(data);
//            double inputChange = 0;
//            double outputChange = 0;
//            String changeMount = data.getMount();
//            double change = parseDouble(changeMount);
//            if (change<=0){
//                //这是支出
//                outputChange = change;
//            }else{
//                //这是收入
//                inputChange = change;
//            }
            TheSum mTheSum =  new TheSum();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.the_sum_container, mTheSum).commitAllowingStateLoss();

        }else{
            Log.d("没有get到Intent",resultCode+"");
        }
        //得到新Activity 关闭后返回的数据
    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_one);
    }

    //select and remove context
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                //连接到数据库
                DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"Record_db",null);
                DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
                DaoSession daoSession = daoMaster.newSession();
                DataDao dataDao = daoSession.getDataDao();
                //连接数据库完成
                dataDao.delete(mAdapter.getItem(menuInfo.position));
                mAdapter.remove(menuInfo.position);
                TheSum theSum = new TheSum();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.the_sum_container,theSum);
                ft.commit();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //optiion选择菜单添加
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"This is DrawableLayout",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this,"This is DrawableLayout",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,"This is DrawableLayout",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"This is DrawableLayout",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this,"This is DrawableLayout",Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Bundle bundle){
        input = bundle.getDouble("input");
        output = bundle.getDouble("output");
        rest = bundle.getDouble("rest");
    }

}
