package com.android_mvvm_sample_app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android_mvvm_sample_app.R;
import com.android_mvvm_sample_app.adapter.BookAdapter;
import com.android_mvvm_sample_app.model.BookResponseModel;
import com.android_mvvm_sample_app.viewmodel.BookViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        bookAdapter = new BookAdapter(this, this, R.layout.book_item_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookAdapter);

        final BookViewModel bookViewModel =
                ViewModelProviders.of(this).get(BookViewModel.class);

        observeViewModel(bookViewModel);
    }

    private void observeViewModel(BookViewModel bookViewModel) {

        // updating list based on data change
        bookViewModel.getBookListLiveData().observe(this, new Observer<BookResponseModel>() {
            @Override
            public void onChanged(@Nullable BookResponseModel bookResponseModel) {

                if (bookResponseModel != null)
                    bookAdapter.addAll(bookResponseModel.getItems());

                Log.e(TAG, "onChanged: " + bookResponseModel.getItems().size());
            }
        });

    }


    @Override
    public void onClick(View view) {

        BookResponseModel.ItemsBean bookItem = (BookResponseModel.ItemsBean) view.getTag();

        Toast.makeText(this, "" + bookItem.getVolumeInfo().getTitle(), Toast.LENGTH_SHORT).show();

    }
}
