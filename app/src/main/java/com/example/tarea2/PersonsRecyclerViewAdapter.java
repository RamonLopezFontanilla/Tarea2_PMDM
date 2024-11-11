package com.example.tarea2;

/**
 * Esta clase actúa como intermediario entre los datos que queremos mostrar y las vistas
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea2.databinding.PersonCardviewBinding;

import java.util.ArrayList;

public class PersonsRecyclerViewAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private final ArrayList<PersonData> persons;
    private final Context context;

    /**
     * Constructor del adaptador
     *
     * @param context Contexto donde se va a crear el adaptador
     * @param persons ArrayList con los datos de los personajes
     *
     */
    public PersonsRecyclerViewAdapter(Context context, ArrayList<PersonData> persons) {
        this.context = context;
        this.persons = persons;
    }

    /**
     * Este método crea las vistas necesarias para cada elemento de la lista
     *
     * @param parent   El grupo de vistas al que se agregará la nueva vista
     *                 después de vincularla a una posición de adaptador.
     * @param viewType El tipo de vista de la nueva vista.
     * @return
     *
     */
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonCardviewBinding binding = PersonCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonViewHolder(binding);
    }

    /**
     * Este método vincula los datos de un personaje específico a la vista correspondiente
     * que se ha creado en el método `onCreateViewHolder`.
     *
     * @param holder   El ViewHolder que debe ser actualizado para mostrar el contenido del
     *                 elemento en la posición dada en el conjunto de datos.
     * @param position La posición del elemento dentro del conjunto de datos del adaptador.
     *
     */
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonData currentPerson = this.persons.get(position);
        holder.bind(currentPerson);
        holder.itemView.setOnClickListener(view -> itemClicked(currentPerson, view));
    }

    /**
     * Método que envía al MainActivity el personaje seleccionado para que muestre el fragment
     * con todos los detalles de éste.
     *
     * @param currentPerson Personaje de la lista seleccionado.
     * @param view
     *
     */
    private void itemClicked(PersonData currentPerson, View view) {
        ((MainActivity) context).personSelectioned(currentPerson, view);
    }

    /**
     * Método que cuenta el número de personajes que tenemos en nuestra lista
     * @return Número de personajes en la lista
     *
     */
    @Override
    public int getItemCount() {
        return persons.size();
    }
}
