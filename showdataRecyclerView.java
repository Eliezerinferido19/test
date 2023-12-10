package com.test.inbenta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class StockScreen extends AppCompatActivity {
    private static final String TAG = "StockScreen";
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;
    private recyclerAdapter adapter;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private TextView testTxT;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_screen);

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewItems);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        testTxT = findViewById(R.id.testTxt);

//        setItemData();
        setRecyclerView();
        retrieveItemsForUser();

    }
   private void setItemData() {
       itemList.add(new Item("milo",70,43,42,62));
       itemList.add(new Item("new",70,43,42,62));
       itemList.add(new Item("try",70,43,42,62));
       itemList.add(new Item("wala ra",70,43,42,62));

   }

//    private void retrieveItemsForUser() {
//        FirebaseUser currentUser = auth.getCurrentUser();
//        String useremail = currentUser.getEmail(); // getUid
//        db.collection("item")
//                .document(useremail)//or userId
//                .collection("items")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                String itemName = document.getString("ItemName");
//                                double listPrice = document.getDouble("PurchasePrice");
//                                double retailPrice = document.getDouble("RetailPrice");
//                                int stockQty = Objects.requireNonNull(document.getLong("StockQuantity")).intValue();
//                                int soldQty = Objects.requireNonNull(document.getLong("soldQty")).intValue();

// //                                itemList.add(new Item(itemName, listPrice, retailPrice, stockQty, soldQty));
//                                // Pagdeclare ug temporaryItemList nga string sa bbaw
//                                String testTxT1 = "Item Name:" + itemName + "; List Price: " + listPrice + "; Retail Price: " + retailPrice + "; Stock Quantity;" +stockQty + ": Sold Quantity:  " + soldQty + "\n";
//                                testTxT.setText(testTxT1);

//                            }
//                            adapter.notifyDataSetChanged();

//                         } else {
//                             Toast.makeText(StockScreen.this,"error cant get any data",Toast.LENGTH_SHORT).show();
//                         }
//                     }
//                 });
//     }




    private void setRecyclerView() {
        adapter = new recyclerAdapter(itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    // Navigation methods

    public void navigateTo(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    public void toRegisterNewItem(View view) {
        navigateTo(RegisterNewItem.class);
    }

//    public void toStockEditScreen(View view) {
//        navigateTo(StockEditScreen.class);
//    }

    public void toHomeScreen(View view) {
        navigateTo(HomeScreen.class);
    }

    public void toRestockScreen(View view) {
        navigateTo(RestockScreen.class);
    }

    public void toSellScreen(View view) {
        navigateTo(SellScreen.class);
    }

    public void toProfileScreen(View view) {
        navigateTo(ProfileScreen.class);
    }
}
