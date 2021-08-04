package ru.samitin.notesapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.Note;

public class AddCardFragment extends Fragment {
    public static final String KEY_ADD_NOTE="KEY_ADD_NOTE";

    public static AddCardFragment newInstance(Note note){
        AddCardFragment fragment=new AddCardFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(KEY_ADD_NOTE,note);
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
        EditText etDate=view.findViewById(R.id.add_card_date);
        EditText etNote=view.findViewById(R.id.add_card_note);
        if (getArguments()!=null){
            Note note=getArguments().getParcelable(KEY_ADD_NOTE);
            etName.setText(note.getNoteName());
            etDescription.setText(note.getNoteDescription());
            etDate.setText(note.getNoteDate());
            etNote.setText(note.getNote());
        }
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note=new Note(etName.getText().toString(),
                        etDescription.getText().toString(),
                        etDate.getText().toString(),
                        etNote.getText().toString());

            }
        });
    }
}
