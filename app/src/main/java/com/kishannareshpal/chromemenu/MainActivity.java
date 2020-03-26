package com.kishannareshpal.chromemenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.kishannareshpal.lib.AppMenuButtonHelper;
import com.kishannareshpal.lib.AppMenuButtonHelperLongClick;
import com.kishannareshpal.lib.AppMenuHandler;
import com.kishannareshpal.lib.AppMenuPropertiesDelegate;
import com.kishannareshpal.lib.AppMenuThemeConfiguration;

import static com.kishannareshpal.lib.AppMenuThemeConfiguration.ThemeModes.NIGHT;

public class MainActivity extends AppCompatActivity implements AppMenuButtonHelperLongClick.OnPrepareMenuListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Component
        View click = findViewById(R.id.clc1);
        View click2 = findViewById(R.id.clc2);

        // Setup Component


        new AppMenuButtonHelperLongClick(click2, this, this, this);
        new AppMenuButtonHelperLongClick(click, this, this, this);

    }

    @Override
    public Menu PrepareMenuItems(PopupMenu popupMenu) {
        Menu m = popupMenu.getMenu();

        // Use a PopupMenu to create the Menu object.
        // @Source: https://stackoverflow.com/a/24729407/7493547
        Menu menu = popupMenu.getMenu();
        int order = 0;
        /* Show these options on the context menu */
        menu.add("hellow");

        switch ("Enunciado"){
            case "Enunciado":
                order = order + 1;
                menu.add("Abrir o enunciado");
                break;
            case "Guia":
                order = order + 1;
                menu.add("Abrir a guia");
                break;

            case "Enunciado + Guia":
                order = order + 1;
                menu.add("Abrir o enunciado");

                order = order + 1;
                menu.add("Abrir o guia");
                break;
        }

        // get the selection state of exame based on the background color of the card... duh
        boolean isSelected = false;

        order = order + 1;
        menu.add("Apagar o exame");

        return m;
    }

    @Override
    public AppMenuPropertiesDelegate PrepareMenuDelegate(View itemView) {
        return new DownloadAppMenuDelegate(this);
    }

}
