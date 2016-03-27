package com.sgj.ayibang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sgj.ayibang.fragment.FragmentCard;
import com.sgj.ayibang.model.Card;
import com.sgj.ayibang.utils.AppUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 2016/3/25.
 */
public class CardPagerAdapter extends FragmentStatePagerAdapter {

    private List<Card> mPostList;
    private List<Fragment> mFragments = new ArrayList();

    public CardPagerAdapter(FragmentManager fm, List<Card> cards) {
        super(fm);
        Iterator iterator = cards.iterator();
        while (iterator.hasNext()){
            Card card = (Card) iterator.next();
            this.mFragments.add(FragmentCard.newInstance(card));

        }
         this.mPostList = cards;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragments.get(position);
    }

    @Override
    public int getCount() {
        return (null == mPostList ? 0 : mPostList.size());
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void setFragments(List<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public void addCardList(ArrayList<Card> cards){
        ArrayList arrayList = new ArrayList();
        Iterator iterator = cards.iterator();
        while (iterator.hasNext()){
            arrayList.add(FragmentCard.newInstance((Card)iterator.next()));
        }
        if(this.mFragments == null){
            mFragments = new ArrayList<>();
        }
        this.mFragments.addAll(arrayList);
        this.mPostList.addAll(cards);

    }
}
