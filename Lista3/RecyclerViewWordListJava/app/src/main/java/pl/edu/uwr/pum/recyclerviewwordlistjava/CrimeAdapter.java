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

    private final LinkedList<String> crimeList;
    private LayoutInflater inflater;

    public CrimeAdapter(Context context, LinkedList<String> crimeList) {
        inflater = LayoutInflater.from(context);
        this.crimeList = crimeList;
    }
    class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView crimeText;
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
        String current = crimeList.get(position);
        holder.crimeText.setText((CharSequence) current);

    }

    @Override
    public int getItemCount() {
        return  crimeList.size();
    }

}
