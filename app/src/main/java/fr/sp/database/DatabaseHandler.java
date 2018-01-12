package fr.sp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Formation on 11/01/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tache_database";
    private static final int DATABASE_VERSION = 1;

    private static final String CONTACT_TABLE_SQL = "CREATE TABLE taches(" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT NOT NULL, "+
            " etat INTEGER NOT NULL )";

    /**
     * etat = 1
     * en attente de validation
     * etat = 2
     * tache valider
     */

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Affichage de test
        sqLiteDatabase.execSQL(CONTACT_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //supprime la table tache si elle existe
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tache");
        //table re crée
        this.onCreate(sqLiteDatabase);

    }
}
