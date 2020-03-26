package com.kishannareshpal.lib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.PopupMenu;

public class AppMenuButtonHelperLongClick extends View.AccessibilityDelegate implements View.OnTouchListener, View.OnCreateContextMenuListener {


    private AppMenuHandler mMenuHandler;
    private Runnable mOnAppMenuShownListener;
    private boolean mIsTouchEventsBeingProcessed;
    private boolean mShowMenuOnUp;
    private boolean mMenuShowsFromBottom;
    private Runnable mOnClickRunnable;

    private Activity activity;
    private Context ctx;
    private View view;


    private OnPrepareMenuListener onPrepareMenu;
    public interface OnPrepareMenuListener {
        Menu PrepareMenuItems(PopupMenu popupMenu);
        AppMenuPropertiesDelegate PrepareMenuDelegate(View itemView);
    }

    public AppMenuButtonHelperLongClick(View view, Context ctx, Activity activity, OnPrepareMenuListener onPrepareMenu) {
        this.view = view;
        this.ctx = ctx;
        this.activity = activity;
        this.onPrepareMenu = onPrepareMenu;
        view.setOnTouchListener(this);
        view.setOnCreateContextMenuListener(this);
    }

    /**
     * Shows the app menu if it is not already shown.
     *
     * @param view           View that initiated showing this menu. Normally it is a menu button.
     * @param startDragging  Whether dragging is started.
     * @return Whether or not if the app menu is successfully shown.
     */
    private boolean showAppMenu(View view, boolean startDragging) {
        if (!mMenuHandler.isAppMenuShowing()
                && mMenuHandler.showAppMenu(view, startDragging, mMenuShowsFromBottom)) {
            // Initial start dragging can be canceled in case if it was just single tap.
            // So we only record non-dragging here, and will deal with those dragging cases in
            // AppMenuDragHelper class.
            /*if (!startDragging) RecordUserAction.record("MobileUsingMenuBySwButtonTap");*/

            if (mOnAppMenuShownListener != null) {
                mOnAppMenuShownListener.run();
            }
            return true;
        }
        return false;
    }


    /**
     * Set whether touch event is being processed and view is pressed on touch event.
     *
     * @param view         View that received a touch event.
     * @param isActionDown Whether the touch event is a down action.
     */
    private void updateTouchEvent(View view, boolean isActionDown) {
        mIsTouchEventsBeingProcessed = isActionDown;
        view.setPressed(isActionDown);
    }


    // the purpose of the touch listener is just to store the touch X,Y coordinates
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // save the X, Y coordinates
        float touchPositionX = event.getX();
        float touchPositionY = event.getY();
        AppMenu.setTouchedPosition(touchPositionX, touchPositionY);

        // If user starts to drag on this menu button, ACTION_DOWN and all the subsequent touch
        // events are received here. We need to forward this event to the app menu to handle
        // dragging and scrolling correctly.

        PopupMenu popupMenu = new PopupMenu(ctx, this.view);
        Menu menu = onPrepareMenu.PrepareMenuItems(popupMenu);
        this.mMenuHandler = new AppMenuHandler(activity, onPrepareMenu.PrepareMenuDelegate(this.view), menu);


        AppMenuDragHelper dragHelper = mMenuHandler.getAppMenuDragHelper();
        if (dragHelper != null) {
            dragHelper.handleDragging(event, v, true);
        }

        // let the touch event pass on to whoever needs it
        return false;
    }

    // Shows the app menu.
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (mMenuHandler.isAppMenuShowing()) { mMenuHandler.hideAppMenu(); }
        showAppMenu(v, false);
        // Event is finally consumed here.
    }


    @Override
    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        if (action == AccessibilityNodeInfo.ACTION_CLICK) {
            if (!mMenuHandler.isAppMenuShowing()) {
                showAppMenu(host, false);
            } else {
                mMenuHandler.hideAppMenu();
            }
            host.playSoundEffect(SoundEffectConstants.CLICK);
            host.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
            return true;
        }
        return super.performAccessibilityAction(host, action, args);
    }
}
