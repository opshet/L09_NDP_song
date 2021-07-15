package sg.edu.rp.c346.id20010021.l09_ndp_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class LastActivity extends AppCompatActivity {

    EditText edTitle;
    EditText edSingers;
    EditText edYear;
    EditText edId;

    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel= findViewById(R.id.btnCancel);

        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        edTitle = findViewById(R.id.edTitle);
        edSingers = findViewById(R.id.edSingers);
        edYear = findViewById(R.id.edYear);
        edId=findViewById(R.id.edId);


        Intent i =getIntent();
        final Song currentSong=(Song) i.getSerializableExtra("song");


        edId.setText(currentSong.getId()+"");
        edTitle.setText(currentSong.getTitle());
        edSingers.setText(currentSong.getSingers());
        edYear.setText(currentSong.getYear()+"");


        if(rb1.isChecked()){
            rb1.isActivated();
        }else if(rb2.isChecked()){
            rb2.isActivated();
        }else if(rb3.isChecked()){
            rb3.isActivated();
        }else if(rb4.isChecked()){
            rb4.isActivated();
        }else if(rb5.isChecked()){
            rb5.isActivated();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh=new DBhelper(LastActivity.this);
                currentSong.setTitle(edTitle.getText().toString().trim());
                currentSong.setSingers(edSingers.getText().toString().trim());
                int year= Integer.valueOf(edYear.getText().toString().trim());
                currentSong.setYear(year);

                int selectRB= rg.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton) findViewById(selectRB);
                currentSong.setStars(Integer.parseInt(rb.getText().toString()));

                int result=dbh.updateSong(currentSong);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh= new DBhelper(LastActivity.this);
                int result= dbh.deleteSong(currentSong.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
