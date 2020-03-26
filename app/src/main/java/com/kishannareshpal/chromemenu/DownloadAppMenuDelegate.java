package com.kishannareshpal.chromemenu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kishannareshpal.lib.AppMenu;
import com.kishannareshpal.lib.AppMenuPropertiesDelegate;


public class DownloadAppMenuDelegate implements AppMenuPropertiesDelegate {

    Activity activity;

    public DownloadAppMenuDelegate(Activity activity){
        this.activity = activity;
    }


    /**
     * When a menu item on the ChromeMenu library, is selected
     */
    @Override
    public void onMenuItemClicked(@NonNull MenuItem menuItem) {
        Toast.makeText(activity, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
    }




    /**
     * UNUSED METHODS
      */
    @Override
    public boolean shouldShowAppMenu() {
        return true;
    }

    @Override
    public void prepareMenu(@NonNull Menu menu) {

    }

    @Override
    public boolean shouldShowHeader(int maxMenuHeight) {
        return true;
    }

    @Override
    public int getHeaderResourceId() {
        return R.layout.app_menu_header;
    }

    @Override
    public void onHeaderViewInflated(@NonNull AppMenu appMenu, @NonNull View headerView) {
        TextView tv_title = headerView.findViewById(R.id.tv_title);
        TextView tv_subtitle = headerView.findViewById(R.id.tv_subtitle);

        tv_subtitle.setVisibility(View.VISIBLE);
        String disciplina = "Geografia";
        String ano_epoca = "2011 – 1a Epoca";
        tv_title.setText(disciplina);
        tv_subtitle.setText(ano_epoca);

        headerView.setEnabled(false);
    }

    @Override
    public boolean shouldShowFooter(int maxMenuHeight) {
        return false;
    }

    @Override
    public int getFooterResourceId() {
        return 0;
    }

    @Override
    public void onFooterViewInflated(@NonNull AppMenu appMenu, @NonNull View view) {

    }



}
