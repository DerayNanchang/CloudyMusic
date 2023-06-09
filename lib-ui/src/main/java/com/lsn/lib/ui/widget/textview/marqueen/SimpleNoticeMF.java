package com.lsn.lib.ui.widget.textview.marqueen;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.lsn.lib.ui.R;

/**
 * 简单字幕
 */
public class SimpleNoticeMF extends MarqueeFactory<TextView, String> {
    private LayoutInflater inflater;

    public SimpleNoticeMF(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TextView generateMarqueeItemView(String data) {
        TextView view = (TextView) inflater.inflate(R.layout.marqueen_layout_notice_item, null);
        view.setText(data);
        return view;
    }
}