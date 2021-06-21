package ru.samitin.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import ru.samitin.notesapp.domain.CardData;
import ru.samitin.notesapp.domain.CardSource;

public class CardNotesAdapter extends RecyclerView.Adapter<CardNotesAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private CardSource cardSource;
    public CardNotesAdapter(CardSource cardSource){
        this.cardSource=cardSource;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public CardNotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardNotesAdapter.ViewHolder holder, int position) {
        holder.setData(cardSource.getCardDates().get(position));
    }

    @Override
    public int getItemCount() {
        return cardSource.size();
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private AppCompatImageView image;
        private TextView description;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.textViewName);
        image=itemView.findViewById(R.id.imageView);
        description=itemView.findViewById(R.id.textViewDescription);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(view,getAdapterPosition());
            }
        });
    }
    public void setData(CardData data){
        name.setText(data.getName());
        image.setBackgroundResource(data.getPicture());
        description.setText(data.getDescription());
    }
}
}
