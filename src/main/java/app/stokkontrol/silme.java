package app.stokkontrol;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class silme extends Fragment {
    View rootview;
    Database db;
    Button delete;
    Context mContext;
    Spinner spinner;
    String urunAD;
    ArrayAdapter<String> dataAdapterSpinner;
    ArrayList<HashMap<String, String>> urunListesi;
    public ArrayList<String> urunAd = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.silme,container,false);
        mContext = getActivity().getApplicationContext();

        spinner = (Spinner)rootview.findViewById(R.id.spinner);
        getActivity().setTitle("Ürün Silme");
        db = new Database(mContext);
        delete = (Button)rootview.findViewById(R.id.delete);

        urunListesi = db.urunler();

        if(urunListesi.size()==0)
        {
            Toast.makeText(mContext,"Silme Yapılamaz...Henüz Hiç Ürün Girilmemiş", Toast.LENGTH_LONG).show();
        }
        else
        {

            for(int i = 0 ; i< urunListesi.size() ; i++){
                urunAd.add(i, urunListesi.get(i).get("urun_ad"));
            }
        }
        dataAdapterSpinner = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,urunAd);
        spinner.setAdapter(dataAdapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                urunAD=urunAd.get(position);
                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.urunSil(Integer.parseInt(db.getID("\"" + urunAD + "\"")));
                Toast.makeText(mContext,urunAD+" İsimli Ürün Başarıyla Silindi", Toast.LENGTH_LONG).show();
            }
        });



        return  rootview;
    }




}
