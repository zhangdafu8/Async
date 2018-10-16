package com.example.qiaoxian.mycoursewithasynclistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    List<LessonResult.LessonDetail> mListDetail = new ArrayList<>();
    Context mContext;

    public MyAdapter(List<LessonResult.LessonDetail> listDetail, Context context){
        mListDetail = listDetail;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListDetail.size();
    }

    @Override
    public Object getItem(int i) {
        return mListDetail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =new ViewHolder();
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item,null);
            viewHolder.myImageView = (ImageView) view.findViewById(R.id.itemImage);
            viewHolder.myTextView= (TextView)view.findViewById(R.id.itemName);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();

        }
        viewHolder.myTextView.setText(mListDetail.get(i).getmName());
        viewHolder.myImageView.setVisibility(View.GONE);

        return view;
    }

    class ViewHolder{
        public ImageView myImageView;
        public TextView myTextView;
    }
}
