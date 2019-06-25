package com.bridgesafe.bridge.ui.adapter;

import android.support.annotation.Nullable;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RoadAdapter extends BaseQuickAdapter<FalseModel, BaseViewHolder> {
    public RoadAdapter(int layoutResId, @Nullable List<FalseModel> data) {
        super(R.layout.road_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FalseModel item) {
        helper.setText(R.id.tv_name, "中山港大桥");

    }
}
