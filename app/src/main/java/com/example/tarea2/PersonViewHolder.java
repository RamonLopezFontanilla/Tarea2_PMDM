package com.example.tarea2;

/**
 *
 * Esta clase infla sólo las vistas necesarias, la de los elementos visibles
 */

import androidx.recyclerview.widget.RecyclerView;
import com.example.tarea2.databinding.PersonCardviewBinding;

public class PersonViewHolder extends RecyclerView.ViewHolder{

    private final PersonCardviewBinding binding;

    /**
     * Constructor que usa el ViewBinding para simplificar el acceso a las vistas
     * dentro del diseño de cada elemento del RecyclerView
     *
     * @param binding
     *
     */
    public PersonViewHolder(PersonCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Método que se usa para enlazar los datos de un objeto PersonData a las vistas específicas
     * de un item en un RecyclerView
     *
     * @param person Objeto personaje
     *
     */
    public void bind(PersonData person){
        binding.fotoCard.setImageResource(person.getImage());
        binding.nameCard.setText(person.getName());
        binding.whoIsCard.setText(person.getWhois());
        binding.executePendingBindings(); // Asegura que se apliquen los cambios de inmediato
    }

}

