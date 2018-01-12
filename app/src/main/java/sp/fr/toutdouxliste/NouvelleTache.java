package sp.fr.toutdouxliste;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.sp.database.DatabaseHandler;

public class NouvelleTache extends AppCompatActivity {

    private EditText editTextName;
    private String tacheId;
    private static final Integer ETAT_INITIALE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_tache);

        android.app.ActionBar actionBar = getActionBar();

        if(actionBar != null) {
            //Fèche de retour à l'activité principal
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextName = (EditText)findViewById(R.id.textViewNouvelleTache);

    } //onCreate

    public void onValide(View v) {

        //Récupération de la saisie de l'utilisateur
        String editTextName = ((EditText)findViewById(R.id.textViewNouvelleTache)).getText().toString();

        //Instanciation de la connexion à la base de donnée
        DatabaseHandler db = new DatabaseHandler(this);

        //Définition des données à inserer
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", editTextName);
        insertValues.put("etat", ETAT_INITIALE);

        //Insertion des données
        try {

            Long insertId = db.getWritableDatabase().insert("taches", null, insertValues);
            Toast.makeText(this, "Enregistrement réussi || id : " + insertId, Toast.LENGTH_LONG).show();


        } catch(SQLiteException ev) {

            Toast.makeText(this, "Echec de l'enregistrement", Toast.LENGTH_LONG).show();

        }


    }//onValide

} // NouvelleTache
