package com.net.santosh.customnavigationdrawer.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.net.santosh.customnavigationdrawer.R;
import com.net.santosh.customnavigationdrawer.adapter.BooksAdapter;
import com.net.santosh.customnavigationdrawer.adapter.BottomSheetAdapter;
import com.net.santosh.customnavigationdrawer.model.Book;

import java.util.ArrayList;

public class BottomSheetActivity extends AppCompatActivity {

    private ListView listView;
    private GridView bottomSheet;
    private ArrayAdapter<Integer> bottomSheetAdapter;
    private ArrayAdapter<Book> adapter;
    private ArrayList<Book> books;
    private Book selectedBook;
    private BottomSheetBehavior sheetBehavior;
    private Integer[] bottomItems = {R.drawable.add, R.drawable.mail, R.drawable.delete, R.drawable.facebook,
            R.drawable.google_plus, R.drawable.twitter};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        getSupportActionBar().setTitle("Bottom Sheet");
        createBooksData();

        listView = (ListView) findViewById(R.id.list_view);
        bottomSheet = (GridView) findViewById(R.id.bottom_sheet);


        //set main ListView adapter
        adapter = new BooksAdapter(this, R.layout.item_list_view, books);
        listView.setAdapter(adapter);

        //set bottom sheet(GridView) adapter
        bottomSheetAdapter = new BottomSheetAdapter(this, R.layout.item_grid, bottomItems);
        bottomSheet.setAdapter(bottomSheetAdapter);

        bottomSheet.setTranslationY(getStatusBarHeight());
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                Log.d("MainActivity", "onStateChanged: " + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.d("MainActivity", "onSlide: ");
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });

        //main listview item click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedBook = (Book) parent.getAdapter().getItem(position);
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        //bottom sheet item click event
        bottomSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position == 2) {

                    //delete an item in listview
                    if (selectedBook == null) {
                        Toast.makeText(getApplicationContext(), "select an item", Toast.LENGTH_LONG).show();
                    } else {
                        new AlertDialog.Builder(BottomSheetActivity.this)
                                .setTitle("Delete Confirm")
                                .setMessage("Are you sure you want to delete " + "\"" + selectedBook.getName().toUpperCase() + "\"" + "?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //delete it
                                        books.remove(selectedBook);
                                        adapter.notifyDataSetChanged();
                                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                } else if (position == 0) {

                    //add new book to main ListView
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BottomSheetActivity.this);
                    alertBuilder.setTitle("Adding a book");
                    alertBuilder.setMessage("Input book name and author");

                    LinearLayout layout = new LinearLayout(BottomSheetActivity.this);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    final EditText name = new EditText(BottomSheetActivity.this);
                    name.setHint("Book title");
                    layout.addView(name);

                    final EditText author = new EditText(BottomSheetActivity.this);
                    author.setHint("Book's author");
                    layout.addView(author);

                    alertBuilder.setView(layout);

                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //add this item
                            books.add(new Book(name.getText().toString(), author.getText().toString()));
                            adapter.notifyDataSetChanged();
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    });
                    alertBuilder.setNegativeButton(android.R.string.no, null);
                    alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);

                    alertBuilder.show();
                } else if (position == 3) {
                    Toast.makeText(getBaseContext(), "Share this on Facebook", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {
                    Toast.makeText(getBaseContext(), "Share this on Google+", Toast.LENGTH_SHORT).show();
                } else if (position == 5) {
                    Toast.makeText(getBaseContext(), "Share this on Twitter", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createBooksData() {
        books = new ArrayList<>();
        books.add(new Book("Lão Hạc", "Nam Cao"));
        books.add(new Book("Số đỏ", "Vũ Trọng Phụng"));
        books.add(new Book("Tắt đèn", "Ngô Tất Tố"));
        books.add(new Book("The wild chase sheep", "Haruki Murakami"));
        books.add(new Book("Mảnh trăng cuối rừng", "Nguyễn Minh Châu"));
        books.add(new Book("The Adventures of Huckleberry Finn", "Mark Twain"));
        books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài"));
        books.add(new Book("Never let me go", "Kazuo Ishiguro"));
        books.add(new Book("Harry Potter", "J.K. Rowling"));
        books.add(new Book("The Last Leaf", "O. Henry"));
        books.add(new Book("The Call of the Wild", "Jack London"));
        books.add(new Book("Le Papa de Simon", "Guy de Maupassant"));
        books.add(new Book("One Hundred Years of Solitude", "Gabriel García Márquez"));
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
