package com.example.t00014961.taskapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Lists {

    public String title;
    public Map<String,Boolean> list;
   // FirebaseStorage db;
    public FirebaseDatabase db = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("message");


    public Lists(String title, Map<String,Boolean> categories) {
        this.title = title;
        this.list = categories;
        myRef.setValue("Hello, World");
    }


//    public void addItem() {
//        list.put("RollNo","1");
//
//    }
//
//    public void removeItem() {
//
//
//    }
    public void examplePosts() {

        HashMap<String,String> student = new HashMap<>();

        student.put("RollNo","1");

        student.put("Name","Jayesh");

        myRef.setValue(student);

//        Map<String, Boolean> categories = new HashMap<>();
//        categories.put("technology", true);
//        categories.put("opinion", true);
//        categories.put("cats", true);
//
//        Lists myMapPost = new Lists("My great post", categories);
//        db.
//        db.collection("cities")
//                .add(categories)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
////                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });


//        DocumentReference newCityRef = collection("cities").document();
//        newCityRef.set(categories);
    }


}