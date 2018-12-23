package com.kishannareshpal.chromemenu;

import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kishannareshpal.lib.AppMenu;
import com.kishannareshpal.lib.AppMenuPropertiesDelegate;


public class DownloadAppMenuDelegate implements AppMenuPropertiesDelegate {


    /**
     * When a menu item on the ChromeMenu library, is selected
     */
    @Override
    public void onMenuItemClicked(@NonNull MenuItem menuItem) {

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
    public void onHeaderViewInflated(@NonNull AppMenu appMenu, @NonNull View view) {
        view.setEnabled(false);
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
