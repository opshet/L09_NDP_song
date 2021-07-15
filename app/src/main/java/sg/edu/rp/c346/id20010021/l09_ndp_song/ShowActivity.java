package sg.edu.rp.c346.id20010021.l09_ndp_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> songlist;
    ArrayAdapter adapter;
    Button btn5star;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btn5star = (Button) this.findViewById(R.id.btn5star);

        DBhelper DBhelper = new DBhelper(this);
        lv = findViewById(R.id.lv);

        songlist = new ArrayList<>();
        songlist = DBhelper.getAllSongs();
        DBhelper.close();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songlist);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ShowActivity.this, LastActivity.class);
                Song details = songlist.get(position);
                i.putExtra("details",details);
                startActivity(i);
            }
        });


        btn5star.setOnClickListener((v -> {
            DBhelper dbh = new DBhelper(ShowActivity.this);
            songlist.clear();
            songlist.addAll(dbh.getAllSongs());
            adapter.notifyDataSetChanged();
        }));
    }


}


