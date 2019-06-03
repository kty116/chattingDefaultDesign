package com.kyoungae.chatting.lib.recycler_view;

import android.support.v7.app.AppCompatActivity;

public class EndlessLinearLayoutActivity extends AppCompatActivity {

//    /**총 갯수*/
//    private static final int TOTAL_COUNTER = 64;
//
//    /**불러오는 데이터 갯수*/
//    private static final int REQUEST_COUNT = 10;
//
//    /**현재 데이터 갯수*/
//    private int mCurrentCounter = 0;
//
//    private RecyclerView mRecyclerView = null;
//
//    private DataAdapter mDataAdapter = null;
//
//    private PreviewHandler mHandler = new PreviewHandler(this);
//    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter = null;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sample_activity);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.list);
//
//        //init data
//        ArrayList<FavoriteAddressModel> dataList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//
//
//
//            FavoriteAddressModel item = new FavoriteAddressModel();
//            item.id = i;
//            item.title = "item" + i;
//            dataList.add(item);
//        }
//
//        mCurrentCounter = dataList.size();
//
//        mDataAdapter = new DataAdapter(this);
//        mDataAdapter.addItems(dataList);
//
//        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(this));
//
//        mRecyclerView.addOnScrollListener(mOnScrollListener);
//    }
//
//    private void notifyDataSetChanged() {
//        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
//    }
//
//    private void addItems(ArrayList<FavoriteAddressModel> list) {
//
//        mDataAdapter.addItems(list);
//        mCurrentCounter += list.size();
//    }
//
//    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
//
//        @Override
//        public void onLoadNextPage(View view) {
//            super.onLoadNextPage(view);
//
//            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
//            if(state == LoadingFooter.State.Loading) {
//                Log.d("@Cundong", "the state is Loading, just wait..");
//                return;
//            }
//
//            if (mCurrentCounter < TOTAL_COUNTER) {
//                // loading more
//                RecyclerViewStateUtils.setFooterViewState(EndlessLinearLayoutActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
//                requestData();
////            } else {
//                //the end
////                RecyclerViewStateUtils.setFooterViewState(EndlessLinearLayoutActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
//            }
//        }
//    };
//
//    private static class PreviewHandler extends Handler {
//
//        private WeakReference<EndlessLinearLayoutActivity> ref;
//
//        PreviewHandler(EndlessLinearLayoutActivity activity) {
//            ref = new WeakReference<>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            final EndlessLinearLayoutActivity activity = ref.get();
//            if (activity == null || activity.isFinishing()) {
//                return;
//            }
//
//            switch (msg.what) {
//                case -1:
//                    int currentSize = activity.mDataAdapter.getItemCount();
//
//                    //模拟组装10个数据
//                    ArrayList<RecentAddressModel> newList = new ArrayList<>();
//                    for (int i = 0; i < 10; i++) {
//                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
//                            break;
//                        }
//
//                        RecentAddressModel item = new RecentAddressModel();
//                        item.id = currentSize + i;
//                        item.title = "item" + (item.id);
//
//                        newList.add(item);
//                    }
//
//                    activity.addItems(newList);
//                    RecyclerViewStateUtils.setFooterViewState(activity.mRecyclerView, LoadingFooter.State.Normal);
//                    break;
//                case -2:
//                    activity.notifyDataSetChanged();
//                    break;
//                case -3:
//                    RecyclerViewStateUtils.setFooterViewState(activity, activity.mRecyclerView, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
//                    break;
//            }
//        }
//    }
//
//    private View.OnClickListener mFooterClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            RecyclerViewStateUtils.setFooterViewState(EndlessLinearLayoutActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
//            requestData();
//        }
//    };
//
//    /**
//     * 模拟请求网络
//     */
//    private void requestData() {
//
//        new Thread() {
//
//            @Override
//            public void run() {
//                super.run();
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //模拟一下网络请求失败的情况
//                if(NetworkUtils.isNetAvailable(EndlessLinearLayoutActivity.this)) {
//                    mHandler.sendEmptyMessage(-1);
//                } else {
//                    mHandler.sendEmptyMessage(-3);
//                }
//            }
//        }.start();
//    }
//
//    private class DataAdapter extends RecyclerView.Adapter {
//
//        private LayoutInflater mLayoutInflater;
//        private SortedList<FavoriteAddressModel> mSortedList;
//
//        public DataAdapter(Context context) {
//            mLayoutInflater = LayoutInflater.from(context);
//            mSortedList = new SortedList<>(FavoriteAddressModel.class, new SortedList.Callback<FavoriteAddressModel>() {
//
//                /**
//                 * 返回一个负整数（第一个参数小于第二个）、零（相等）或正整数（第一个参数大于第二个）
//                 */
//                @Override
//                public int compare(FavoriteAddressModel o1, FavoriteAddressModel o2) {
//
//                    if (o1.id < o2.id) {
//                        return -1;
//                    } else if (o1.id > o2.id) {
//                        return 1;
//                    }
//
//                    return 0;
//                }
//
//                @Override
//                public boolean areContentsTheSame(FavoriteAddressModel oldItem, FavoriteAddressModel newItem) {
//                    return oldItem.title.equals(newItem.title);
//                }
//
//                @Override
//                public boolean areItemsTheSame(FavoriteAddressModel item1, FavoriteAddressModel item2) {
//                    return item1.id == item2.id;
//                }
//
//                @Override
//                public void onInserted(int position, int count) {
//                    notifyItemRangeInserted(position, count);
//                }
//
//                @Override
//                public void onRemoved(int position, int count) {
//                    notifyItemRangeRemoved(position, count);
//                }
//
//                @Override
//                public void onMoved(int fromPosition, int toPosition) {
//                    notifyItemMoved(fromPosition, toPosition);
//                }
//
//                @Override
//                public void onChanged(int position, int count) {
//                    notifyItemRangeChanged(position, count);
//                }
//            });
//        }
//
//        public void addItems(ArrayList<FavoriteAddressModel> list) {
//            mSortedList.beginBatchedUpdates();
//
//            for(FavoriteAddressModel itemModel : list) {
//                mSortedList.add(itemModel);
//            }
//
//            mSortedList.endBatchedUpdates();
//        }
//
//        public void deleteItems(ArrayList<FavoriteAddressModel> items) {
//            mSortedList.beginBatchedUpdates();
//            for (FavoriteAddressModel item : items) {
//                mSortedList.remove(item);
//            }
//            mSortedList.endBatchedUpdates();
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(mLayoutInflater.inflate(R.layout.sample_item_text, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            FavoriteAddressModel item = mSortedList.get(position);
//
//            ViewHolder viewHolder = (ViewHolder) holder;
//            viewHolder.textView.setText(item.title);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mSortedList.size();
//        }
//
//        private class ViewHolder extends RecyclerView.ViewHolder {
//
//            private TextView textView;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                textView = (TextView) itemView.findViewById(R.id.info_text);
//
//                textView.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FavoriteAddressModel item = mSortedList.get(RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this));
//                        Toast.makeText(EndlessLinearLayoutActivity.this, item.title, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
//    }
}
