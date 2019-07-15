package fyp.app.aluminportal.Activties;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fyp.app.aluminportal.R;
import fyp.app.aluminportal.model.Admin;

public class AdminSignupActivity extends AppCompatActivity {

    EditText mEmailEdittxt,mPasswordEdittxt,userphone,username,useradress;
    Button btn_signup;
    TextView mLogin;
    String usernameStr,emailStr,passworsStr,phoneStr,adressStr;
    FirebaseAuth auth;
    Admin model;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Admin");

        username=findViewById(R.id.UserNameET);
        mEmailEdittxt=findViewById(R.id.EmailET);
        mPasswordEdittxt=findViewById(R.id.PasswordET);
        userphone=findViewById(R.id.PhoneET);
        useradress=findViewById(R.id.AddressET);

        btn_signup=findViewById(R.id.RegsiterBtn);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameStr=username.getText().toString();
                emailStr=mEmailEdittxt.getText().toString();
                passworsStr=mPasswordEdittxt.getText().toString();
                phoneStr=userphone.getText().toString();
                adressStr=useradress.getText().toString();


                if (usernameStr.isEmpty()){
                    username.setError("Username is empty");
                }
                if (emailStr.isEmpty()){
                    mEmailEdittxt.setError("Email is empty");
                }else if (passworsStr.isEmpty()){
                    mPasswordEdittxt.setError("Password is empty");
                }else if (phoneStr.isEmpty()){
                    userphone.setError("PhoneNo is empty");
                }else  if (adressStr.isEmpty()){
                    useradress.setError("Adress is empty");
                }else
                {
                    auth.createUserWithEmailAndPassword(emailStr,passworsStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                model=new Admin(usernameStr,phoneStr,adressStr);
                                reference.push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(AdminSignupActivity.this,"Data added successfully",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AdminSignupActivity.this,AdminBottomNavActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AdminSignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminSignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });




    }
}
