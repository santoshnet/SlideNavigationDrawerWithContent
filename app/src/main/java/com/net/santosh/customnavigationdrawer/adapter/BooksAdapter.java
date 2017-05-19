package com.net.santosh.customnavigationdrawer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.net.santosh.customnavigationdrawer.R;
import com.net.santosh.customnavigationdrawer.model.Book;

import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {

    private List<Book> books;
    private Activity activity;

    public BooksAdapter(Activity context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.books = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_view, null, false);
        } else {
            convertView.getTag();
        }

        TextView name = (TextView) convertView.findViewById(R.id.book_name);
        TextView author = (TextView) convertView.findViewById(R.id.book_author);

        name.setText("Book name: " + getItem(position).getName());
        author.setText("Author: " + getItem(position).getAuthor());

        return convertView;
    }
}
