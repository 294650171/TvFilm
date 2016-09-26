package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.bean.DirectListBean;

import java.util.List;

/**
 * 文 件 名: CateListContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 15:42
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CateListContract {
public interface View{
    void initViewRadioButton(List<DirectListBean.DataBean.DirectorBean> directorBeanList);
}

public interface Presenter{
    void initRadioButton(int type);

    void initViewRadioButton(List<DirectListBean.DataBean.DirectorBean> directorBeanList);
}

public interface Model{
    void initRadioButton(int type);
}


}