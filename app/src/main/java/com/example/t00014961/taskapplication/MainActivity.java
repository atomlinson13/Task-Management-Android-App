package com.example.t00014961.taskapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.t00014961.taskapplication.R.layout.multi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   CheckedTextView checkBox;
    private static final String TAG = "HELLO";
    private DrawerLayout mDrawerLayout;
    String selected;
    private Spinner categoriesList;
    Button btn,newList, viewLists, getParents;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    List<String> keys, listNames;
    ArrayAdapter<String> arrayAdapter;
    Spinner parentsList, catSpinner;
    String[] tasks, paths, cats;
    String content, categorySelected;
    private TextView mTextMessage;
    public EditText input;
    public int listCount, itemCount, catPosition = 0;
    ListView listContent;

    ArrayList<String> listItems = new ArrayList<String>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewLists = findViewById(R.id.viewLists);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        catSpinner = findViewById(R.id.catSpinner);

        ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Boolean> map2 = (Map<String, Boolean>) dataSnapshot.getValue();
                listNames = new ArrayList<>(map2.keySet());
                Toast.makeText(MainActivity.this, "" + keys, Toast.LENGTH_SHORT).show();
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                 Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        cats = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> adapterNew2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, cats);
        adapterNew2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapterNew2);
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = catSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, R.layout.multi, keys);
//        lv = findViewById(R.id.list);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigations);
        btn = findViewById(R.id.newList);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void getParents(View view) {
        parentsList = (Spinner) findViewById(R.id.parents_list);
        Toast.makeText(this, "" + listNames, Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapterNew = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, listNames);
        adapterNew.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        parentsList.setAdapter(adapterNew);
        parentsList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                      selected = parentsList.getSelectedItem().toString();
                                                  }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void setListBottom(View view) {
        lv = findViewById(R.id.list);
        Toast.makeText(this, "" + selected + " " + categorySelected, Toast.LENGTH_SHORT).show();
        ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.child(selected).child(categorySelected).getValue();
                keys = new ArrayList<>(map.keySet());
                Toast.makeText(MainActivity.this, "" + keys, Toast.LENGTH_SHORT).show();
                String[] stockArr = new String[keys.size()];
                stockArr = keys.toArray(stockArr);
                ArrayAdapter<String> arrayAdapter3;
                arrayAdapter3 = new ArrayAdapter<String>(lv.getContext(), multi, stockArr);
               // checkBox = (CheckedTextView) getComponentName(findViewById(R.id.text1));
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(arrayAdapter3);

               lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "IT WORKED!", Toast.LENGTH_SHORT).show();
                        //categorySelected = catSpinner.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

        });

    }


    public void createLists(View view) {
        Intent intent = new Intent(this, AddList.class);
        startActivity(intent);
   }

    @Override
    public void onClick(View v) {

    }
}
