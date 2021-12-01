package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder> {

    private List<Crime> crimeList;
    private Context context;
    private LayoutInflater inflater;

    public CrimeAdapter(Context context, List<Crime> crimeList) {
        inflater = LayoutInflater.from(context);
        this.crimeList = crimeList;
        this.context = context;
    }
    class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView crimeText;
        final CrimeAdapter adapter;

        public CrimeViewHolder(@NonNull View itemView, CrimeAdapter adapter) {
            super(itemView);
            crimeText = itemView.findViewById(R.id.crimes);
            this.adapter = adapter;
        }
        @Override
        public void onClick(View view){
            int position = getLayoutPosition();
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
        holder.crimeText.setText(current.getTitle());

    }

    @Override
    public int getItemCount() {
        return  crimeList.size();
    }

}
