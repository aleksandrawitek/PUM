package pl.edu.uwr.pum.awitekprojekt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView totalView;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private String userId = "";
    private ProgressDialog progressDialog;
    private ItemAdapter itemAdapter;
    private List<Data> dataList;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalView = findViewById(R.id.total_spend);
        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.floating_button);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("expenses").child(userId);
        progressDialog = new ProgressDialog(this);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }

        });


        //https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataList = new ArrayList<>();
        itemAdapter = new ItemAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(itemAdapter);
        
        getItems();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getItems() {

        //"wyciagniecie" tylko tych danych z bazy z dzisiejsza data
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("expenses").child(userId);
        Query query = reference.orderByChild("date").equalTo(date);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Data data1 = dataSnapshot.getValue(Data.class);
                    String item = data1.getItem();
                    String note = data1.getNotes();
                    int amount = data1.getAmount();
                    String date = data1.getDate();
                    String id = data1.getId();
                    //dataList.add(new Data(item,date,id,note,amount));
                    dataList.add(data1);
                }
                itemAdapter.notifyDataSetChanged();


                //podsumowanie wydatkow na dany dzien i zaktualizowanie odpowiednio text view
                int totalAmount = 0;
                for (DataSnapshot ds : snapshot.getChildren()){
                    Map< String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount+=pTotal;

                    totalView.setText("Total today: "+ totalAmount + " pln");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void addItem() {

        //otwieranie okna dodania nowego wydatku

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View myView = inflater.inflate(R.layout.activity_add_new, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        //wybieranie kategorii wydatku z listy

        final Spinner itemSpinner = myView.findViewById(R.id.spinner);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.items));
        itemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(itemsAdapter);

        final EditText amount = myView.findViewById(R.id.amount);
        final EditText notes = myView.findViewById(R.id.note);
        final Button save = myView.findViewById(R.id.save);
        final Button cancel = myView.findViewById(R.id.cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String amount2 = amount.getText().toString();
                String notes2 = notes.getText().toString();
                String item = itemSpinner.getSelectedItem().toString();

                if (amount2.isEmpty()){
                    amount.setError("Please put correct amount");
                    return;
                }
                if (notes2.isEmpty()){
                    notes.setError("Please put notes");
                    return;
                }
                if (item.equals("Select item")){
                    Toast.makeText(MainActivity.this, "Select correct item", Toast.LENGTH_SHORT).show();
                }

                else{
                    progressDialog.setMessage("Item adding...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    String id = reference.push().getKey();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                    String date = dateFormat.format(cal.getTime());

                    Data data = new Data(item, date, id, notes2,Integer.parseInt(amount2));
                    reference.child(id).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Item not added, fail", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                }
                dialog.dismiss();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(); }

        @Override
        public boolean onCreateOptionsMenu (Menu menu)
        {
            MenuInflater inflater =getMenuInflater();
            inflater.inflate(R.menu.activity_menu,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.account){
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
        }

}