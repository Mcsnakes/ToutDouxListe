package fr.sp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import sp.fr.toutdouxliste.model.Tache;

/**
 * Created by Formation on 11/01/2018.
 */

public class TacheDAO implements DAOInterface<Tache> {

    private DatabaseHandler db;

    public TacheDAO(DatabaseHandler db) {
        this.db = db;
    }

    @Override
    public Tache findOneById(Long id) throws SQLiteException {

        //Exécution de la requête
        String[] params = {String.valueOf(id)};
        String sql = "SELECT id, name, etat FROM taches WHERE id=?";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, params);
        //Instanciation d'une tache
        Tache tache = new Tache();
        //hydratation d'une tache
        if(cursor.moveToNext()) {
            hydrateTache(cursor);
        }
        //Fermeture du curseur
        cursor.close();
        return tache;
    }

    private Tache hydrateTache(Cursor cursor) {

        Tache tache = new Tache();
        tache.setId(cursor.getLong(0));
        tache.setName(cursor.getString(1));
        tache.setCheck(cursor.getInt(2));

        return tache;
    }

    @Override
    public List<Tache> findAll() {

        //Initialisation de la liste des taches
        List<Tache> tacheList = new ArrayList<>();
        //Exécution de la requête SQL
        String sql = "SELECT id, name, etat FROM taches";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, null);
        //Boucle sur le curseur
        while(cursor.moveToNext()) {
            tacheList.add(hydrateTache(cursor));
        }
        //Fermeture du curseur
        cursor.close();
        return tacheList;

    }

    private ContentValues getContentValuesFromEntity(Tache entity) {

        ContentValues values = new ContentValues();
        values.put("name", entity.getName());
        values.put("etat", entity.getCheck());

        return values;
    }

    @Override
    public void persistance(Tache entity) {

        if(entity.getId() == null) {
            this.insert(entity);
        } else {
            this.update(entity);
        }
    }

    private void insert(Tache entity) {

        Long id = this.db.getWritableDatabase().insert("taches", null, this.getContentValuesFromEntity(entity));
        entity.setId(id);

    }

    private void update(Tache entity) {

        String[] params = {entity.getId().toString()};
        this.db.getWritableDatabase().update(
                "taches",
                this.getContentValuesFromEntity(entity),
                "id=?",
                params
        );

    }

}
