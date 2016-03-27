package com.sgj.ayibang.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sgj.ayibang.R;
import com.sgj.ayibang.model.Card;
import com.sgj.ayibang.utils.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/25.
 */
public class FragmentCard extends AbsBaseFragment{

    protected Card mCard;
    @Bind(R.id.text_author)
    protected TextView mAuthorText;
    @Bind(R.id.image_bottom_edge)
    protected ImageView mBottomEdgeImageView;
    @Bind(R.id.text_bravos)
    protected TextView mBravoNumText;
    @Bind(R.id.box_card)
    protected RelativeLayout mCardLayout;
    @Bind(R.id.image_cover)
    protected ImageView mCoverImageView;
    @Bind(R.id.text_digest)
    protected TextView digestText;
    @Bind(R.id.text_subtitle)
    protected TextView mSubTitleText;
    @Bind(R.id.text_title)
    protected TextView mTitleText;


    public static FragmentCard newInstance(Card card){
        FragmentCard fragmentCard = new FragmentCard();
        Bundle bundle = new Bundle();
        bundle.putSerializable("card", card);
        fragmentCard.setArguments(bundle);
        return fragmentCard;
    }

    @Override
    protected void initActions(View paramView) {



    }

    @Override
    protected void initData() {
        this.mCard = (Card) getArguments().getSerializable("card");
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.card_item, null);
        ButterKnife.bind(this, view);

            mTitleText.setText(mCard.getTitle());
            mSubTitleText.setText(mCard.getSubtitle());
            this.mBravoNumText.setText("No." + mCard.getBravos());
            this.digestText.setText("相忘谁先忘，倾国是故国");
            this.mAuthorText.setText(Html.fromHtml("<B>" + mCard.getAuthor() + "</B>"));
            initAndDisplayCoverImage();

        return view;
    }

    private void initAndDisplayCoverImage() {
        int coverWidth = AppUtils.getScreenDisplayMetrics(getActivity()).widthPixels - 2 * getResources().getDimensionPixelSize(R.dimen.card_margin);
        int coverHeight = (int) (180.0F * (coverWidth / 320.0F));

        ViewGroup.LayoutParams params = this.mCoverImageView.getLayoutParams();
        params.height = Float.valueOf(coverHeight).intValue();;
        Glide.with(getActivity()).load(mCard.getCover().getFileUrl(getContext())).centerCrop().into(mCoverImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
