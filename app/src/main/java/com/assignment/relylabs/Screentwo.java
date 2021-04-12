package com.assignment.relylabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Screentwo extends AppCompatActivity {

    Button b;
    EditText ed1;
    EditText ed2;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screentwo);

        ed1 = findViewById(R.id.title);
        ed2 = findViewById(R.id.description);
        b = findViewById(R.id.submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
                Intent intent = new Intent(Screentwo.this, Screenone.class);
                startActivity(intent);
            }
        });
    }

    public Connection Add(){
        String ip = "13.233.252.177;";
        String classs = "net.sourceforge.jtds.jdbc.Driver";
        String db = "SongsLibrary";
        String uname = "sa";
        String password = "Impro123";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection conn = null;
        String ConnURL;

        try{
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + uname + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
            PreparedStatement pst;
            String title = ed1.getText().toString();
            String description = ed2.getText().toString();

            pst = conn.prepareStatement("insert into songs (title,description)values(?,?)");
            pst.setString(1, title);
            pst.setString(2, description);
            pst.executeUpdate();
            Toast.makeText(this, "Song Added into Library!",Toast.LENGTH_LONG).show();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

}