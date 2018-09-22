package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */
public class BuyResultActvivity extends BaseActivity {
    @BindView(R.id.btn_buy)
    TextView btnBuy;
    @BindView(R.id.rl_success)
    RelativeLayout rlSuccess;
    @BindView(R.id.rl_false)
    RelativeLayout rlFalse;

    @BindView(R.id.header_left)
    ImageButton left;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_buy_results);
    }

    @Override
    protected void initData() {


        showNormeBar();

        Intent intent = getIntent();
        int end = intent.getIntExtra("END", -1);
        if (end == 0) {
            rlSuccess.setVisibility(View.VISIBLE);
            rlFalse.setVisibility(View.GONE);
            setTile(getResources().getString(R.string.buy_result));
        } else {
            rlFalse.setVisibility(View.VISIBLE);
            rlSuccess.setVisibility(View.GONE);
            setTile(getResources().getString(R.string.buy_result_false));
        }


    }


    @OnClick({R.id.btn_buy,R.id.header_left})
    public void onViewClicked() {
//        /**/finish(BuyResultActvivity.this);
        change(BuyActivity.class,BuyResultActvivity.this,new Intent(),true);

    }


//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//            change(BuyActivity.class,BuyResultActvivity.this,new Intent(),true);
//        }
//        return false;
//
//    }


}
