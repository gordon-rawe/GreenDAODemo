package com.gordon.rawe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.generator.gordon.greendaodemo.R;
import com.gordon.rawe.db.DBManager;
import com.gordon.rawe.models.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View add, all,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        all = findViewById(R.id.all);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        all.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                logPeople(DBManager.getInstance().getAllPeople());
                break;
            case R.id.add:
                DBManager.getInstance().createPerson("gordon", "rawe");
                break;
            case R.id.delete:
                DBManager.getInstance().deleteAllPeople();
                break;
        }
    }

    public void logPeople(List<Person> people) {
        for (Person person : people) {
            Log.d("gordon", person.toString());
        }
    }
}
