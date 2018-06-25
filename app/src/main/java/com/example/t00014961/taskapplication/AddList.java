package com.example.t00014961.taskapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddList extends AppCompatActivity {

    private Spinner categoriesList;
    Button btn,newList, saveList;
    ListView listContent;

    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter2;

    private FirebaseAuth mAuth;
    String[] currentList, paths;
    String content, categorySelected, selected, title;
    private TextView mTextMessage;
    public EditText input, listInput;
    public int listCount, itemCount, catPosition = 0;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        listContent = findViewById(R.id.listContent);
        listInput = findViewById(R.id.listInput);
        saveList = findViewById(R.id.saveList);

        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);

        listContent.setAdapter(adapter2);
        //listContent.setListAdapter(adapter2);

//        title = input.getText().toString();
        input = findViewById(R.id.editText3);

        categoriesList = (Spinner) findViewById(R.id.categories_list);
        paths = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddList.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesList.setAdapter(adapter);

        categoriesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                selected = categoriesList.getSelectedItem().toString();
                catPosition = position;
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItem(View v) {
        String item =  listInput.getText().toString();
        listItems.add(item);
        listInput.getText().clear();

        adapter2.notifyDataSetChanged();
    }

    public void commitList(View v) {
        //newList = findViewById(R.id.newList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        title = input.getText().toString();

        Log.v("potato", "list has : " + listItems.size());

        for(String item : listItems) myRef.child(title).child(selected).child(item).setValue(false);

        finish();
//
//        myRef.child("" + categorySelected).child("Item " + itemCount++).setValue("Apples");
//        myRef.child("" + categorySelected).child("Item " + itemCount++).setValue("Oranges");
//        myRef.child("" + categorySelected).child("Item " + itemCount++).setValue("Bananas");
//        myRef.child("" + categorySelected).child("Item " + itemCount++).setValue("Bread");
//        myRef.child("" + categorySelected).child("Item " + itemCount++).setValue("Eggs");

    }

    public void setListAdapter(ArrayAdapter<String> listAdapter) {
        this.listAdapter = listAdapter;
    }
}
