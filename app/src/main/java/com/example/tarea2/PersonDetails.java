package com.example.tarea2;

/**
 * Esta clase gestiona la vista de la SharedPreferences: xml/preferences.xml, que a su vez contiene
 * otra vista llamada preferences_header.xml
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tarea2.databinding.FragmentPersonDetailsBinding;

public class PersonDetails extends Fragment {
    private FragmentPersonDetailsBinding binding;

    /**
     *
     * Método para inflar el layout y mostrar la vista
     *
     * @param inflater El objeto LayoutInflater que se puede utilizar para inflar
     * cualquier vista en el fragmento
     * @param container Si no es nula, esta es la vista principal en la que se encuentra el fragmento.
     * Se debe adjuntar la interfaz de usuario (UI).  El fragmento no debe agregar la vista en sí
     * pero esto se puede utilizar para generar los LayoutParams de la vista.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     * de un estado guardado anterior como se indica aquí.
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflamos the layout para este fragment usando el binding
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     *
     * Método que realiza configuraciones adicionales en la vista inflada anteriormente. En este caso
     * rellenará los campos de texto e imagenes con los datos recibidos en el Bundle del personaje seleccionado en el RecyclerView
     *
     * @param view La vista anteriormente inflada.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     * de un estado guardado anterior como se indica aquí.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            //guardamos en variables los datos recibidos del bundle
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String habilities = getArguments().getString("habilities");
            int image = getArguments().getInt("image");

            //rellenamos los campos de la vista con el valor de las variables anteriores
            binding.personName.setText(name);
            binding.personDescription.setText(description);
            binding.personHabilities.setText(habilities);
            binding.fotoPerson.setImageResource(image);

            // Llamamos al método de MainActivity para ocultar botón hamburguesa
            if (getActivity() instanceof MainActivity) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setDrawerIconVisibility(false);
            }
        }
    }

    /**
     *  Método que vamos a usar para poner a la ActionBar título relacionado con el contenido mostrado
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.titleToolbarDetails);
        }
    }
}