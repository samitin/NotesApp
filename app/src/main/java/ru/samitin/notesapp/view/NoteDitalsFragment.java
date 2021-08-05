package ru.samitin.notesapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.Note;

public class NoteDitalsFragment extends Fragment {

    public static String ARG_NOTE="ARG_NOTE";
    private Note correntNote;

    public static NoteDitalsFragment newInstance(Note note){
        NoteDitalsFragment ndf=new NoteDitalsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(ARG_NOTE,note);
        ndf.setArguments(bundle);
        return ndf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null)
            correntNote=getArguments().getParcelable(ARG_NOTE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_note_ditals, container, false);
        TextView nameTV=view.findViewById(R.id.name_detals);
        TextView descriptionTV=view.findViewById(R.id.description_detals);
        TextView dateTV=view.findViewById(R.id.date_detals);
        TextView noteTV=view.findViewById(R.id.note_detals);

        nameTV.setText(correntNote.getNoteName());
        descriptionTV.setText(correntNote.getNoteDescription());
        dateTV.setText(correntNote.getNoteDate());
        noteTV.setText(correntNote.getNote());
        return view;
    }
}