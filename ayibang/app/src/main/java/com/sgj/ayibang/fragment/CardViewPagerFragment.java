package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.CardPagerAdapter;
import com.sgj.ayibang.model.Card;
import com.sgj.ayibang.viewselect.ViewSelectorLayout;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2016/3/25.
 */
public class CardViewPagerFragment extends AbsBaseFragment {

    private static final String TAG = "CardViewPagerFragment";

    private static CardViewPagerFragment mFragment;
    private ViewPager mViewPager;
    private CardPagerAdapter mCardPagerAdapter;
    private ArrayList<Card> mCardList;
    private ViewSelectorLayout mViewSelectorLayout;

    private boolean mHasNext = true;

    private boolean mIsRequesting;//请求

    private boolean isAdapterUpdated;//更新适配器

    private int mCurrentViewPagerPage;//当前视图

    private Button mSideMenuOrBackBtn;

    private View mMainView;
    private int mPreColor;

    public static CardViewPagerFragment getInstance(){
        if(mFragment == null){
            mFragment = new CardViewPagerFragment();
        }
        return mFragment;
    }

    @Override
    protected void initActions(View paramView) {

    }

    @Override
    protected void initData() {
        mCardList = new ArrayList<>();
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_card, null);
        mViewSelectorLayout = new ViewSelectorLayout(getActivity(), view);

        mMainView = view.findViewById(R.id.main_view);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        return mViewSelectorLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewSelectorLayout.show_LoadingView();
        Log.e(TAG, " -- onActivityCreated -- ");
        fetchData();
    }

    private void fetchData() {
        mViewSelectorLayout.show_ContentView();
        BmobQuery<Card> query = new BmobQuery<>();
        query.setLimit(50);
        query.findObjects(getActivity(), new FindListener<Card>() {
            @Override
            public void onSuccess(List<Card> list) {
                mCardList = (ArrayList) list;
                handData(mCardList);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    private void handData(ArrayList<Card> cardList) {

        updateAppAdapter(cardList);

    }

    private void updateAppAdapter(ArrayList<Card> cardList) {
        if(getActivity() == null || (getActivity().isFinishing())){
            return;
        }
        if(cardList.isEmpty()){
            this.mMainView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            return;
        }
        if(mCardPagerAdapter == null){
            mCurrentViewPagerPage = 0;
            mCardPagerAdapter = new CardPagerAdapter(getActivity().getSupportFragmentManager(), cardList);
            mViewPager.setAdapter(mCardPagerAdapter);
        }else {
            mCardPagerAdapter.addCardList(cardList);
            mCardPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragment = null;
    }
}
