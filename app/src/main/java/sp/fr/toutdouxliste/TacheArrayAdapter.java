package sp.fr.toutdouxliste;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import sp.fr.toutdouxliste.model.Tache;

/**
 * Created by Formation on 11/01/2018.
 */

public class TacheArrayAdapter extends ArrayAdapter{

    private Activity context;
    private List<Tache> data;
    private LayoutInflater inflater;

    public TacheArrayAdapter(@NonNull Context context,@NonNull List<Tache> data) {
        super(context, 0, data);

        this.data = data;
        this.context = (Activity) context;
        this.inflater = this.context.getLayoutInflater();

    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        convertView = inflater.inflate(R.layout.activity_main, null);

        //Insticiation de la vue
        View view = this.inflater.inflate(R.layout.tache_list_view, parent, false);

        //Récupération des données d'une ligne
        Tache contactData = this.data.get(position);

        //Liaison entre les données et la vue
        TextView firstNameTextView = view.findViewById(R.id.ListeTacheName);
        firstNameTextView.setText(contactData.getName());

        CheckBox checkBoxIdView = view.findViewById(R.id.checkBoxId);
        //Conversion en boolean
        boolean e = (contactData.getCheck() != 0);
        checkBoxIdView.setChecked(e);

        convertView.setTag( checkBoxIdView );


        return view;

    }

    public void setSelection(Integer selectionIndex) {
        //this.selection = selection;
    }

}
