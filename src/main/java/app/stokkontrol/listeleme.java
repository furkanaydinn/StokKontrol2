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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class listeleme extends Fragment {
    View rootview;
    Database db;
    ListView list;
    Context mContext;
    String urunAd[],stokAdedi[];
    ArrayList<HashMap<String, String>> urunListesi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.listeleme,container,false);
        mContext = getActivity().getApplicationContext();
        list = (ListView)rootview.findViewById(R.id.list);

        getActivity().setTitle("Ürün Listeleme");

        db = new Database(mContext);
        urunListesi = db.urunler();

        if(urunListesi.size()==0){
            Toast.makeText(mContext,"Henüz Hiç Ürün Girilmemiş", Toast.LENGTH_LONG).show();
        }
        else{
            urunAd = new String[urunListesi.size()];
            stokAdedi = new String[urunListesi.size()];

            for(int i = 0 ; i< urunListesi.size() ; i++){
                urunAd[i] = urunListesi.get(i).get("urun_ad");
                stokAdedi[i] = urunListesi.get(i).get("stok_adedi");
                fill();
            }
        }
        return  rootview;
    }


    public class CustomList  extends BaseAdapter {

        @Override
        public int getCount() {
            return db.getRowCount();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_list_item,null);

            TextView txtUrunAd = (TextView)view.findViewById(R.id.txtUrunAd);
            TextView txtStok = (TextView)view.findViewById(R.id.txtStok);

            txtUrunAd.setText(urunAd[i]);
            txtStok.setText(stokAdedi[i]);

            return view;
        }
    }
    public void fill(){
        CustomList customAdapter = new CustomList();
        list.setAdapter(null);
        list.setAdapter(customAdapter);
    }

}
