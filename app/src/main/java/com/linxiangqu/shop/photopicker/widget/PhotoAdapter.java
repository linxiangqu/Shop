package com.linxiangqu.shop.photopicker.widget;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.photopicker.PhotoPickUtils;
import com.linxiangqu.shop.photopicker.PhotoPreview;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by donglua on 15/5/31.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<String> photoPaths;
    private LayoutInflater inflater;

    private Context mContext;


    public void setAction(@MultiPickResultView.MultiPicAction int action) {
        this.action = action;
    }

    private int action;


    public PhotoAdapter(Context mContext, ArrayList<String> photoPaths) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        padding = dip2Px(8);

    }

    public void add(ArrayList<String> photoPaths) {
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
            notifyDataSetChanged();
        }

    }

    public void refresh(ArrayList<String> photoPaths) {
        this.photoPaths.clear();
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
        }
        notifyDataSetChanged();
    }


    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    public int dip2Px(int dip) {
        // px/dip = density;
        float density = mContext.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    int padding;

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {

        if (action == MultiPickResultView.ACTION_SELECT) {
            // RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.ivPhoto.getLayoutParams();
            holder.ivPhoto.setPadding(padding, padding, padding, padding);
            if (position == getItemCount() - 1) {//最后一个始终是+号，点击能够跳去添加图片
                Glide.with(mContext)
                        .load("")
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(R.mipmap.icon_pic_default)
                        .error(R.mipmap.icon_pic_default)
                        .into(holder.ivPhoto);
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (photoPaths != null && photoPaths.size() == 9) {
                            Toast.makeText(mContext, "已选了9张图片", Toast.LENGTH_SHORT).show();
                        } else {
                            PhotoPickUtils.startPick((Activity) mContext, photoPaths);
                        }
                    }
                });
                holder.deleteBtn.setVisibility(View.GONE);
            } else {
                String str = photoPaths.get(position);
                Log.e("file", str);
//                Toast.makeText(mContext,photoPaths.get(position),Toast.LENGTH_SHORT).show();
                Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
                Glide.with(mContext)
                        .load(uri)
                        .centerCrop()
                        .thumbnail(0.1f)
                        // .bitmapTransform(new RoundedCornersTransformation(mContext,6,0))
                        .placeholder(R.drawable.__picker_default_weixin)
                        .error(R.mipmap.__picker_ic_broken_image_black_48dp)
                        .into(holder.ivPhoto);
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        photoPaths.remove(position);
                        notifyDataSetChanged();
                    }
                });

                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PhotoPreview.builder()
                                .setPhotos(photoPaths)
                                .setAction(action)
                                .setCurrentItem(position)
                                .start((Activity) mContext);
                    }
                });
            }
        } else if (action == MultiPickResultView.ACTION_ONLY_SHOW) {
            //Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
            //Uri uri = Uri.parse(photoPaths.get(position));
            Log.e("pic", photoPaths.get(position));
            Toast.makeText(mContext,photoPaths.get(position),Toast.LENGTH_SHORT).show();
            Glide.with(mContext)
                    .load(photoPaths.get(position))
                    .centerCrop()
                    .thumbnail(0.1f)
                    // .bitmapTransform(new RoundedCornersTransformation(mContext,4,0))
                    .placeholder(R.drawable.__picker_default_weixin)
                    .error(R.mipmap.__picker_ic_broken_image_black_48dp)
                    .into(holder.ivPhoto);

            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PhotoPreview.builder()
                            .setPhotos(photoPaths)
                            .setAction(action)
                            .setCurrentItem(position)
                            .start((Activity) mContext);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return action == MultiPickResultView.ACTION_SELECT ? photoPaths.size() + 1 : photoPaths.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;
        public View cover;
        public View deleteBtn;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            vSelected.setVisibility(View.GONE);
            cover = itemView.findViewById(R.id.cover);
            cover.setVisibility(View.GONE);
            deleteBtn = itemView.findViewById(R.id.v_delete);
            deleteBtn.setVisibility(View.GONE);
        }
    }

}
