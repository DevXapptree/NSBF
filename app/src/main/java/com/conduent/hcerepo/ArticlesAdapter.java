package com.conduent.hcerepo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ItemHelper> {

    private Context context;
    private JSONArray jsonArray;
    private OnSelect selectListener;

    public ArticlesAdapter(@NotNull Context context, @NotNull JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.selectListener = (OnSelect) context;

    }

    @NonNull
    @Override
    public ArticlesAdapter.ItemHelper onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_list_item, viewGroup, false);

        return new ItemHelper(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticlesAdapter.ItemHelper itemHelper, int i) {
        try {
            itemHelper.jsonObject = jsonArray.getJSONObject(i);
            itemHelper.txt_article_name.setText(itemHelper.jsonObject.optString("TitleOffer"));

            itemHelper.txt_article_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectListener.onItemSelected(itemHelper.jsonObject.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ItemHelper extends RecyclerView.ViewHolder {
        private JSONObject jsonObject;
        private TextView txt_article_name;

        public ItemHelper(@NonNull View itemView) {
            super(itemView);
            txt_article_name = itemView.findViewById(R.id.txt_article_name);
        }
    }

    interface OnSelect {
        public void onItemSelected(String articleData);
    }
}
