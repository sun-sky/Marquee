package com.sunsky.marqueeviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamlive.upmarqueeviewdemo.R;
import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：仿淘宝首页的 淘宝头条滚动的自定义View
 * 双行
 * 创建人：sunsky
 * 创建时间：2017/6/1 17:47
 * 修改人：sunsky
 * 修改时间：2017/6/1 17:47
 * 修改备注：
 */
public class MainActivity extends AppCompatActivity {


    private MarqueeView marqueeView1;
    private MarqueeView marqueeView2;
    List<String> data = new ArrayList<>();
    List<View> views1 = new ArrayList<>();
    List<View> views2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initdata();
        initView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        marqueeView1.startFlipping();
        marqueeView2.startFlipping();
    }

    @Override
    protected void onStop() {
        super.onStop();
        marqueeView1.stopFlipping();
        marqueeView2.stopFlipping();
    }

    /**
     * 实例化控件
     */
    private void initParam() {
        marqueeView1 = (MarqueeView) findViewById(R.id.upview1);
        marqueeView2 = (MarqueeView) findViewById(R.id.upview2);
    }

    /**
     * 初始化界面程序
     */
    private void initView() {
        setViewTwoLines();
        setViewSingleLine();
        marqueeView1.setViews(views1);
        marqueeView2.setViews(views2);
//        /**
//         * 设置item_view的监听
//         */
//        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view) {
//                Toast.makeText(MainActivity.this, "你点击了第几个items" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     * 条数为奇数时做了些处理：奇数时最后一个没有内容 将第一个拼接到最后显示
     */
    private void setViewTwoLines() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.size() > position + 1) {
                        Toast.makeText(getApplicationContext(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), position + "你点击了" + data.get(0).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.setText(data.get(0).toString());
            }

            //添加到循环滚动数组里面去
            views1.add(moreView);
        }
    }


    /**
     * 自定义布局——单行滚动
     */
    private void setViewSingleLine() {
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view_single, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });

            //进行对控件赋值
            tv1.setText(data.get(i).toString());

            //添加到循环滚动数组里面去
            views2.add(moreView);
        }
    }


    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
//        data.add("阿里HotFix2.0升级详解 畅谈热修复领域那些事");

    }
}
