package com.bridgesafe.bridge.ui.main;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgesafe.bridge.R;

public class BridgeDetailDialogFragment extends DialogFragment {
    private TextView tv_details;
    private ImageView iv_close;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().setCanceledOnTouchOutside(true);
        //getDialog().setCancelable(true);
        View view = inflater.inflate(R.layout.fragment_bridge_detail_dialog, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        tv_details = view.findViewById(R.id.tv_details);
        iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
