package ru.samitin.notesapp.view;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.CardData;
import ru.samitin.notesapp.model.repository.CardSource;

public class CardNotesAdapter extends RecyclerView.Adapter<CardNotesAdapter.ViewHolder> {
    private final static String TAG = "SocialNetworkAdapter";
    private CardSource cardSource;
    private final Fragment fragment;
    private int menuPosition;
    public CardNotesAdapter(CardSource cardSource,Fragment fragment){
        this.fragment=fragment;
        this.cardSource=cardSource;
    }
    public int getMenuPosition() {
        return menuPosition;
    }

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }
    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        holder.setData(cardSource.getCardData(position));
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return cardSource.size();

    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на один пункт списка
    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTextViewName);
            description = itemView.findViewById(R.id.itenTextViewDescription);
            image = itemView.findViewById(R.id.itemImageView);


            registerContextMenu(itemView);

            // Обработчик нажатий на картинке
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

            // Обработчик нажатий на картинке
            image.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData cardData){
            title.setText(cardData.getName());
            description.setText(cardData.getDescription());
            image.setImageResource(cardData.getPicture());
        }
    }
}

