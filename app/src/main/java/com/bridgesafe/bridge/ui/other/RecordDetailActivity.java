package com.bridgesafe.bridge.ui.other;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.bridgesafe.bridge.ui.adapter.RecordAdapter;
import com.bridgesafe.bridge.ui.adapter.RecordDetailAdapter;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class RecordDetailActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tv_ship_name)//船名字
            TextView mTvShipName;
    @BindView(R.id.tv_time)//时间
            TextView mTvTime;
    @BindView(R.id.tv_routes)//航速
            TextView mTvShipRoutes;
    @BindView(R.id.tv_course)//航向
            TextView mTvShipCourse;
    @BindView(R.id.tv_wind_direction)//风向
            TextView mTvWindDirection;
    @BindView(R.id.tv_speed)//风速
            TextView mTvSpeed;
    @BindView(R.id.tv_meteorological)//气象
            TextView mTvMeteorological;
    @BindView(R.id.tv_water)//水位
            TextView mTvWater;

    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private ArrayList<FalseModel> data;
    private RecordDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        setBackView();
        initData();
        initUI();
    }

    private void initUI() {
        setTitle("航行详情", getResources().getColor(R.color.title_color));
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        initAdapter();
    }

    private void initAdapter() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecordDetailAdapter(R.layout.record_detail_listview_items, data);
        mAdapter.setEnableLoadMore(false);
        // mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.disableLoadMoreIfNotFullPage();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String id = data.get(position).getId();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                toActivity(GoodDescActivity.class, bundle);
              //  toActivity(RecordDetailActivity.class);
            }
        });
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FalseModel falseModel = new FalseModel();
            falseModel.setTitle("" + i);
            data.add(falseModel);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    //数据全部加载完毕
//                    mAdapter.loadMoreEnd();
//                } else {
//                    if (isErr) {
//                        //成功获取更多数据
//                        //  mQuickAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
//                        pageNum += 1;
//                        initData(pageNum, true);
//                        mCurrentCounter = mAdapter.getData().size();
//                        mAdapter.loadMoreComplete();
//                    } else {
//                        //获取更多数据失败
//                        isErr = true;
//                        mAdapter.loadMoreFail();
//
//                    }
//                }
                mAdapter.loadMoreEnd();
                mAdapter.loadMoreComplete();
            }

        }, 3000);
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);//禁止加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                // mAdapter.setNewData(data);
//                isErr = false;
//                mCurrentCounter = PAGE_SIZE;
//                pageNum = 1;//页数置为1 才能继续重新加载
//                initData(pageNum, false);
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(false);//启用加载
            }
        }, 2000);
    }
}
