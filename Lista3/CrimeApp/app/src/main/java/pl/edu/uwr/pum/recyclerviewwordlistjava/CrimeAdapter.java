package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
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
        public ImageView warning;

        public CrimeViewHolder(@NonNull View itemView, CrimeAdapter adapter) {
            super(itemView);

            itemView.setOnClickListener(this);
            crimeText = itemView.findViewById(R.id.crimes);
            warning = itemView.findViewById(R.id.warning);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            listener.onClick(view, getLayoutPosition());

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
        boolean solved = current.getSolved();
        if(solved == true)
        {
            holder.warning.setVisibility(View.INVISIBLE);
        }
        if(solved == false)
        {
            holder.warning.setVisibility(View.VISIBLE);
        }
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




