package fr.sp.database;

import android.database.sqlite.SQLiteException;

import java.util.List;

import sp.fr.toutdouxliste.model.Tache;

/**
 * Created by Formation on 11/01/2018.
 */

interface DAOInterface <DTO> {
    Tache findOneById(Long id) throws SQLiteException;

    List<DTO> findAll();

    void persistance(DTO entity);
}
