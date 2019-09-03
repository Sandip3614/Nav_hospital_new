package sk.nav_hospital;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InformActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5;
    RatingBar rb;
    Button button;
    Intent intent;
    Database myDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        intent = this.getIntent();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String place_name = bundle.getString("place_name");

        myDb = new Database(this);

        myDb.insertData("KIST Medical College Teaching Hospital", "Patan", "500", "17", "Cash | Credit card | Debit card | Insurance", 27.666485, 85.334056, "3.5", "");

        myDb.insertData("Red Cross Balkot Sub-branch", "Anantalingeshwor", "450", "14", "Cash | Credit card | Debit card | Insurance", 27.669865, 85.365955, "4.5", "");

        myDb.insertData("Patan Hospital", "Lagankhel Satdobato Road,Patan", "1100", "18", "Cash | Credit card | Debit card | Insurance", 27.668530, 85.320504, "4.5", "");

        myDb.insertData("अल्का अस्पताल", "Patan", "900", "16", "Cash | Credit card | Debit card | Insurance", 27.674640, 85.315154, "3.5", "");

        myDb.insertData("Vayodha Hospitals", "Balkhu,Kathmandu", "1500", "23", "Cash | Credit card | Debit card | Insurance", 27.684648, 85.298331, "4.6", "");

        myDb.insertData("Norvic International Hospital", "Kathmandu", "850", "13", "Cash | Credit card | Debit card | Insurance", 27.690305, 85.319059, "4.5", "");

        myDb.insertData("Civil Service Hospital Of Nepal", "Minbhawan marg minbhawan,Kathmandu", "2200", "12", "Cash | Credit card | Debit card | Insurance", 27.686641, 85.339036, "4.5", "");

        myDb.insertData("Kathmandu Medical College", "Sinamangal Road,Kathmandu", "900", "15", "Cash | Credit card | Debit card | Insurance", 27.696126, 85.353402, "4.5", "");

        myDb.insertData("Teku Hospital(Shukraraaj Tropical & Infectious Disease Hospital)", "Teku", "1200", "14", "Cash | Credit card | Debit card | Insurance", 27.695720, 85.307016, "3.5", "");

        myDb.insertData("Tilganga Eye Hospital", "Ring Road,Kathmandu", "1000", "16", "Cash | Credit card | Debit card | Insurance", 27.705672, 85.351152, "4.8", "");

        myDb.insertData("Bir Hospital", "Kantipath,Kathmandu", "1800", "10", "Cash | Credit card | Debit card | Insurance", 27.704916, 85.313661, "4.5", "");

        myDb.insertData("Om Hospital & Research Center", "Chabahil Sadak,Kathmandu", "890", "12", "Cash | Credit card | Debit card | Insurance", 27.721752, 85.344794, "3.5", "");

        myDb.insertData("Medicare National Hospital and Research Center", "Ring Road,Kathmandu", "560", "13", "Cash | Credit card | Debit card | Insurance", 27.718632, 85.346425, "4.6", "");


        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
        tv3 = findViewById(R.id.text3);
        tv4 = findViewById(R.id.text4);
        tv5 = findViewById(R.id.text5);
        rb = findViewById(R.id.text6);

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String[] listItems = {"By Vehicles ", "Walking"};

                AlertDialog.Builder builder = new AlertDialog.Builder(InformActivity.this);
                builder.setTitle("Select Direction Mode");

                int checkedItem = 0; //this will checked the item when user open the dialog
                builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + place_name + "&mode=" + "d");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent);
                                break;
                            case 1:
                                Uri gmmIntentUri2 = Uri.parse("google.navigation:q=" + place_name + "&mode=" + "w");
                                Intent mapIntent2 = new Intent(Intent.ACTION_VIEW, gmmIntentUri2);
                                mapIntent2.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent2);
                                break;

                        }

                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        Cursor res = myDb.getSpecificCol(place_name);

        while (res.moveToNext()) {

            tv1.setText(res.getString(0));
            tv2.setText(res.getString(1));
            tv3.setText(res.getString(2));
            tv4.setText(res.getString(3));
            tv5.setText(res.getString(4));
            rb.setRating(res.getInt(7));
        }

    }
}
