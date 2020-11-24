package com.example.laboratoriofiresabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditMovie extends AppCompatActivity {
    public static final String idmovies = "idmovies";;
    private TextInputEditText movieName;
    private TextInputEditText movieLogo;
    private RatingBar mRatingBar;
    private Button update;
    private TextView txtidmovies;
    private static final String userId = "53";

    DatabaseReference MoviesRef;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        movieName = (TextInputEditText) findViewById(R.id.tiet_movie_nameU);
        movieLogo = (TextInputEditText) findViewById(R.id.tiet_movie_logoU);
        update = (Button) findViewById(R.id.update);
        mRatingBar = (RatingBar) findViewById(R.id.rating_barU);
        txtidmovies =(TextView)findViewById(R.id.idMovie);

        String idmovies = getIntent().getStringExtra("idmovies");
        txtidmovies.setText(idmovies);
        txtidmovies.setVisibility(View.INVISIBLE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference();

        MoviesRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("movies");

        MoviesRef.child(idmovies).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    movieName.setText(dataSnapshot.child("movieName").getValue().toString());
                    movieLogo.setText(dataSnapshot.child("moviePoster").getValue().toString());
                    mRatingBar.setRating(Float.parseFloat(dataSnapshot.child("movieRating").getValue().toString()));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ;
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(movieName) && !isEmpty(movieName)){
                    myNewMovie(txtidmovies.getText().toString(), movieName.getText().toString().trim(),movieLogo.getText().toString(),mRatingBar.getRating());
                }else{
                    if(isEmpty(movieName)){

                    }else if(isEmpty(movieLogo)){

                    }
                }
                Intent intent = new Intent(com.example.laboratoriofiresabase.EditMovie.this, com.example.laboratoriofiresabase.MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void myNewMovie(String idmovies, String movieName, String moviePoster, float rating) {
        Movie movie = new Movie(idmovies,movieName,moviePoster,rating);
        mDatabaseReference.child("users").child(userId).child("movies").child(movie.getId()).setValue(movie);
    }

    private boolean isEmpty(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

}
