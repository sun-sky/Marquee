# Marquee-master
仿淘宝首页的淘宝头条垂直滚动
滚动布局继承了是ViewFlipper,可以实现进入动画，退出动画，设置切换时间间隔。
MarqueeView可用于多行或单行滚动，滚动的布局可以自定义。

### 效果图  
<img src="/screenshots/marqueeview.gif" style="width: 30%;">


### 使用介绍

#### 1、添加依赖<br>
##### Step 1. Add it in your root build.gradle at the end of repositories:

```
    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

##### Step 2. Add the dependency

```
    dependencies {
	          compile 'com.github.sun-sky:Marquee:1.0.0'
	}
```
#### 2、在布局文件中添加MarqueeView，可以设置自定义属性<br>

**xml文件**

```xml
 <com.sunsky.marqueeview.MarqueeView
        android:id="@+id/upview1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:padding="10dp"
        app:animDuration="1000"
        app:interval="5000"
        app:isSetAlphaAnim="true" />
```

**Attributes:**

|attr属性|description 描述|default vuale 默认值|
|:---|:---|:---|
|isSetAlphaAnim|是否显示淡入淡出动画|true|
|interval|切换间隔时间|5000（5秒）|
|animDuration|动画时间|2000（2秒）|

#### 3、Activity中使用<br>

**控件初始化**

```java
  marqueeView1 = (MarqueeView) findViewById(R.id.upview1);
  initdata();
  setViewSingleLine();
  marqueeView1.setViews(views1);

```

**初始化数据**

```java
    private void initdata() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
        data.add("阿里HotFix2.0升级详解 畅谈热修复领域那些事");

    }
```


**初始化view队列**

```java
    /**
     * 自定义布局——单行滚动
     */
    private void setViewSingleLine() {
     views2.clear();//记得加这句话，不然可能会产生重影现象
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

```

#### 4、重影问题可参考以下解决方案<br>
```java
  @Override
    public void onResume() {
        super.onResume();
        marqueeView1.startFlipping();
    }

    @Override
    public void onPause() {
        super.onPause();
        marqueeView1.stopFlipping();
    }
```


# Thanks
该项目参考了：

* [https://github.com/dreamlivemeng/UpMarqueeTextView-master](https://github.com/dreamlivemeng/UpMarqueeTextView-master)

# 关于我
#### [我的Github](https://github.com/sun-sky)
#### [我的简书](http://www.jianshu.com/u/562d62d52334)
#### ⊙开源不易，希望给个star或者fork奖励