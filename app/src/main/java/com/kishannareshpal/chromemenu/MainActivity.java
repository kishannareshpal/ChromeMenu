package com.kishannareshpal.chromemenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kishannareshpal.lib.AbstractAppMenuPropertiesDelegate;
import com.kishannareshpal.lib.AppMenu;
import com.kishannareshpal.lib.AppMenuButtonHelper;
import com.kishannareshpal.lib.AppMenuHandler;
import com.kishannareshpal.lib.AppMenuPropertiesDelegate;

import java.util.ArrayList;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Component
        Button btn_simple = findViewById(R.id.btn_simple);

        // Setup Component
        AppMenuHandler appMenuHandler = new AppMenuHandler(this, new DownloadAppMenuDelegate(), R.menu.menu);
        AppMenuButtonHelper appMenuButtonHelper = new AppMenuButtonHelper(appMenuHandler);
        btn_simple.setOnTouchListener(appMenuButtonHelper);
    }
}
