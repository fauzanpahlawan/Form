package com.fauzan.form;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFormActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView[] textViews, textViewEmail, textViewGender, textViewPhoneNumber, textViewAddress;
    private ImageView imageViewProfile;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_form);

        textViews = new TextView[5];
        textViews[0] = (TextView) findViewById(R.id.textViewName);
        textViews[1] = (TextView) findViewById(R.id.textViewEmail);
        textViews[2] = (TextView) findViewById(R.id.textViewGender);
        textViews[3] = (TextView) findViewById(R.id.textViewPhoneNumber);
        textViews[4] = (TextView) findViewById(R.id.textViewAddress);
        imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);

        Intent fillFormActivity = this.getIntent();
        Bundle fillFormActivityExtras = fillFormActivity.getExtras();
        String[] extras = new String[5];
        extras[0] = fillFormActivityExtras.getString("name");
        extras[1] = fillFormActivityExtras.getString("email");
        extras[2] = fillFormActivityExtras.getString("gender");
        extras[3] = fillFormActivityExtras.getString("phone");
        extras[4] = fillFormActivityExtras.getString("address");

        Uri uriExtra = Uri.parse(fillFormActivityExtras.getString("image"));

        if (uriExtra.toString().equals("")) {
            imageViewProfile.setImageResource(R.drawable.profilepicturenone);
        } else {
            imageViewProfile.setImageURI(uriExtra);
        }

        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(this);

        for (int i = 0; i < textViews.length; i++) {

            textViews[i].setText(extras[i]);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                Intent intentBack = new Intent(ShowFormActivity.this, FillFormActivity.class);
                ShowFormActivity.this.startActivity(intentBack);
        }
    }
}
