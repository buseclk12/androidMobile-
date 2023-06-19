package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private List<String> cartItems = new ArrayList<>();
    ImageView image_view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.homepage);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        ExampleItem exampleItem;
        mExampleList = new ArrayList<>();

        Button buttonShopping = findViewById(R.id.buttonShopping);
        buttonShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCartDialog();
            }
        });

        Button userInfo = findViewById(R.id.userInfo);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserProfileDialog(email,password);
            }
        });
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // JSON dosyasını okuyun
        FetchJSON fetchJSON = new FetchJSON(this);

        mExampleList = fetchJSON.loadInBackground();
        mExampleAdapter = new ExampleAdapter(HomePage.this, mExampleList);
        mRecyclerView.setAdapter(mExampleAdapter);

    }



    private void showUserProfileDialog(String email, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Profile");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("E-mail: ").append(email).append("\n");
        stringBuilder.append("Password: ").append(password).append("\n");

        builder.setMessage(stringBuilder.toString());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //JSON OKUMA
    private static class FetchJSON {
        String myDdata = "";
        String line = "";
        Context context;
        String key;
        public FetchJSON(Context context) {
            this.context = context;
            this.key = key;
        }
        public ArrayList<ExampleItem> loadInBackground () {
            ArrayList<ExampleItem> tempList = new ArrayList<ExampleItem>();
            String jsonStr = loadFileFromAssets("data.json");
            if (jsonStr != null) {
                try {
                    JSONObject allJSON = new JSONObject(jsonStr);
                    //hits yerine objeye tag diye static str verip onu alabilirsin
                    JSONArray productsJSONArray = allJSON.getJSONArray("hits");

                    for (int i = 0; i < productsJSONArray.length(); i++) {
                        JSONObject s = productsJSONArray.getJSONObject(i);

                        //aynı tag muhabbeti burada da geçerli
                        String creatorName = s.getString("Title");
                        String imageUrl = s.getString("Image Src");
                        int likeCount = s.getInt("Variant Price");

                        ExampleItem exampleItem = new ExampleItem(imageUrl, creatorName, likeCount);
                        tempList.add(exampleItem);


                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                }
            }
            return tempList;
        }

        private String loadFileFromAssets(String fileName) {
            String fileContent = null;
            try {
                InputStream is = context.getAssets().open(fileName);

                int size = is.available();
                byte[] buffer = new byte[size];

                is.read(buffer);
                is.close();

                fileContent = new String(buffer, "UTF-8");

            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return fileContent;
        }
    }
    private void showCartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setTitle("Shopping Card");

        // Alışveriş sepetini göstermek için bir RecyclerView ekleyin
        RecyclerView recyclerView = new RecyclerView(HomePage.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));

        // Alışveriş sepetini RecyclerView ile bağlayan bir CartAdapter oluşturun
        List<com.example.project.CartItemWithQuantity> cartItemsWithQuantity = new ArrayList<>();
        // cartItemsWithQuantity listesine alışveriş sepetindeki öğeleri ve ilgili miktarları ekleyin

        com.example.project.CartAdapter cartAdapter = new com.example.project.CartAdapter(cartItemsWithQuantity, HomePage.this);
        recyclerView.setAdapter(cartAdapter);

        builder.setView(recyclerView);

        // Kapatma düğmesini ekleme
        builder.setPositiveButton("Kapat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dialogu kapat
            }
        });
        builder.setPositiveButton("Go to the Payment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(HomePage.this, Payment.class);
                startActivity(intent);
            }
        });

        builder.create().show(); // Dialogu göster
    }
}
