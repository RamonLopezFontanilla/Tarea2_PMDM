package com.example.tarea2;

/**
 *
 * Esta clase controla la Actividad Principal del proyecto, encargandose de
 * Los controles de la ActionBar, El DrawerMenu, El contenedor de fragments
 *
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;
import com.example.tarea2.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private NavController navController;

    /**
     * Método llamado antes de que la actividad inicie su ciclo de vida
     * que vamos a usar para fijar el idioma preferido
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(updateLocale(newBase));
    }

    /**
     * Método para inflar el layout y mostrar la vista
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**************************************************************************************
         *       Pantalla Splash de Bienvenida según parámetros fijados en themes.xml         *
         **************************************************************************************/
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        //ejecutar la configuración básica de la actividad antes de agregar la lógica personalizada
        super.onCreate(savedInstanceState);

        //extender el contenido de la actividad hacia los bordes de la pantalla
        EdgeToEdge.enable(this);

        //declaración del ViewBinder
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*******************************************************************************************
         *                             DrawerLayout con menú de navegación                         *
         *******************************************************************************************/
        //contenedor del menú deslizable, declarado en activity_main.xlm
        drawerLayout = findViewById(R.id.drawer_layout);

        //vista interior del menú deslizable, declarada en activity_main.xlm
        navigationView = findViewById(R.id.nav_view);

        //declaración del controlador de navegación, declarado en activity_main.xlm
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //configurar y mostrar el botón hamburguesa en la ActionBar que abre y cierra el menú deslizable
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Vinculamos la vista de navegación con el controlador de navegación
        NavigationUI.setupWithNavController(navigationView, navController);

        //Agregamos un Listener para realizar acciones personalizadas cada vez que se selecciona un elemento en el NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_home) {
                    navController.navigate(R.id.mainFragment);
                }
                if (menuItem.getItemId() == R.id.item_adjust) {
                    navController.navigate(R.id.preferencesFragment);
                }
                drawerLayout.closeDrawers(); // Cierra el drawer
                return true;
            }
        });

        /********************************************************************************************
         *                    Mensaje de bienvenida en pie de pantalla                              *
         ********************************************************************************************/
        Snackbar.make(findViewById(android.R.id.content), R.string.SnackbarText, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Método para establecer el idioma en función de la preferencia almacenada
     *
     * @param context Contexto al que se aplica
     * @return Nueva configuración del contexto
     *
     */
    public Context updateLocale(Context context) {
        // Obtener las preferencias
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String languageCode;

        // Comprobar la preferencia de idioma
        if (prefs.getBoolean("switch_english", true)) {
            languageCode = "en";
        } else {
            languageCode = "es";
        }

        // Crear el nuevo Locale
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Crear la configuración y asignar el Locale
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);

        // Aplicar la configuración sin deprecated
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.createConfigurationContext(config);
        } else {
            // En versiones anteriores, usamos updateConfiguration (aunque es deprecated)
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            return context;
        }
    }

    /**
     * Método para inflar el menú contextual en la ActionBar
     *
     * @param menu Archivo xml que contiene el menú a inflar
     * @return
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contextual,menu);
        return true;
    }

    /**
     * Método para responder a las pulsaciones en la ActionBar
     *
     * @param item Ociones de los menús
     * @return
     *
     **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //controla el menú contextual
        if(item.getItemId()==R.id.about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.about);
            builder.setMessage(R.string.aboutText);
            builder.setPositiveButton(R.string.botonAcept,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
            });
            builder.show();
        }
        //controla el menú hamburguesa
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para gestionar el paso de datos del personaje seleccionado
     *
     * @param person Objeto personaje que va a coincidir con el seleccionado
     * @param view Vista asociada al gráfico de navegación
     *
     **/
    public void personSelectioned(PersonData person, View view){
        Bundle bundle = new Bundle();
        bundle.putString("name", person.getName());
        bundle.putString("description",person.getDescription());
        bundle.putString("habilities",person.getHabilities());
        bundle.putInt("image",person.getImage());

        //mensaje Toast con la elección del personaje
        Toast.makeText(this,getString(R.string.toastText)+person.getName(),Toast.LENGTH_SHORT).show();

        //mostrar fragment de detalle del personaje
        Navigation.findNavController(view).navigate(R.id.personDetails,bundle);
    }

    /**
     * Método para mostrar u ocultar menú hamburguesa en los fragments
     * @param visible Boolean para decir si queremos o no que se muestre
     *
     **/
    public void setDrawerIconVisibility(boolean visible) {
        if (toggle != null) {
            toggle.setDrawerIndicatorEnabled(visible);
        }
    }

    /**
     * Método que implementa el botón Back con el NavController
     * @return
     *
    **/
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
