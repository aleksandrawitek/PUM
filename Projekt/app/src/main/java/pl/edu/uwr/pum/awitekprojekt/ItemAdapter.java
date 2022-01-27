package pl.edu.uwr.pum.awitekprojekt;

import android.app.AlertDialog;
import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context mContext;
    private List<Data> myDataList;
    private String postid;
    private String note;
    private int amount;
    private String item;

    public ItemAdapter(Context mContext, List<Data> myDataList) {
        this.mContext = mContext;
        this.myDataList = myDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_data, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Data data = myDataList.get(position);
        holder.item.setText("Item: "+data.getItem());
        holder.amount.setText("Amount spend: "+data.getAmount() + " pln");
        holder.date.setText(data.getDate());
        holder.notes.setText("Note: "+data.getNotes());

        //odpowiedni rysunek w zaleznosci na co byly wydane pieniadze

        if (data.getItem().equals("Bills")){
            holder.imageView.setImageResource(R.drawable.bills);}
        if (data.getItem().equals("Drive")){
            holder.imageView.setImageResource(R.drawable.drive);}
        if (data.getItem().equals("Food")){
            holder.imageView.setImageResource(R.drawable.food);}
        if (data.getItem().equals("Groceries")){
            holder.imageView.setImageResource(R.drawable.groceries);}
        if (data.getItem().equals("Health")){
            holder.imageView.setImageResource(R.drawable.health);}
        if (data.getItem().equals("Shopping")){
            holder.imageView.setImageResource(R.drawable.shopping);}
        if (data.getItem().equals("Other")){
            holder.imageView.setImageResource(R.drawable.other);}


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postid = data.getId();
                note = data.getNotes();
                amount = data.getAmount();
                item = data.getItem();

                update();
            }
        });

    }

    private void update() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View myView = inflater.inflate(R.layout.activity_update, null);

        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();

        final  TextView mItem = myView.findViewById(R.id.item);
        final EditText mAmount = myView.findViewById(R.id.amount);
        final EditText mNote = myView.findViewById(R.id.note);
        final ImageView mImage = myView.findViewById(R.id.picture);

        if (item.equals("Bills")){
            mImage.setImageResource(R.drawable.bills);
        }
        if (item.equals("Groceries")){
            mImage.setImageResource(R.drawable.groceries);
        }
        if (item.equals("Health")){
            mImage.setImageResource(R.drawable.health);
        }
        if (item.equals("Other")){
            mImage.setImageResource(R.drawable.other);
        }
        if (item.equals("Shopping")){
            mImage.setImageResource(R.drawable.shopping);
        }
        if (item.equals("Drive")){
            mImage.setImageResource(R.drawable.drive);
        }
        if (item.equals("Food")){
            mImage.setImageResource(R.drawable.food);
        }
        mItem.setText(item);

        mAmount.setText(String.valueOf(amount));
        mAmount.setSelection(String.valueOf(amount).length());

        mNote.setText(note);
        mNote.setSelection(note.length());

        Button updateBtn  = myView.findViewById(R.id.update);
        Button deleteBtn = myView.findViewById(R.id. delete);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(mAmount.getText().toString());
                note = mNote.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                String date = dateFormat.format(cal.getTime());

                Data data = new Data(item, date,postid, note, amount);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("expenses").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                );
                reference.child(postid).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mContext, "Item updated successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext, "Failure" +task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("expenses").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                );
                reference.child(postid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mContext, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext, "Failure" +task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();

            }
        });


        dialog.show();
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView item, amount, date, notes;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            notes  = itemView.findViewById(R.id.note);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
