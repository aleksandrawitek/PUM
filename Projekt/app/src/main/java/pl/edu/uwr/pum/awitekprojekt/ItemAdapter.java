package pl.edu.uwr.pum.awitekprojekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private Context context;
    private List<Data> dataList;

    public ItemAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_data,parent,false);
        return new ItemAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Data data = dataList.get(position);
        holder.item.setText("Item: " + data.getItem());
        holder.amount.setText("Spend: " + data.getAmount() + " pln");
        holder.date.setText(data.getDate());
        holder.notes.setText("Note: " + data.getNotes());


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


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView item, amount, date, notes;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            notes = itemView.findViewById(R.id.note);
            imageView = itemView.findViewById(R.id.imageView);



        }
    }
}
