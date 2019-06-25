package com.bridgesafe.bridge.ui.adapter;

import android.support.annotation.Nullable;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RoadDetailsAdapter extends BaseQuickAdapter<FalseModel, BaseViewHolder> {
    public RoadDetailsAdapter(int layoutResId, @Nullable List<FalseModel> data) {
        super(R.layout.road_details_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FalseModel item) {
        helper.setText(R.id.tv_ship_name, "中山港大桥");
        helper.setText(R.id.tv_ship_code, "G1101001231");
    }
}
