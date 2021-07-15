package sg.edu.rp.c346.id20010021.l09_ndp_song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnShow_list;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    EditText edTitle;
    EditText edSingers;
    EditText edYear;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow_list = findViewById(R.id.btnShow_list);

        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        edTitle = findViewById(R.id.edTitle);
        edSingers = findViewById(R.id.edSingers);
        edYear = findViewById(R.id.edYear);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edTitle.getText().toString().trim();
                String singers = edSingers.getText().toString().trim();

                String year_str = edYear.getText().toString().trim();
                int year = Integer.valueOf(year_str);
                int stars = getStars();


                DBhelper dbh = new DBhelper(MainActivity.this);
                long result = dbh.insertSong(title, singers, year, stars);


            }
        });


        btnShow_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        ShowActivity.class);
                startActivity(i);


            }
        });


    }

    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb1:
                stars = 1;
                break;
            case R.id.rb2:
                stars = 2;
                break;
            case R.id.rb3:
                stars = 3;
                break;
            case R.id.rb4:
                stars = 4;
                break;
            case R.id.rb5:
                stars = 5;
                break;
        }
        return stars;
    }
}