package ru.samitin.notesapp.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.CardData;
import ru.samitin.notesapp.model.repository.CardSource;
import ru.samitin.notesapp.model.repository.CardSourceImpi;
import ru.samitin.notesapp.model.domain.Note;



public class NotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Note corentNote;    // Текущая позиция
    private boolean isLandscape;
    private CardSource data;
    private CardNotesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notes, container, false);
        initView(view);
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        initToolBar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        // Получим источник данных для списка
        data = new CardSourceImpi(getResources()).init();
        initRecyclerView();
    }


    private void initRecyclerView(){
        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);
        // Будем работать со встроенным менеджером
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // Установим адаптер
        adapter=new CardNotesAdapter(data);
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
    }

    private void initToolBar(Toolbar toolbar){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.point_add:
                        data.addCardData(new CardData("Заголовок " + (data.size()+1),
                                R.drawable.nature1,"Описание " + (data.size()+1)));
                        adapter.notifyItemInserted(data.size() - 1);
                        recyclerView.scrollToPosition(data.size() - 1);
                        //Toast.makeText(getContext(),"NotesFragment",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.point_clear:
                        data.clearCardData();
                        adapter.notifyDataSetChanged();
                        return true;
                }
                return false;
            }
        });
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, corentNote);
        super.onSaveInstanceState(outState);
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

    private void showNoteDitals(Note note){
        if (isLandscape)
            showLandNoteDitals(note);
        else
            showPortNoteDitals(note);
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
        intent.setClass(getActivity(), NoteDitalsActivity.class);
        intent.putExtra(NoteDitalsFragment.ARG_NOTE,corentNote);
        startActivity(intent);
    }

}