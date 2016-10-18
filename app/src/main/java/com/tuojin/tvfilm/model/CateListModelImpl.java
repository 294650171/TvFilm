package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.contract.CateListContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.CateListPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
 * Created by MVPHelper on 2016/09/22
 */

public class CateListModelImpl implements CateListContract.Model, Constant {

    CateListPresenterImpl mPresenter;

    public CateListModelImpl(CateListPresenterImpl presenter) {
        mPresenter = presenter;
    }
    //    获取年份列表（GETYEARLIST）
//    A.接口定义
//    承载协议：websocket协议
//    接口方向：点播应用–〉播出服务
//    发送信息：PAD1465889962927|getYearList|startIndex=0&endIndex=27

    //    PAD1465889962927|getDoctorList|startIndex=0&endIndex=20

    /**
     *
     * @param type 0-导演
     */
    @Override
    public void initRadioButton(int type) {
        TvFilmNetWorkWS filmNetWorkWS = new TvFilmNetWorkWS();
        switch (type) {
            case 0:
                filmNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getDoctorList|startIndex=0&endIndex=20", new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        DirectListBean directListBean = new Gson().fromJson(data, DirectListBean.class);
                        List<DirectorBean> directorBeanList = directListBean.getData().getData();
                        mPresenter.initViewRadioButton(directorBeanList);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {

                    }
                });
                break;

        }



    }

}