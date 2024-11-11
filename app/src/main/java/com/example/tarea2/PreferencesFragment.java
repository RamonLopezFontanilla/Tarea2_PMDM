package com.example.tarea2;

/**
 * Esta clase gestiona la vista del ajustes de idioma de la App: fragment_person_details.xml
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;

public class PreferencesFragment extends PreferenceFragmentCompat {
    private SharedPreferences sharedPreferences;

    /**
     *
     * Método para definir y configurar la jerarquía de preferencias del fragmento.
     * @param savedInstanceState Contiene el estado guardado previamente del fragmento.
     * @param rootKey Representa la clave raíz de la pantalla de preferencias del fragmento
     *
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SwitchPreferenceCompat switchPreference = findPreference("switch_english");

        // Al usar el switch, reinicia la activity
        if (switchPreference != null) {
            switchPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                return true;
            });
        }
    }

    /**
     *
     * Método que realiza configuraciones adicionales en la vista inflada anteriormente.
     *
     * @param view La vista anteriormente inflada.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     * de un estado guardado anterior como se indica aquí.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Llamar al método de MainActivity para ocultar menú hamburguesa
        if (getActivity() instanceof MainActivity) {
            MainActivity activity = (MainActivity) getActivity();
            activity.setDrawerIconVisibility(false);
        }
    }

    /**
     *  Método que vamos a usar para poner a la ActionBar título relacionado con el contenido mostrado
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.titleToolbarAdjust);
        }
    }
}