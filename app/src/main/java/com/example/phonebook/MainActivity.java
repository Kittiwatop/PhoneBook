package com.example.phonebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phonebook.Adaptor.ContactListAdaptor;
import com.example.phonebook.db.DatabaseHelper;
import com.example.phonebook.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDB;


    private ArrayList<Contact> mContactList = new ArrayList<>(); //เก็บ contact

    private ListView mContactListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);
        mDB = mHelper.getWritableDatabase();//return ผลลัพธ์ เป็น database
        mContactListView = (ListView) findViewById(R.id.contact_list_view); //เชื่อมส่งข้อมูลให้ ListView
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = mDB.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        mContactList.clear();

        while (cursor.moveToNext()) { // moveToNext ถ้าไม่เจอข้อมูลจะ return fault

            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
            String image = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGE));

            Contact contact = new Contact(name, phone, image);//สร้าง ข้อมูล
            mContactList.add(contact);//ยัดข้อมูลเข้า ArrayList
        }

        ContactListAdaptor adapter = new ContactListAdaptor(
                this,
                R.layout.list_item,
                mContactList);

        mContactListView.setAdapter(adapter);//หน้าที่ Adapter ยัดข้อมูลใส่ layout แล้วส่งกลับ ListView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add){
            Intent intent =new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
