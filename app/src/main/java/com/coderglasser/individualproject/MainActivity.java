package com.coderglasser.individualproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ListView;
import android.widget.Toast;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    //flag用来调试
    public int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //悬浮小圆球
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Date date = new Date();
//                mAdapter.add(new Data(date.getTime(),R.drawable.dianying,"电影","$"+flag,date));
//                flag++;
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
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(mAdapter);
        list_one.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 0, 0, R.string.remove);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            Date date = new Date(bundle.getLong("dataId"));
            mAdapter.add(new Data(bundle.getLong("dataId"),bundle.getInt("image"),bundle.getString("content"),
                    bundle.getString("mount"),date));
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
                mAdapter.remove(menuInfo.position);
                //DataDao.deleteByKey(menuInfo.position);
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



}
