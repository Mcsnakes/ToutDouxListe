package sp.fr.toutdouxliste;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.sp.database.DatabaseHandler;
import fr.sp.database.TacheDAO;
import sp.fr.toutdouxliste.model.Tache;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ListView tacheListView;
    private TextView textViewMain;
    private Spinner spinner;
    private List<Tache> tacheList;
    private Tache selectedTache;
    private Integer selectedIndexTache;
    private TacheArrayAdapter tacheAdapter;
    private DatabaseHandler db;
    private TacheDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciation de la base de données
        db = new DatabaseHandler(this);
        //Instanciation de la DAO
        this.dao = new TacheDAO(this.db);

        //Référence au widget
        tacheListView =findViewById(R.id.tacheListView);
        textViewMain = findViewById(R.id.textViewMain);
        //Initialisation
        TacheListInit();

        spinner = findViewById(R.id.spinnerFilter);
        spinner.setOnItemSelectedListener(this);

        //récupération des données persistée dans le bundle
        if(savedInstanceState != null) {
            this.selectedIndexTache = savedInstanceState.getInt("SelectedIndex");
            if(this.selectedIndexTache != null) {
                this.selectedTache = this.tacheList.get(this.selectedIndexTache);

                tacheAdapter.setSelection(this.selectedIndexTache);
                //contactListView.requestFocusFromTouch();
                //contactListView.setSelection(this.selectedIndex);

            }
        }

    }

    private void TacheListInit() {

        //Récupération de la liste des taches
        tacheList = this.dao.findAll();
        //Création d'une tache ArrayAdapter
        tacheAdapter = new TacheArrayAdapter(this, tacheList);
        //Définition de l'adapter de notre ListView
        tacheListView.setAdapter(tacheAdapter);


    }

    public void onAddTache(View v) {

        Intent intentionAddTache = new Intent(this.getBaseContext(), NouvelleTache.class);
        startActivity(intentionAddTache);

    }


    /**
     *
     * START Zone de test
     *
     */

    public void onClick(View v) {

        //Toast.makeText(this, "click ! sur " + tacheListView.getPositionForView(v), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "click ! sur " + this.tacheListView.getTag(), Toast.LENGTH_LONG).show();

        //tacheListView.setBackgroundColor(Color.BLUE);
        //tacheListView.getSelectedItem();

        if(this.tacheListView.getTag() == null) {

            Toast.makeText(this, "null", Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if(adapterView == spinner) {
            String lsFonction = adapterView.getItemAtPosition(position).toString();

            Toast.makeText(this,  lsFonction, Toast.LENGTH_LONG).show();

            if( lsFonction.equals("ALL") ) {

                Toast.makeText(this,  "Affichage intégrale", Toast.LENGTH_LONG).show();

            } else if ( lsFonction.equals("Valider") ) {

                Toast.makeText(this, "Affichage des valider", Toast.LENGTH_LONG).show();

            } else if ( lsFonction.equals("Non valider") ) {
                Toast.makeText(this, "Affichage des non valider", Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /**
     *
     * FIN Zone de test
     *
     */
}
