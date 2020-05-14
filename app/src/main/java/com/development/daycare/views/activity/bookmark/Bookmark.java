package com.development.daycare.views.activity.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.development.daycare.R;
import com.development.daycare.adapter.BookmarkAdapter;
import com.development.daycare.databinding.ActivityBookmarkBinding;
import com.development.daycare.model.BookmarkData;
import com.development.daycare.views.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Bookmark extends BaseActivity {
ActivityBookmarkBinding bookmarkBinding;
BookmarkAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookmarkBinding = DataBindingUtil.setContentView(this,R.layout.activity_bookmark);
        initializeView();
        setData();
    }

    private void initializeView(){
        bookmarkBinding.toolbar.setTitle(getString(R.string.bookmark));

        setSupportActionBar(bookmarkBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }

    private void setRecyclerview(List<BookmarkData> bookmarkDataList){
        adapter = new BookmarkAdapter(this, bookmarkDataList);
        mLayoutManager = new LinearLayoutManager(this);
        bookmarkBinding.recyclerView.setLayoutManager(mLayoutManager);
        bookmarkBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookmarkBinding.recyclerView.setAdapter(adapter);
    }


    private void setData(){
        List<BookmarkData> bookmarkList = new ArrayList<>();

        for(int i=0;i<6;i++){
            BookmarkData bookmark = new BookmarkData();
            bookmark.setName("Day Care Name");
            bookmark.setAge("3-4 years");
            bookmark.setCountry("India");
            bookmark.setCity("Delhi");
            bookmark.setImage_url("https://cdn.britannica.com/24/141224-050-0F5FA19C/Caregivers-children-day-care-centre.jpg");
            bookmark.setAddress("9/55 Caroline Street,South Yarra VIC");

            bookmarkList.add(bookmark);
        }

        setRecyclerview(bookmarkList);


    }

}
