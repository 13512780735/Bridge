package com.bridgesafe.bridge.ui.adapter;

import android.support.annotation.Nullable;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<FalseModel, BaseViewHolder> {
    public RecordAdapter(int layoutResId, @Nullable List<FalseModel> data) {
        super(R.layout.record_list_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FalseModel item) {
        helper.setText(R.id.tv_from, "中山港码头");
        helper.setText(R.id.tv_end, "深圳宝安");
        helper.setText(R.id.tv_time, "2019-05-05");

    }
}
