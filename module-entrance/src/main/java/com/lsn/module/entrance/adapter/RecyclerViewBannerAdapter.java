package com.lsn.module.entrance.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pmisy.roomkb.R;
import com.lsn.lib.ui.adapter.recyclerview.BaseRecyclerAdapter;
import com.lsn.lib.ui.adapter.recyclerview.RecyclerViewHolder;
import com.pmisy.roomkb.adapter.KBInjEqAdapter;import com.pmisy.roomkb.adapter.KBInjOrAdapter;import com.pmisy.roomkb.entity.comm.KBEqRoomContentDetailEntity;
import com.pmisy.roomkb.entity.comm.KBOrRoomContentDetailEntity;

import java.util.ArrayList;

/**
 * @Author : lsn
 * @CreateTime : 2023/3/31 下午 04:24
 * @Description :
 */
public class RecyclerViewBannerAdapter extends BaseRecyclerAdapter<String> {

    private Context context;

    private ArrayList<ArrayList<KBEqRoomContentDetailEntity>> data;
    private ArrayList<ArrayList<KBOrRoomContentDetailEntity>> data2;

    public RecyclerViewBannerAdapter() {
        super();
    }

    public RecyclerViewBannerAdapter(ArrayList<String> list,
                                     Context context,
                                     ArrayList<ArrayList<KBEqRoomContentDetailEntity>> data,
                                     ArrayList<ArrayList<KBOrRoomContentDetailEntity>> data2) {
        super(list);
        this.context = context;
        this.data = data;
        this.data2 = data2;
    }


    @Override
    protected int getItemLayoutId(int viewType) {

        return R.layout.banner_item_kanban_eq;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, String item) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        if (data != null) {
            KBInjEqAdapter kanbanEqAdapter = new KBInjEqAdapter();
            RecyclerView rvContent = holder.findViewById(R.id.rvContent);
            if (context != null) {
                rvContent.setLayoutManager(linearLayoutManager);
                rvContent.setAdapter(kanbanEqAdapter);
                kanbanEqAdapter.setData(data.get(position));
            }
        } else if (data2 != null) {
            KBInjOrAdapter kanbanEqAdapter = new KBInjOrAdapter();
            RecyclerView rvContent = holder.findViewById(R.id.rvContent);
            if (context != null) {
                rvContent.setLayoutManager(linearLayoutManager);
                rvContent.setAdapter(kanbanEqAdapter);
                kanbanEqAdapter.setData(data2.get(position));
            }
        }
    }
}
