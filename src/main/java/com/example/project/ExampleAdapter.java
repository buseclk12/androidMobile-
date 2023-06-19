package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    //new
    Context context;
    private List<String> cartItems;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView buttonAddToCart;

        ViewHolder(View itemView) {
            super(itemView);
            buttonAddToCart = itemView.findViewById(R.id.addButton);
        }
    }
    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View itemView = inflator.inflate(R.layout.products_row_item, parent, false);
        ExampleViewHolder mViewHolder = new ExampleViewHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        //String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        int likeCount = currentItem.getLikeCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Price: " + likeCount + " $");
        Picasso.get().load(currentItem.getImageUrl()).into(holder.mImageView);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),  creatorName + " added to the card ", Toast.LENGTH_SHORT).show();
                addToCart(creatorName);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void addToCart(String productName) {
        if (cartItems == null) {
            cartItems = new ArrayList<>(); // cartItems listesini başlat
        }
        cartItems.add(productName);     }
    //new
    private void showCartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alışveriş Sepeti");

        // Alışveriş sepetindeki ürünlerin listesini oluştur
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : cartItems) {
            stringBuilder.append("- ").append(item).append("\n");
        }

        builder.setMessage(stringBuilder.toString());
        builder.setPositiveButton("Tamam", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public TextView addButton;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
            //new
            addButton = itemView.findViewById(R.id.addButton);
        }

    }
}
