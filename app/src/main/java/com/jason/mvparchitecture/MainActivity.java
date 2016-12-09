package com.jason.mvparchitecture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jason.mvplib.view.activity.MVPActivity;
import com.jason.mvplib.view.iview.IView;

/**
 * https://github.com/jasonim
 * ----------view截获事件通过presenter的接口传递事件给presenter处理
 * ----------presenter通过view的接口传递数据给view显示或反馈
 */
public class MainActivity extends MVPActivity<MainActivity, HelloPresenter> implements IView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        final TextView textview = (TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString().trim();
                presenter.submit();//
//                presenter.isViewAttached();
                //当 presenter 想要调用 view 的方法是， 我们需要判断 isViewPresenter()
                // 并使用 getView()来获取引用，以坚持view是否连接当了 presenter。
            }
        });
    }

    /**
     * Presenter与View一起创建, 并绑定View与Model的引用. 通过View接口, 在构造器中, 创建View与Presenter的相互引用.
     */
    @Override
    public HelloPresenter createPresenter() {
        return new HelloPresenter();
    }
}
