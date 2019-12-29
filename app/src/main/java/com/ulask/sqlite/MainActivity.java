package com.ulask.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextPhone;
    public Button btnSave, btnList, btnDelete, btnEdit;
    private ListView listView;
    //int findId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPhone = findViewById(R.id.editTextPhone);

        btnSave = findViewById(R.id.btnSave);
        btnList = findViewById(R.id.btnList);
        btnDelete = findViewById(R.id.btnDelete);

        listView = findViewById(R.id.listView);

        //Save Data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comingName = editTextName.getText().toString();
                String comingSurname = editTextSurname.getText().toString();
                String comingPhone = editTextPhone.getText().toString();

                Database db = new Database(MainActivity.this);
                db.SaveData(comingName, comingSurname, comingPhone);
                List();
            }
        });

        //List Data
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(MainActivity.this);
                List<String> list = db.ListData();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
                listView.setAdapter(adapter);
                List();
            }
        });

        //Delete Data
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comingName = editTextName.getText().toString();
                Database db = new Database(MainActivity.this);
                db.DeleteData(comingName);
                //List again after deleting
                List();
            }
        });

    }

    //List Data
    public void List() {
        Database db = new Database(MainActivity.this);
        List<String> list = db.ListData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
    }

}
