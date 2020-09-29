package ru.ratatoskr.racatalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//updated
public class MainActivity extends AppCompatActivity {

    public List<User> users = new ArrayList<>();
    private RecyclerView rlist;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlist = findViewById(R.id.rlist);

        users.add(new User("1", "Max", "Ivanov", 24));
        users.add(new User("2", "Semen", "Petrov", 14));
        users.add(new User("3", "Avgust", "Bader", 34));
        users.add(new User("4", "Max", "von Zudov", 54));
        users.add(new User("5", "Kris", "Kasperski", 64));
        users.add(new User("6", "Lena", "Ivanova", 27));
        users.add(new User("7", "Olga", "Pavlova", 21));
        users.add(new User("8", "Masha", "Molodova", 22));
        users.add(new User("9", "Oleg", "Denisov", 29));
        users.add(new User("10", "Tanya", "Peregudova", 23));
        users.add(new User("11", "Andrei", "Hodychkin", 54));
        users.add(new User("12", "Max", "Ivanov", 64));
        users.add(new User("13", "Alexei", "Tolstoy", 76));
        users.add(new User("14", "Max", "Brennan", 44));
        users.add(new User("15", "Maxim", "Bold", 22));
        users.add(new User("16", "Kernel", "Obu", 23));
        users.add(new User("17", "Mister", "X", 12));
        users.add(new User("18", "Double", "Trouble", 12));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlist.setLayoutManager(layoutManager);
        adapter = new MyAdapter(this,R.layout.item,users);
        rlist.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_down:
                Collections.sort(users);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.main_up:
                Collections.sort(users, Collections.reverseOrder());
                adapter.notifyDataSetChanged();
                return true;
            case R.id.main_add:
                addAUser();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addAUser() {

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);
        final EditText first = view.findViewById(R.id.first);
        final EditText last = view.findViewById(R.id.last);
        final EditText age = view.findViewById(R.id.age);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Add a user");
        builder.setCancelable(true);
        builder.setPositiveButton("Create a user", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String f = first.getText().toString();
                String l = last.getText().toString();
                int a = 0;
                try {
                    a = Integer.parseInt(age.getText().toString());
                } catch (Exception e) {
                }
                User u = new User("", f, l, a);
                users.add(u);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}