package com.bridgesafe.bridge.ui.adapter;

import android.support.annotation.Nullable;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.network.model.FalseModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RecordDetailAdapter extends BaseQuickAdapter<FalseModel,BaseViewHolder> {
    public RecordDetailAdapter(int layoutResId, @Nullable List<FalseModel> data) {
        super(R.layout.record_detail_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FalseModel item) {
        helper.setText(R.id.tv_bridge_name,"中山港大桥");
        helper.setText(R.id.tv_bridge_code,"G1101001231");
    }
}
