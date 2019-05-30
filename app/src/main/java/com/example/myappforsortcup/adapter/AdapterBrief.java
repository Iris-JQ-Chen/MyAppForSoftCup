package com.example.myappforsortcup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.activity.ShowAnswer;
import com.example.myappforsortcup.bean.AnswerBrief;

import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class AdapterBrief extends RecyclerView.Adapter<AdapterBrief.ViewHolder> {

    private Context mContext = null;
    private List<AnswerBrief> mList = null;

    class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        View itemView;
        TextView title;
        TextView briefDescription;
        TextView sourceWeb;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView)itemView.findViewById(R.id.question_title);
            briefDescription = (TextView)itemView.findViewById(R.id.answer_brief_description);
            sourceWeb = (TextView)itemView.findViewById(R.id.answer_source_web);
            date = (TextView)itemView.findViewById(R.id.answer_date);
        }

        @Override
        public void preAnimateAddImpl(RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void preAnimateRemoveImpl(RecyclerView.ViewHolder viewHolder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void animateAddImpl(RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void animateRemoveImpl(RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }

    public AdapterBrief(List<AnswerBrief> list){
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (parent != null && mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brief, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AnswerBrief answerBrief = mList.get(position);
        holder.title.setText(answerBrief.getTitle());
        holder.briefDescription.setText(answerBrief.getBriefDescription());
        holder.sourceWeb.setText(answerBrief.getSourceWeb());
        holder.date.setText(answerBrief.getData().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext == null){
                    Log.d("SoftCup","mcontext为空");
                }
                Intent intent = new Intent(mContext, ShowAnswer.class);
                intent.putExtra(ShowAnswer.ANSWER_ID, answerBrief.getId());
                intent.putExtra(ShowAnswer.QUESTION_TITLE,answerBrief.getTitle());
                intent.putExtra(ShowAnswer.ANSWER_BRIEF_DESCRIPTION, answerBrief.getBriefDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
