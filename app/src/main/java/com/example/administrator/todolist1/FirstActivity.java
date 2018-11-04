package com.example.administrator.todolist1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FirstActivity extends AppCompatActivity {
    public MyAdapter adapter1 ;//变量放这里声明！！！但是在这里是不能实例化的；
    public ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        adapter1 = new MyAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list_item);//在主视图里寻找view并初始化listView实例；
        listView.setAdapter(adapter1);



        Button button_add = (Button) findViewById(R.id.button_add);//在主界面中不用写converView.findVById
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.add("");
                adapter1.notifyDataSetChanged();//强制执行getView刷新界面；
            }
        });

        listView.setAdapter(adapter1);

    }

    private class MyAdapter extends BaseAdapter{//自定义适配器
        //final MyAdapter adapter1 = new MyAdapter (FirstActivity.this);
        private Context context;
        private LayoutInflater inflater;


        public MyAdapter(Context context){
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);//用变量inflater来存放我们传入的布局；
            arr = new ArrayList<>();//在Myadapter中初始化arr这个数组
            for(int i = 0;i < 1;i++){
                arr.add("");
            }
        }


        @Override
        public int getCount() {
            return  arr.size();//重写了这些方法，就可以出现框框了！
        }

        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg1) {//如果不重写这些方法，AS就会要求我把Myadapter声明为抽象类
            return arg1;
        }//必须把这些主要方法重写了才不会报错；

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {//重写了getview方法后类的声明报错才消失
            if(convertView == null){
                convertView = inflater.inflate(R.layout.todo_item,null);}//提高ListView的工作效率，传入子项布局todo_item;!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            final EditText editText = (EditText) convertView.findViewById(R.id.edit_item);//这里为什么要用final修饰第二次创建editText实例
            Button button_del = (Button) convertView.findViewById(R.id.button_del);//调用converView的findViewById()；方法刷新视图；果然还是要去子项里面去找！如果不加converView就会返回一个空的button_del对象；
            if(position >= 0)
            editText.setText(arr.get(position));//对这个方法不是很了解；!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if(arr.size()>0&&position<=arr.size()-1){
                        arr.set(position,editText.getText().toString());

                    }
                }
        });



            button_del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    arr.set(position,"");
                    arr.remove(position);
                    adapter1.notifyDataSetChanged();
                }
            });

        return convertView;
        }
    }








}
