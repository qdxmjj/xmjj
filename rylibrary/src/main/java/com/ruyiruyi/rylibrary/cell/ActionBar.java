package com.ruyiruyi.rylibrary.cell;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build.VERSION;

import com.ruyiruyi.rylibrary.R;
import com.ruyiruyi.rylibrary.android.rx.rxbinding.RxViewAction;
import com.ruyiruyi.rylibrary.utils.LayoutHelper;

import rx.functions.Action1;

public class ActionBar extends FrameLayout {

    private  FrameLayout content;
    private  TextView titleView;
    private ImageView backButtonImageView;
    private TextView titleTextView;
    private TextView subTitleTextView;
    private View actionModeTop;
 //   private ActionBarMenu menu;
   // private ActionBarMenu actionMode;
    private boolean occupyStatusBar;
    private boolean allowOverlayTitle;
    private CharSequence lastTitle;
    private boolean castShadows;
    protected boolean isSearchFieldVisible;
    protected int itemsBackgroundResourceId;
    private boolean isBackOverlayVisible;
    public ActionBar.ActionBarMenuOnItemClick actionBarMenuOnItemClick;
    private int extraHeight;
    private static Paint paint;
    private boolean needDivider;
    private boolean isSmallStyle;

    public void setActionBarMenuOnItemClick(ActionBar.ActionBarMenuOnItemClick var1){
        this.actionBarMenuOnItemClick = var1;
    }

    public void onBackItemClick(){
        if (this.actionBarMenuOnItemClick != null){
            this.actionBarMenuOnItemClick.onItemClick(-1);
        }
    }


    public ActionBar(Context context) {
        super(context);
        initView(context);
    }

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        content = new FrameLayout(context);
        content.setBackgroundColor(0xfffc6421);
        addView(content,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,130));

        backButtonImageView = new ImageView(context);
        backButtonImageView.setScaleType(ImageView.ScaleType.CENTER);
        backButtonImageView.setImageResource(R.drawable.ic_back);
        content.addView(backButtonImageView, LayoutHelper.createFrame(60,60,Gravity.CENTER_VERTICAL,20,0,0,0));

        RxViewAction.clickNoDouble(backButtonImageView)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ActionBar.this.actionBarMenuOnItemClick.onItemClick(-1);
                    }
                });

        titleView = new TextView(context);
        titleView.setTextSize(20);
        titleView.setText("AA");
        titleView.setTextColor(Color.WHITE);
        content.addView(titleView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER));
    }

    public void setBackground(int color){
        content.setBackgroundColor(color);
    }

    public void setTitle(String title){
        titleView.setText(title);
    }
    public void setShowBackView(boolean isShow){
        if (isShow){
            backButtonImageView.setVisibility(VISIBLE);
        }else {
            backButtonImageView.setVisibility(GONE);
        }

    }



    public static class ActionBarMenuOnItemClick{
        public ActionBarMenuOnItemClick(){

        }
        public void onItemClick(int var1){}
    }




}