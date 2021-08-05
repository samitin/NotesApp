package ru.samitin.notesapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.CardData;
import ru.samitin.notesapp.model.domain.Note;

public class AddCardFragment extends Fragment {
    public static final String KEY_ADD_CARD="KEY_ADD_CARD";
    public static final String KEY_ADD_POSITION="KEY_ADD_POSITION";

    public static AddCardFragment newInstance(CardData card,int position){
        AddCardFragment fragment=new AddCardFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(KEY_ADD_CARD,card);
        bundle.putInt(KEY_ADD_POSITION,position);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add_card,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText etName=view.findViewById(R.id.add_card_name);
        EditText etDescription=view.findViewById(R.id.add_card_description);
        CardData cardData;
        int position=0;
        if (getArguments()!=null){
            cardData=getArguments().getParcelable(KEY_ADD_CARD);
            position=getArguments().getInt(KEY_ADD_POSITION);
            etName.setText(cardData.getName());
            etDescription.setText(cardData.getDescription());

        }

        int finalPosition = position;
        int image=getImageId(view);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable(KEY_ADD_CARD,new CardData(etName.getText().toString(),getImageId(view),
                        etDescription.getText().toString()));
                bundle.putInt(KEY_ADD_POSITION, finalPosition);
                requireActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.home,new NotesFragment().newInstance(bundle)).commit();

            }
        });
    }
    private int getImageId(View view){
        RadioButton r1=view.findViewById(R.id.add_card_image1);
        if(r1.isChecked())
            return R.drawable.nature1;
        RadioButton r2=view.findViewById(R.id.add_card_image2);
        if(r2.isChecked())
            return R.drawable.nature2;
        RadioButton r3=view.findViewById(R.id.add_card_image3);
        if(r3.isChecked())
            return R.drawable.nature3;
        RadioButton r4=view.findViewById(R.id.add_card_image4);
        if(r4.isChecked())
            return R.drawable.nature4;
        RadioButton r5=view.findViewById(R.id.add_card_image5);
        if(r5.isChecked())
            return R.drawable.nature5;
        return R.drawable.nature1;
    }
}
