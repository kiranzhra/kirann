package fyp.app.aluminportal.Activties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fyp.app.aluminportal.R;

public class MainActivity extends AppCompatActivity {

    private Button Snb;
    private Button Lnb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Snb =(Button) findViewById(R.id.Adm);
        Snb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivityS();
            }
        });

        Lnb =(Button) findViewById(R.id.lnb);
        Lnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityL();
            }
        });

    }
    public void openActivityS(){
        Intent intent=new Intent(this,AdminLoginActivity.class);
        startActivity(intent);
    }

    public void openActivityL(){
        Intent intent=new Intent(this,LonginActivity.class);
        startActivity(intent);
    }
}
