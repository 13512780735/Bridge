package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.bridgesafe.bridge.ui.adapter.RecordAdapter;
import com.bridgesafe.bridge.ui.adapter.RoadAdapter;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.other.RecordDetailActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 路线
 */
public class RoadActivity extends BaseActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<FalseModel> data;
    private RoadAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
        setBackView();
        initData();
        initUI();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            FalseModel falseModel = new FalseModel();
            falseModel.setTitle("" + i);
            data.add(falseModel);
        }
    }

    private void initUI() {
        setTitle("路线", getResources().getColor(R.color.title_color));
        initAdapter();
    }

    private void initAdapter() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RoadAdapter(R.layout.road_listview_items, data);
        mAdapter.setEnableLoadMore(false);
        // mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String id = data.get(position).getId();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                toActivity(GoodDescActivity.class, bundle);
                toActivity(RoadDetailActivity.class);
            }
        });
    }
//    @Override
//    public void onRefresh() {
//        mAdapter.setEnableLoadMore(false);//禁止加载
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                // mAdapter.setNewData(data);
////                isErr = false;
////                mCurrentCounter = PAGE_SIZE;
////                pageNum = 1;//页数置为1 才能继续重新加载
////                initData(pageNum, false);
//                mAdapter.setEnableLoadMore(false);//启用加载
//            }
//        }, 2000);
//    }
}
