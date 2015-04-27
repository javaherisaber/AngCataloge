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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.highroid.catalog.R;
import ir.highroid.catalog.activity.ActivityGallery;
import ir.highroid.catalog.bundle.BundleCategory;

/**
 * Created by mohammad on 6/19/2016.
 */
public class AdapterCategoryRecycler extends RecyclerView.Adapter<AdapterCategoryRecycler.CategoryViewHolder> {

    private List<BundleCategory> categoryList;
    private Context contextMain;
    Typeface tfSans;
    Activity activity;

    public AdapterCategoryRecycler(List<BundleCategory> categoryList, Context context, Activity activity) {
        this.categoryList = categoryList;
        this.contextMain = context;
        this.activity = activity;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    private final int VIEW_TITLE_RIGHT = 1;
    private final int VIEW_TITLE_LEFT = 2;

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return VIEW_TITLE_RIGHT;
        }
        return VIEW_TITLE_LEFT;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, final int position) {

        BundleCategory categoryInfo = categoryList.get(position);
        categoryViewHolder.categoryID = categoryInfo.id;
        categoryViewHolder.categoryTitle.setText(categoryInfo.title);
//        categoryViewHolder.categoryTitle.setTypeface(tfSans);
//        int id = contextMain.getResources().getIdentifier(categoryInfo.pic, "drawable", contextMain.getPackageName());
//        categoryViewHolder.categoryImage.setImageResource(id);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(230 , FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0,0,0,15);
//        switch (categoryViewHolder.getItemViewType()) {

//            case VIEW_TITLE_RIGHT:
//                params.gravity = Gravity.BOTTOM|Gravity.RIGHT;
//                categoryViewHolder.categoryTitle.setLayoutParams(params);
//                break;

//            case VIEW_TITLE_LEFT:
//                categoryViewHolder.categoryTitle.setText(categoryInfo.title);
//                categoryViewHolder.categoryTitle.setTypeface(tfSans);
//                params.gravity = Gravity.BOTTOM|Gravity.LEFT;
//                int id1 = contextMain.getResources().getIdentifier(categoryInfo.pic, "drawable", contextMain.getPackageName());
//                categoryViewHolder.categoryImage.setImageResource(id1);
//                categoryViewHolder.categoryTitle.setLayoutParams(params);
//                break;
//        }

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_category_item, viewGroup, false);
        CategoryViewHolder category = new CategoryViewHolder(itemView);
        return category;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        protected TextView categoryTitle;
        protected ImageView categoryImage;
        protected int categoryID;

        public CategoryViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            categoryTitle =  (TextView) v.findViewById(R.id.txtTitle);
            categoryImage = (ImageView)  v.findViewById(R.id.imgCategoryImage);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityGallery.class);
                    intent.putExtra("categID",categoryID);
                    contextMain.startActivity(intent);
                    activity.overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                }
            });

        }

    }
}
