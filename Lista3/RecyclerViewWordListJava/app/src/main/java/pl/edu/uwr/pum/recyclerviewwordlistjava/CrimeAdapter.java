package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder> {

    private List<Crime> crimeList;
    private Context context;
    private LayoutInflater inflater;
    private RecyclerViewClickListener listener;


    public CrimeAdapter(Context context, List<Crime> crimeList, RecyclerViewClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.crimeList = crimeList;
        this.context = context;
        this.listener = listener;
    }
    class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView crimeText;
        final CrimeAdapter adapter;

        public CrimeViewHolder(@NonNull View itemView, CrimeAdapter adapter) {
            super(itemView);

            itemView.setOnClickListener(this);
            crimeText = itemView.findViewById(R.id.crimes);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            listener.onClick(view, getLayoutPosition());//or adapter???
            //int position = getLayoutPosition();
            //Crime element = crimeList.get(position);
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.com"));
            //context.startActivity(intent);
            //adapter.notifyItemChanged(position);

        }
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.crimes_list, parent, false);
        return new CrimeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeViewHolder holder, int position) {
        Crime current = crimeList.get(position);
        holder.crimeText.setText(current.getTitle()+"\n"+current.getDate());

    }

    @Override
    public int getItemCount() {
        return  crimeList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);

    }



}
