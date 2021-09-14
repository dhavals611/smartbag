package dupd.com.smartbag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView bookname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("rfid");

        bookname = (TextView)findViewById(R.id.book);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {


                        bag b = new bag();
                        Toast.makeText(getApplicationContext(), "iiii", Toast.LENGTH_LONG).show();
                      //  System.out.println("datas" + ds);
                       // b.setBookname(ds.getValue(bag.class).getBookname());
                       /* b.setBookname(ds.getValue(bag.class).getBookname());
                        bookname.setText(b.getBookname());

*/



                      // System.out.println("further"+ds.getValue());
                       for(DataSnapshot d :ds.getChildren())
                       {
                       //    System.out.println("further"+d.getValue());
                           if(d.getKey().equals("Book Name"))
                           {
                               if(d.getValue().toString().equals(""))
                               {
                                   Toast.makeText(getApplicationContext(),"Book is Blank",Toast.LENGTH_LONG).show();
                               //    System.out.println("blank");
                                   opendialog();
                               }
                               else
                               bookname.setText(d.getValue().toString());
                           }

                       }


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {


                    bag b = new bag();
                    Toast.makeText(getApplicationContext(), "iiii", Toast.LENGTH_LONG).show();
                  //  System.out.println("datas" + ds);
                    // b.setBookname(ds.getValue(bag.class).getBookname());
                       /* b.setBookname(ds.getValue(bag.class).getBookname());
                        bookname.setText(b.getBookname());

*/



                    System.out.println("further"+ds.getValue());
                    for(DataSnapshot d :ds.getChildren())
                    {
                        System.out.println("further"+d.getValue());
                        if(d.getKey().equals("Book Name"))
                        {
                            if(d.getValue().toString().equals(""))
                            {
                                Toast.makeText(getApplicationContext(),"Book is Blank",Toast.LENGTH_LONG).show();
                                System.out.println("blank");
                                opendialog();
                            }
                            else
                                bookname.setText(d.getValue().toString());
                        }

                    }


                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void opendialog()
    {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"Dialog");
    }
}
