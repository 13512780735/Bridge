package com.bridgesafe.bridge.ui.other;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.bridgesafe.bridge.ui.adapter.WarningListAdapter;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.util.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 桥梁监控预警
 */
public class WarningActivity extends BaseActivity {

    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    private WarningListAdapter mAdapter;
    private ArrayList<FalseModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        int color = getResources().getColor(R.color.theme_bg_tex);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
        setBackView();
        initData();
        initUI();
    }

    private void initUI() {
        setTitle("桥梁监控预警", getResources().getColor(R.color.black));
        titleBar.setBackgroundColor(getResources().getColor(R.color.theme_bg_tex));
        //mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        initAdapter();
    }
    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WarningListAdapter(R.layout.warning_listview_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.disableLoadMoreIfNotFullPage();
        // mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String id = data.get(position).getId();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                toActivity(GoodDescActivity.class, bundle);
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
}
