package com.ruyiruyi.ruyiruyi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ruyiruyi.ruyiruyi.R;
import com.ruyiruyi.ruyiruyi.db.DbConfig;
import com.ruyiruyi.ruyiruyi.db.model.User;
import com.ruyiruyi.ruyiruyi.ui.activity.CarManagerActivity;
import com.ruyiruyi.ruyiruyi.utils.UIOpenHelper;
import com.ruyiruyi.rylibrary.android.rx.rxbinding.RxViewAction;
import com.ruyiruyi.rylibrary.utils.glide.GlideCircleTransform;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import rx.functions.Action1;

public class MyFragment extends Fragment {

    private TextView myButton;
    private TextView nologin;
    private ImageView myImage;
    private TextView userNameView;
    private LinearLayout userInfoLayout;
    private LinearLayout noLoginLayout;
    private Boolean isLogin;
    private LinearLayout shezhiLayout;
    private RequestManager glideRequest;
    private LinearLayout myCarLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        if (isLogin){
            initDataFromDb();
        }

      /*  myButton = (TextView) getView().findViewById(R.id.my_button);
        nologin = (TextView)  getView().findViewById(R.id.my_nologin);

        RxViewAction.clickNoDouble(myButton)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        UIOpenHelper.openLogin(getContext());
                    }
                });
        RxViewAction.clickNoDouble(nologin)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        DbConfig dbConfig = new DbConfig();
                        User user = dbConfig.getUser();
                        user.setIsLogin("0");
                        DbManager db = dbConfig.getDbManager();

                        try {
                            db.saveOrUpdate(user);
                        } catch (DbException e) {

                        }
                        UIOpenHelper.openLogin(getContext());
                        initView();

                    }
                });


*/


    }

    private void initDataFromDb() {
        DbConfig dbConfig = new DbConfig();
        User user = dbConfig.getUser();
        String headimgurl = user.getHeadimgurl();
        String userName = user.getNick();
        glideRequest = Glide.with(this);
        glideRequest.load(headimgurl).transform(new GlideCircleTransform(getContext())).into(myImage);
        userNameView.setText(userName);



    }

    private void initView() {
        myImage = (ImageView) getView().findViewById(R.id.my_touxiang);
        userNameView = (TextView) getView().findViewById(R.id.my_username);
        userInfoLayout = ((LinearLayout) getView().findViewById(R.id.user_info_layout));
        noLoginLayout = ((LinearLayout) getView().findViewById(R.id.no_login_layout));
        shezhiLayout = (LinearLayout) getView().findViewById(R.id.shezhi_layout);
        myCarLayout = ((LinearLayout) getView().findViewById(R.id.my_car_layout));


        DbConfig dbConfig = new DbConfig();
        isLogin = dbConfig.getIsLogin();
        userInfoLayout.setVisibility(isLogin ? View.VISIBLE:View.GONE);
        noLoginLayout.setVisibility(isLogin ? View.GONE : View.VISIBLE);
        RxViewAction.clickNoDouble(noLoginLayout)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        UIOpenHelper.openLogin(getContext());
                    }
                });
        RxViewAction.clickNoDouble(shezhiLayout)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (isLogin){
                            DbConfig dbConfig = new DbConfig();
                            User user = dbConfig.getUser();
                            user.setIsLogin("0");
                            DbManager db = dbConfig.getDbManager();

                            try {
                                db.saveOrUpdate(user);
                            } catch (DbException e) {

                            }
                            initView();
                        }

                        UIOpenHelper.openLogin(getContext());

                    }
                });
        RxViewAction.clickNoDouble(myCarLayout)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(getContext(), CarManagerActivity.class));
                    }
                });

      /*  DbConfig dbConfig = new DbConfig();
        User user = dbConfig.getUser();
        if (user == null){
            myButton.setText("未登录");
            myButton.setClickable(true);
            nologin.setVisibility(View.GONE);
        }else {
            myButton.setText("已登陆");
            myButton.setClickable(false);
            nologin.setVisibility(View.VISIBLE);
        }*/
    }


}