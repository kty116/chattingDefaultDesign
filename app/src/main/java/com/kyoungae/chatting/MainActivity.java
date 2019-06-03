package com.kyoungae.chatting;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyoungae.chatting.adapter.ChattingAdapter;
import com.kyoungae.chatting.databinding.ActivityMainBinding;
import com.kyoungae.chatting.lib.recycler_view.HeaderAndFooterRecyclerViewAdapter;
import com.kyoungae.chatting.model.ChatModel;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLayoutChangeListener {

    private ChattingAdapter mChattingAdapter;
    private RequestOptions mRequestOptions;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private ActivityMainBinding binding;
    private ArrayList<ChatModel> mChatModels;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mChatModels = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ChatModel chatModel = new ChatModel("https://img.insight.co.kr/static/2019/01/21/700/8wps1552c4j3o4q91jn7.jpg", "노지수 기사님", "안녕하세요");
            mChatModels.add(chatModel);
        }

        mChattingAdapter = new ChattingAdapter(mChatModels, this);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mChattingAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        binding.recyclerView.scrollToPosition(mChattingAdapter.getItemCount() - 1);

        binding.sendButton.setOnClickListener(this);
        binding.closeButton.setOnClickListener(this);
        binding.chattingRoomName.setText("채팅방");

        binding.parentLayout.getHeight();
        binding.parentLayout.addOnLayoutChangeListener(this);

//        RecyclerViewUtils.setHeaderView(binding.recyclerView, new DriverVehicleSelectListHeaderView(getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                String sendMessage = binding.messageContent.getText().toString();
                if (sendMessage != null && !sendMessage.isEmpty()) {
                    binding.messageContent.setText("");
                    ChatModel chatModel = new ChatModel(sendMessage);
                    mChattingAdapter.addData(chatModel);
                    binding.recyclerView.scrollToPosition(mChattingAdapter.getItemCount() - 1);
                }
                break;

            case R.id.closeButton:
                finish();
                break;


        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (bottom < oldBottom) {
            Log.d("Dddd", "onLayoutChange: bottom < oldBottom");
            binding.recyclerView.scrollToPosition(mChattingAdapter.getItemCount() - 1);
        }
    }
}
