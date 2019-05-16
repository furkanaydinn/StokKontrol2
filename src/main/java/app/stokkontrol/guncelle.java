package app.stokkontrol;

import android.content.Context;
import android.content.Intent;
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

public class guncelle extends Fragment {
    View rootview;
    Database db;
    Button guncel;
    Context mContext;
    EditText edtUrunAd,edtStok;
    Spinner spinner;
    String urunAD;
    ArrayAdapter<String> dataAdapterSpinner;
    ArrayList<HashMap<String, String>> urunListesi;
    public ArrayList<String> urunAd = new ArrayList<>();
    public ArrayList<String> stokAdedi = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.guncelleme,container,false);
        mContext = getActivity().getApplicationContext();

        spinner = (Spinner)rootview.findViewById(R.id.spinner);

        getActivity().setTitle("Ürün Güncelleme");
        db = new Database(mContext);
        guncel = (Button)rootview.findViewById(R.id.guncelle);
        edtUrunAd = (EditText)rootview.findViewById(R.id.edtUrunAd);
        edtStok = (EditText) rootview.findViewById(R.id.edtStok);

        urunListesi = db.urunler();

        if(urunListesi.size()==0)
        {
            Toast.makeText(mContext,"Güncelleme Yapılamaz...Henüz Hiç Ürün Girilmemiş", Toast.LENGTH_LONG).show();
        }
        else
        {

            for(int i = 0 ; i< urunListesi.size() ; i++){

                urunAd.add(i, urunListesi.get(i).get("urun_ad"));
                stokAdedi.add(i,urunListesi.get(i).get("stok_adedi"));
            }
        }
        dataAdapterSpinner = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,urunAd);
        spinner.setAdapter(dataAdapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtStok.setText(stokAdedi.get(position));
                urunAD=urunAd.get(position);
                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urunStok = edtStok.getText().toString();

                db.urunGuncelle(urunAD,urunStok,Integer.parseInt(db.getID("\"" + urunAD + "\"")));
                Toast.makeText(mContext,urunAD+" İsimli Ürün Başarıyla Güncellendi", Toast.LENGTH_LONG).show();
            }
        });



        return  rootview;
    }




}
