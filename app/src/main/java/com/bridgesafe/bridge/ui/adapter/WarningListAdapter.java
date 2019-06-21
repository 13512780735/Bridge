package com.bridgesafe.bridge.ui.adapter;

import android.support.annotation.Nullable;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class WarningListAdapter extends BaseQuickAdapter<FalseModel, BaseViewHolder> {
    public WarningListAdapter(int layoutResId, @Nullable List<FalseModel> data) {
        super(R.layout.warning_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FalseModel item) {
        helper.setText(R.id.tv_time, "2019年8月15日 09:30:25");
        helper.setText(R.id.tv_reason, "地基不均匀沉降" + item.getTitle());
        helper.setText(R.id.tv_bridge, "额头湾立交桥" + item.getTitle());

    }
}
