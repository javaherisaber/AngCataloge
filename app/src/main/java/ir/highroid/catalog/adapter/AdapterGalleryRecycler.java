package ir.highroid.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.highroid.catalog.R;
import ir.highroid.catalog.activity.ActivityDetail;
import ir.highroid.catalog.bundle.BundleGallery;

/**
 * Created by mohammad on 6/19/2016.
 */
public class AdapterGalleryRecycler extends RecyclerView.Adapter<AdapterGalleryRecycler.GalleryViewHolder> {


    private List<BundleGallery> galleryList;
    private Context contextMain;
    Typeface tfSans;
    private String categTitle;
    Activity activity;

    public AdapterGalleryRecycler(List<BundleGallery> galleryList, Context context, String categTitle, Activity activity) {
        this.galleryList = galleryList;
        this.contextMain = context;
        this.categTitle = categTitle;
        this.activity = activity;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    private final int VIEW_FAVORITE_OFF = 1;
    private final int VIEW_FAVORITE_ON = 2;

    @Override
    public int getItemViewType(int position) {
        BundleGallery bundleGallery = galleryList.get(position);
        if(bundleGallery.is_favorite == 0){
            return VIEW_FAVORITE_OFF;
        }
        return VIEW_FAVORITE_ON;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder galleryViewHolder, final int position) {

        BundleGallery galleryInfo = galleryList.get(position);
        galleryViewHolder.galleryTitle.setText(galleryInfo.title);
        galleryViewHolder.galleryID = galleryInfo.id;
        galleryViewHolder.fk_categ_id = galleryInfo.fk_categID;
        galleryViewHolder.is_favorite = galleryInfo.is_favorite;
        galleryViewHolder.galleryBtnFavorite.setTag("favorite_is_off");
        int id = contextMain.getResources().getIdentifier(galleryInfo.pic, "drawable", contextMain.getPackageName());
        galleryViewHolder.galleryImage.setImageResource(id);
        galleryViewHolder.galleryImage.setTag(galleryInfo.pic);
        galleryViewHolder.txtCategoryTitle.setText(categTitle);


//        switch (galleryViewHolder.getItemViewType()) {

//            case VIEW_FAVORITE_OFF:
//                break;
//
//            case VIEW_FAVORITE_ON:
//                galleryViewHolder.galleryTitle.setText(galleryInfo.title);
//                galleryViewHolder.galleryID = galleryInfo.id;
//                galleryViewHolder.is_favorite = galleryInfo.is_favorite;
//                galleryViewHolder.fk_categ_id = galleryInfo.fk_categID;
//                galleryViewHolder.galleryBtnFavorite.setTag("favorite_is_on");
//                int id1 = contextMain.getResources().getIdentifier(galleryInfo.pic, "drawable", contextMain.getPackageName());
//                galleryViewHolder.galleryImage.setImageResource(id1);
//                galleryViewHolder.galleryImage.setTag(galleryInfo.pic);
//                galleryViewHolder.txtCategoryTitle.setText(categTitle);
//                break;
//        }

    }



    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_gallery_item, viewGroup, false);
        GalleryViewHolder gallery = new GalleryViewHolder(itemView);
        return gallery;
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        protected TextView galleryTitle, txtCategoryTitle;
        protected ImageView galleryImage;
        protected ImageView galleryBtnFavorite;
        protected int galleryID,is_favorite;
        protected  String fk_categ_id;

        public GalleryViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            galleryTitle =  (TextView) v.findViewById(R.id.txtTitle);
            galleryImage = (ImageView)  v.findViewById(R.id.imgGalleryImage);
            galleryBtnFavorite = (ImageView) v.findViewById(R.id.btnFavorite);
            txtCategoryTitle = (TextView) v.findViewById(R.id.txtCategoryTitle);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityDetail.class);
                    intent.putExtra("imageNum", getLayoutPosition());
                    intent.putExtra("imageName", galleryImage.getTag() + "b");
                    intent.putExtra("categId", fk_categ_id);
                    intent.putExtra("categTitle", txtCategoryTitle.getText().toString());
                    contextMain.startActivity(intent);
                    activity.overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                }
            });

//            galleryBtnFavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (galleryBtnFavorite.getTag().equals("favorite_is_off")){
//                        galleryBtnFavorite.setImageResource(R.mipmap.ic_favorite_remove);
//                        galleryList.get(getLayoutPosition()).is_favorite = 1;
//
//                    }
//                    else{
//                        galleryBtnFavorite.setImageResource(R.mipmap.ic_favorite_add);
//                        galleryList.get(getLayoutPosition()).is_favorite = 0;
//                    }
//                }
//            });

        }

    }
}
