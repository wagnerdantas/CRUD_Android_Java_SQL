package com.example.crudapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView id= (TextView)findViewById(R.id.edittextid);
        TextView name= (TextView)findViewById(R.id.edittextname);
        TextView address= (TextView)findViewById(R.id.edittextaddress);
        Button btninsert=(Button)findViewById(R.id.btnadd);
        Button btnupdate=(Button) findViewById(R.id.btnupdate);
        Button btndelete=(Button) findViewById(R.id.btndelete);
        Button btnget=(Button) findViewById(R.id.btnget);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection=connectionclass();
                try {
                    if(connection !=null){
                        String sqlinsert="Insert into UserInfo_Tab values('"+id.getText().toString()+"','"+name.getText().toString()+"','"+address.getText().toString()+"')";
                        Statement st=connection.createStatement();
                        ResultSet rs=st.executeQuery(sqlinsert);
                    }
                }
                catch(Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection=connectionclass();
                try {
                    if(connection !=null){
                        String sqlinsert="update UserInfo_Tab set Name ='"+name.getText().toString()+"', Address = '"+address.getText().toString()+"' where ID = '"+id.getText().toString()+ "'";
                        Statement st=connection.createStatement();
                        ResultSet rs=st.executeQuery(sqlinsert);
                    }
                }
                catch(Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection=connectionclass();
                try {
                    if(connection !=null){
                        String sqldelete="delete UserInfo_Tab where ID ='"+id.getText().toString()+"'";
                        Statement st=connection.createStatement();
                        ResultSet rs=st.executeQuery(sqldelete);
                    }
                }
                catch(Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if (connection != null) {
                        String sqldelete = "select * from UserInfo_Tab where ID ='" + id.getText().toString() + "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqldelete);


                        while (rs.next()) {
                            name.setText(rs.getString(2));
                            address.setText(rs.getString(3));
                        }

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    @SuppressLint("NewApi")
    public Connection connectionclass(){
        Connection con=null;
        String ip="192.168.1.12", port="1433", username="sa", password="program71", databasename="CRUDAndroidDB";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            con= DriverManager.getConnection(connectionUrl);
        }
        catch(Exception exception){
            Log.e("Error", exception.getMessage());
        }
        return con;
    }



}