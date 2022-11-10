package uz.mult;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultfilmAdapter extends RecyclerView.Adapter<MultfilmAdapter.MultItemHolder> {
    private JsonClasses.Root mRoot;
    private Context mContext;
    private ClickListener mClickListener;

    public MultfilmAdapter(JsonClasses.Root root, ClickListener clickListener, Context context) {
        mContext = context;
        this.mRoot = root;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public MultItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.rv_item, null, false);
        MultItemHolder holder = new MultItemHolder(viewGroup);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MultItemHolder holder, int position) {
        String imageUrl = mRoot.items.get(holder.getAdapterPosition()).snippet.thumbnails.high.url;
        Picasso.get().load(imageUrl).into(holder.getRvImageView());
//        Glide.with(mContext).load(imageUrl).into(holder.getRvImageView());
        holder.getRvTitle().setText(mRoot.items.get(holder.getAdapterPosition()).snippet.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(mRoot.items.get(holder.getAbsoluteAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoot.items.size();
    }

    public class MultItemHolder extends RecyclerView.ViewHolder {
        private ImageView rvImageView;
        private TextView rvTitle;

        public MultItemHolder(@NonNull View itemView) {
            super(itemView);
            rvImageView = itemView.findViewById(R.id.rv_image);
            rvTitle = itemView.findViewById(R.id.rv_title);
        }

        public ImageView getRvImageView() {
            return rvImageView;
        }

        public TextView getRvTitle() {
            return rvTitle;
        }
    }
}
