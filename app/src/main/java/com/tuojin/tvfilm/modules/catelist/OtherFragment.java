package com.tuojin.tvfilm.modules.catelist;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: OtherFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 12:57
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class OtherFragment extends BaseFragment<SortListContract.View, SortListPresenterImpl> implements SortListContract.View {
    @BindView(R.id.recyclerview)
    public RecyclerView mRecyclerview;
    private FocusGridLayoutManager mGridLayoutManager;
    private List<FilmBean> mFilmBeen;
    private static CommonAdapter mOtherAdapter;
    private int mSortType;
    private FilmBean mValue;
    private boolean aBoolean = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView() {
        mSortType = getArguments().getInt("sortType", 0);
        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mSortType, 0);

    }

    @Override
    protected SortListPresenterImpl initPresenter() {
        return new SortListPresenterImpl();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mFilmBeen, 1) {
                        @Override
                        public void convert(ViewHolder holder, FilmBean bean) {
                            holder.setText(R.id.movie_title_other, bean.getMovie_name());
                            holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                            holder.setScaleAnimation(R.id.movie_title_other);
                        }
                    };
                    mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                            mValue = mFilmBeen.get(position);
                            intent.putExtra("film", mValue);
                            startActivity(intent);
                        }
                    });
                    mRecyclerview.setAdapter(mOtherAdapter);
                    break;
            }
        }

    };

    @Override
    public void setRecyclerItem(List<FilmBean> mList) {
        mFilmBeen = mList;
//        mHandler.sendEmptyMessage(0);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mFilmBeen, 1) {
                    @Override
                    public void convert(ViewHolder holder, FilmBean bean) {
                        holder.setText(R.id.movie_title_other, bean.getMovie_name());
                        holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                        holder.setScaleAnimation(R.id.movie_title_other);
                    }
                };
                mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                        mValue = mFilmBeen.get(position);
                        intent.putExtra("film", mValue);
                        startActivity(intent);
                    }
                });
                mRecyclerview.setAdapter(mOtherAdapter);
            }
        });
    }

    @Override
    public void refreshUI() {

    }
}
