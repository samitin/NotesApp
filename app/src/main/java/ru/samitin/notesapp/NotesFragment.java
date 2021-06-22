package ru.samitin.notesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.samitin.notesapp.domain.CardSource;
import ru.samitin.notesapp.domain.CardSourceImpi;
import ru.samitin.notesapp.domain.Note;


public class NotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Note corentNote;    // Текущая позиция
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view_lines);
        CardSource data=new CardSourceImpi(getResources()).init();
        initRecyclerView(recyclerView,data);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void initRecyclerView(RecyclerView recyclerView,CardSource data){
        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);
        // Будем работать со встроенным менеджером
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // Установим адаптер
        CardNotesAdapter adapter=new CardNotesAdapter(data);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDicoration=new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        itemDicoration.setDrawable(getResources().getDrawable(R.drawable.seporator,null));
        recyclerView.addItemDecoration(itemDicoration);
        adapter.setOnItemClickListener(new CardNotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                corentNote=new Note(getResources().getStringArray(R.array.name)[position],
                        getResources().getStringArray(R.array.description)[position],
                        getResources().getStringArray(R.array.date)[position],
                        getResources().getStringArray(R.array.notes)[position]);
                showNoteDitals(corentNote);
            }
        });
        /*LinearLayout layout=(LinearLayout)view;
        String[] names=getResources().getStringArray(R.array.name);
        for (int i=0;i<names.length;i++){
            TextView textView=new TextView(getContext());
            textView.setText(names[i]);
            textView.setTextSize(30);
            layout.addView(textView);
            final int fi=i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    corentNote=new Note(getResources().getStringArray(R.array.name)[fi],
                            getResources().getStringArray(R.array.description)[fi],
                            getResources().getStringArray(R.array.date)[fi],
                            getResources().getStringArray(R.array.notes)[fi]);
                    showNoteDitals(corentNote);
                }
            });
        }*/
    }
    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, corentNote);
        super.onSaveInstanceState(outState);
    }


    private void showNoteDitals(Note note){
        if (isLandscape)
            showLandNoteDitals(note);
        else
            showPortNoteDitals(note);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState!=null)
            corentNote=savedInstanceState.getParcelable(CURRENT_NOTE);
        else
            corentNote=new Note(getResources().getStringArray(R.array.name)[0],
                    getResources().getStringArray(R.array.description)[0],
                    getResources().getStringArray(R.array.date)[0],
                    getResources().getStringArray(R.array.notes)[0]);
        if (isLandscape){

           showLandNoteDitals(corentNote);
        }

    }
    private void showLandNoteDitals(Note note){
        NoteDitalsFragment ditail=NoteDitalsFragment.newInstance(note);
        FragmentManager fragmentManager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.notes_dital,ditail);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }


    private void showPortNoteDitals(Note note){
        Intent intent=new Intent();
        intent.setClass(getActivity(),NoteDitalsActivity.class);
        intent.putExtra(NoteDitalsFragment.ARG_NOTE,corentNote);
        startActivity(intent);
    }

}