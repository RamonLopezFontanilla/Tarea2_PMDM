package com.example.tarea2;

/**
 * Esta clase gestiona la vista del listado de personajes (contenedor del RecyclerView): fragment_persons_list.xml
 */

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea2.databinding.FragmentPersonsListBinding;

import java.util.ArrayList;

public class Persons_list extends Fragment {
    private FragmentPersonsListBinding binding;
    private ArrayList<PersonData> personsList;
    private PersonsRecyclerViewAdapter adapter;

    /**
     * Método para inflar el layout y mostrar la vista
     *
     * @param inflater           El objeto LayoutInflater que se puede utilizar para inflar
     *                           cualquier vista en el fragmento
     * @param container          Si no es nula, esta es la vista principal en la que se encuentra el fragmento.
     *                           Se debe adjuntar la interfaz de usuario (UI).  El fragmento no debe agregar la vista en sí
     *                           pero esto se puede utilizar para generar los LayoutParams de la vista.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     *                           de un estado guardado anterior como se indica aquí.
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflamos the layout para este fragment usando el binding
        binding = FragmentPersonsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que realiza configuraciones adicionales en la vista inflada anteriormente. En este caso
     * llamar al método para construir los personajes e inicializar el RecyclerView
     *
     * @param view               La vista anteriormente inflada.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     *                           de un estado guardado anterior como se indica aquí.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //inicializa la lista de personajes
        loadPersons();

        //inicializa el RecyclerView
        adapter = new PersonsRecyclerViewAdapter(getActivity(), personsList);
        binding.personsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.personsRv.setAdapter(adapter);

        // Llamar al método de MainActivity para mostrar botón hamburguesa
        if (getActivity() instanceof MainActivity) {
            MainActivity activity = (MainActivity) getActivity();
            activity.setDrawerIconVisibility(true);
        }
    }

    /**
     * Método para construir la lista de personajes
     */
    private void loadPersons() {
        personsList = new ArrayList<PersonData>();
        personsList.add(new PersonData(
                "Mario",
                getString(R.string.marioWhois),
                getString(R.string.marioDescription),
                getString(R.string.marioHabilities),
                R.drawable.mario
        ));
        personsList.add(new PersonData(
                "Luigi",
                getString(R.string.luigiWhois),
                getString(R.string.luigiDescription),
                getString(R.string.luigiHabilities),
                R.drawable.luigi
        ));
        personsList.add(new PersonData(
                "Peach",
                getString(R.string.peachWhois),
                getString(R.string.peachDescription),
                getString(R.string.peachHabilities),
                R.drawable.peach
        ));
        personsList.add(new PersonData(
                "Toad",
                getString(R.string.toadWhois),
                getString(R.string.toadDescription),
                getString(R.string.toadHabilities),
                R.drawable.toad
        ));
    }

    /**
     * Método que vamos a usar para poner a la ActionBar título relacionado con el contenido mostrado
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.titleToolbarList);

        }
    }

}