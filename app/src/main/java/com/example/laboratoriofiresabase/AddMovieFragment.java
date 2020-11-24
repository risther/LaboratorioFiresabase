package com.example.laboratoriofiresabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class AddMovieFragment extends Fragment {
    private DatabaseReference mDatabaseReference;
    private TextInputEditText movieName;
    private TextInputEditText movieLogo;
    private RatingBar mRatingBar;
    private Button bSubmit;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_movie,container,false);
        movieName = (TextInputEditText) v.findViewById(R.id.tiet_movie_name);
        movieLogo = (TextInputEditText) v.findViewById(R.id.tiet_movie_logo);
        bSubmit = (Button) v.findViewById(R.id.b_submit);
        mRatingBar = (RatingBar) v.findViewById(R.id.rating_bar);



        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(movieName) && !isEmpty(movieName)){
                    myNewMovie("53", movieName.getText().toString().trim(),movieLogo.getText().toString(),mRatingBar.getRating());
                }else{
                    if(isEmpty(movieName)){
                        Toast.makeText(getContext(), "Please enter a movie name!", Toast.LENGTH_SHORT).show();
                    }else if(isEmpty(movieLogo)){
                        Toast.makeText(getContext(), "Please specify a url for the logo", Toast.LENGTH_SHORT).show();
                    }
                }

                getActivity().onBackPressed();
            }
        });
        return v;

    }


    private void myNewMovie(String userId, String movieName, String moviePoster, float rating) {
        Movie movie = new Movie(UUID.randomUUID().toString(),movieName,moviePoster,rating);
        mDatabaseReference.child("users").child(userId).child("movies").child(movie.getId()).setValue(movie);
    }

    private boolean isEmpty(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }
}
