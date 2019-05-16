package app.stokkontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ekleme extends Fragment {
    View rootview;
    Database db;
    Button ekle;
    Context mContext;
    EditText edtUrunAd,edtStok;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.ekleme,container,false);
        mContext = getActivity().getApplicationContext();

        getActivity().setTitle("Ürün Ekleme");
        db = new Database(mContext);
        ekle = (Button)rootview.findViewById(R.id.ekle);
        edtUrunAd = (EditText)rootview.findViewById(R.id.edtUrunAd);
        edtStok = (EditText) rootview.findViewById(R.id.edtStok);



        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urunAd = edtUrunAd.getText().toString();
                String urunStok = edtStok.getText().toString();

                db.urunEkle(urunAd,urunStok);
                Toast.makeText(mContext,urunAd+" İsimli Ürün Başarıyla Eklendi", Toast.LENGTH_LONG).show();
            }
        });



        return  rootview;
    }




}
