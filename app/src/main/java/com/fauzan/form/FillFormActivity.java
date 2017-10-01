package com.fauzan.form;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FillFormActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgVProfilePhoto;
    private Button buttonSave;
    private EditText editTextName, editTextEmail, editTextPhoneNumber, editTextAddress;
    private Uri uriImgVProfilePhoto;
    private RadioButton radiobuttonMale, radioButtonFemale;
    private String gender;
    private final int PICK_IMAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        radiobuttonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);

        imgVProfilePhoto = (ImageView) findViewById(R.id.imageViewProfile);
        imgVProfilePhoto.setImageResource(R.drawable.profilepicturechoose);
        uriImgVProfilePhoto = Uri.parse("");


        buttonSave = (Button) findViewById(R.id.buttonSave);


        radiobuttonMale.setOnClickListener(this);
        radioButtonFemale.setOnClickListener(this);
        imgVProfilePhoto.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE:
                imgVProfilePhoto.setImageURI(data.getData());
                uriImgVProfilePhoto = data.getData();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewProfile:
                Intent intentOpenGallery = new Intent();
                intentOpenGallery.setType("image/*");
                intentOpenGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intentOpenGallery, "Select Picture"), PICK_IMAGE);
                break;
            case R.id.radioButtonMale:
                gender = radiobuttonMale.getText().toString();
                break;
            case R.id.radioButtonFemale:
                gender = radioButtonFemale.getText().toString();
                break;
            case R.id.buttonSave:
                Intent intentShowForm = new Intent(FillFormActivity.this, ShowFormActivity.class);
                Bundle fillFormBundle = new Bundle();
                fillFormBundle.putString("name", editTextName.getText().toString());
                fillFormBundle.putString("email", editTextEmail.getText().toString());
                fillFormBundle.putString("gender", gender);
                fillFormBundle.putString("phone", editTextPhoneNumber.getText().toString());
                fillFormBundle.putString("address", editTextAddress.getText().toString());
                fillFormBundle.putString("image", uriImgVProfilePhoto.toString());
                intentShowForm.putExtras(fillFormBundle);
                FillFormActivity.this.startActivity(intentShowForm);
                break;
        }
    }
}
