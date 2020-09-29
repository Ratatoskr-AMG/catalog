package ru.ratatoskr.racatalog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    public List<User> users;

    static MyAdapter getInstance(@NonNull Context context, int resource, List<User> users){

        return new MyAdapter(context,resource,users);

    }

    public MyAdapter(@NonNull Context context, int resource, List<User> users) {
        this.users = users;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener  {

        public LinearLayout wrapper;
        public TextView First;
        public TextView Last;
        public TextView Age;
        public MyAdapter MyAdapter;;

        public MyViewHolder(LinearLayout v, MyAdapter context) {
            super(v);
            MyAdapter=context;
            First = v.findViewById(R.id.first);
            Last = v.findViewById(R.id.last);
            Age = v.findViewById(R.id.age);
            wrapper = v;
            wrapper.setOnCreateContextMenuListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Integer position = getAdapterPosition();
            switch (menuItem.getItemId())
            {
                case 1:
                    MyAdapter.deleteItemByPosition(position);
                    return true;
            }

            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem item = contextMenu.add(0, 1,100,"Delete");
            item.setOnMenuItemClickListener(this);
        }

    }

    public void deleteItemByPosition(int position){
        notifyItemRemoved(position);
        users.remove(position);
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.ritem, parent, false);
        MyViewHolder vh = new MyViewHolder(v,this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.First.setText(user.getFirstname());
        holder.Last.setText(user.getLastname());
        holder.Age.setText(String.valueOf(user.getAge()));
        holder.wrapper.setTag(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
