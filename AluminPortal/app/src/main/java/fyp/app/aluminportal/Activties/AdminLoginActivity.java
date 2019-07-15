package fyp.app.aluminportal.Activties;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import fyp.app.aluminportal.R;

public class AdminLoginActivity extends AppCompatActivity {

    TextView signUpTxt;
    EditText mEmailEdittxt,mPasswordEdittxt;
    Button mloginbtn;
    ProgressBar pd;
    FirebaseAuth mAuth;
    String emailStr,passworsStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        mAuth= FirebaseAuth.getInstance();
        signUpTxt=findViewById(R.id.Signup_txt_vw);
        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginActivity.this, AdminSignupActivity.class));

            }
        });
        mEmailEdittxt=findViewById(R.id.emailEd);
        mPasswordEdittxt=findViewById(R.id.passwordEd);
        mloginbtn=findViewById(R.id.loginBtn);

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailStr=mEmailEdittxt.getText().toString();
                passworsStr=mPasswordEdittxt.getText().toString();
                if (emailStr.isEmpty()){
                    mEmailEdittxt.setError("Enter is empty");
                }else if (passworsStr.isEmpty()){
                    mPasswordEdittxt.setError("Password is empty");
                }else{
                    mAuth.signInWithEmailAndPassword(emailStr,passworsStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //  startActivity(new Intent(AdminActivity.this,HomepageActivity.class));

                                Toast.makeText(AdminLoginActivity.this,"signin succesfuly",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminLoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });



    }
}
