package fyp.app.aluminportal.Activties;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import fyp.app.aluminportal.model.user;

public class SignupActivity extends AppCompatActivity {
    EditText mEmailEdittxt,mPasswordEdittxt,userphone,username,useradress,userpassingyear;
    Button btn_signup;
    TextView mLogin;
    String usernameStr,emailStr,passworsStr,phoneStr,adressStr,passingyearStr;
    Spinner studentType;
    FirebaseAuth auth;
    user model;
    String mSelectedStudentType;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("user");

        username=findViewById(R.id.UserNameET);
        mEmailEdittxt=findViewById(R.id.EmailET);
        mPasswordEdittxt=findViewById(R.id.PasswordET);
        userphone=findViewById(R.id.PhoneET);
        useradress=findViewById(R.id.AddressET);
        userpassingyear=findViewById(R.id.PYearET);
        btn_signup=findViewById(R.id.RegsiterBtn);
        studentType=findViewById(R.id.spinner1);
        studentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedStudentType=studentType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameStr=username.getText().toString();
                emailStr=mEmailEdittxt.getText().toString();
                passworsStr=mPasswordEdittxt.getText().toString();
                phoneStr=userphone.getText().toString();
                adressStr=useradress.getText().toString();
                passingyearStr=userpassingyear.getText().toString();

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
                }else if (passingyearStr.isEmpty()){
                    userpassingyear.setError("Passingyear is empty");
                }else
                {
                    auth.createUserWithEmailAndPassword(emailStr,passworsStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                model=new user(usernameStr,phoneStr,adressStr,2019,mSelectedStudentType);
                                reference.push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignupActivity.this,"sucessfully",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this,HomepageActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });



















    }
}
