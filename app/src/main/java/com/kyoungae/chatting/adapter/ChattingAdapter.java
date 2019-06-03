package com.kyoungae.chatting.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kyoungae.chatting.R;
import com.kyoungae.chatting.databinding.ListItemChattingBinding;
import com.kyoungae.chatting.model.ChatModel;

import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter {
    public static final String TAG = ChattingAdapter.class.getSimpleName();
    private LayoutInflater mLayoutInflater;
    private ArrayList<ChatModel> mChatModels;
    private Activity activity;
    private long mLastButtonClickTime;
    private RequestOptions mRequestOptions;

    public ChattingAdapter(ArrayList<ChatModel> chatModels, Activity activity) {
        this.activity = activity;
        mChatModels = chatModels;
        mLayoutInflater = LayoutInflater.from(activity);
        initData();

    }

    public void setData(ArrayList<ChatModel> chatModels) {
        mChatModels = chatModels;

        notifyDataSetChanged();
    }

    public void addData(ChatModel chatModel) {
        mChatModels.add(chatModel);
        notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    private void initData() {

        mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.drawable.ic_default_image);
        mRequestOptions.error(R.drawable.ic_default_image);
        mRequestOptions.override(100);
        mRequestOptions.centerCrop();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_chatting, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;

        ChatModel chatModel = mChatModels.get(position);

        if (chatModel != null) {

            if (chatModel.getProfileImage() != null) {

                viewHolder.listBinding.myLayout.setVisibility(View.GONE);
                viewHolder.listBinding.otherLayout.setVisibility(View.VISIBLE);

                Glide.with(activity).asBitmap().load(chatModel.getProfileImage()).apply(mRequestOptions).into(viewHolder.listBinding.otherProfileImage);

                Log.d(TAG, "onBindViewHolder: " + chatModel.getProfileImage());
                viewHolder.listBinding.otherName.setText(chatModel.getNicName());
                viewHolder.listBinding.otherMessage.setText(chatModel.getMessage());
            } else {
                viewHolder.listBinding.myLayout.setVisibility(View.VISIBLE);
                viewHolder.listBinding.otherLayout.setVisibility(View.GONE);

                viewHolder.listBinding.myMessage.setText(chatModel.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mChatModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ListItemChattingBinding listBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            listBinding = DataBindingUtil.bind(itemView);
        }
    }

}
