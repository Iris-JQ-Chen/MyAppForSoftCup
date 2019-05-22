package com.example.myappforsortcup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.activity.ShowAnswer;
import com.example.myappforsortcup.bean.AnswerBrief;

import java.util.List;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class AdapterOnSearch extends RecyclerView.Adapter<AdapterOnSearch.ViewHolder> {

    private Context mContext = null;
    private List<AnswerBrief> mList = null;

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView title;
        TextView briefDescription;
        TextView sourceWeb;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView)itemView.findViewById(R.id.question_title);
            briefDescription = (TextView)itemView.findViewById(R.id.answer_brief_description);
            sourceWeb = (TextView)itemView.findViewById(R.id.answer_source_web);
            date = (TextView)itemView.findViewById(R.id.answer_date);
        }
    }

    public AdapterOnSearch(List<AnswerBrief> list){
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (parent != null && mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_on_search, parent, false);
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
        holder.view.setOnClickListener(new View.OnClickListener() {
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
