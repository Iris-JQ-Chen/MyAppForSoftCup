package com.example.myappforsortcup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import butterknife.BindView;

public class SearchTest extends AppCompatActivity {

    private SearchBox searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_test);

        searchBox = (SearchBox)findViewById(R.id.search_box_on_test);
        searchBox.enableVoiceRecognition(this);

        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history_black_48dp));
            searchBox.addSearchable(option);
        }

        searchBox.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {
                Toast.makeText(SearchTest.this,"Menu Click",Toast.LENGTH_SHORT).show();
            }
        });

        searchBox.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {
                Toast.makeText(SearchTest.this,"Search Open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchCleared() {
                Toast.makeText(SearchTest.this,"Search Cleared",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchClosed() {
                Toast.makeText(SearchTest.this,"Search closed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchTermChanged(String s) {
                Toast.makeText(SearchTest.this,"SearchTermChanged",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearch(String s) {
                Toast.makeText(SearchTest.this,"Search",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResultClick(SearchResult searchResult) {
                Toast.makeText(SearchTest.this,"ResultClicked",Toast.LENGTH_SHORT).show();
            }
        });

        searchBox.setOverflowMenu(R.menu.menu_search_test);
        searchBox.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search_Box_menu_item1:
                        Toast.makeText(SearchTest.this,"item1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search_Box_menu_item2:
                        Toast.makeText(SearchTest.this,"item2",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }
}
