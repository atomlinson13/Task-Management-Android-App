package com.example.t00014961.taskapplication;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

/**
 * Created by T00014961 on 6/11/2018.
 */

abstract class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
    protected final String mEmail;
    protected final String mPassword;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    public FirebaseAuth mAuth;
    UserLoginTask(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    protected void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //   showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Signed in succesful", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithCredential:success");
                          //  FirebaseUser user = mAuth.getCurrentUser();
                          //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                           // updateUI(null);
                            Toast.makeText(this, "Signed in unsuccesful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        //  hideProgressDialog();
        if (user != null) {
            //       mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            //     mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            //   findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            //      findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            //   mStatusTextView.setText(R.string.signed_out);
            //  mDetailTextView.setText(null);

            //   findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //   findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();

    }
}
