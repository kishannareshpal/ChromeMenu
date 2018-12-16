# ChromeMenu

[![Download](https://api.bintray.com/packages/reedreed/maven/chrome-menu/images/download.svg)](https://bintray.com/reedreed/maven/chrome-menu/_latestVersion)
[![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=17)

------

###### Note: All credits goes to [**@DeweyReed**](https://github.com/DeweyReed) for his work on [Chrome Menu](https://github.com/DeweyReed/ChromeMenu). All i've done is migrate it back from ***Android X***, because of some issues i was encountering while using it on my project.

------



The menu from [Chromium Android appmenu](https://github.com/chromium/chromium/tree/master/chrome/android/java/src/org/chromium/chrome/browser/appmenu). It can do this:

![Example GIF](https://github.com/DeweyReed/ChromeMenu/blob/master/images/example.gif?raw=true)

Notice **the press, move, selection, up are finished in one gesture**.

I love this design. It significantly improves the efficiency of using an app. So I dig into chromium, extract related code and pack them into this library.

## Usage

1. **Install dependency**

   `implementation 'com.kishannareshpal:chrome-menu:1.0.0'`

2. **Usage: Set up touch listener**

   a. **Kotlin**

   ```Kotlin
   val handler = AppMenuHandler(activity, listener, R.menu.menu)
   val helper = AppMenuButtonHelper(handler)
   btn.setOnTouchListener(helper)
   ```

   b. *Java**

   ```java
   AppMenuHandler handler = new AppMenuHandler(activity, listener, R.menu.menu);
   AppMenuButtonHelper helper = new AppMenuButtonHelper(handler);
   btn.setOnTouchListener(helper);
   ```



   `listener` is an [AppMenuPropertiesDelegate](https://github.com/DeweyReed/ChromeMenu/blob/master/library/src/main/java/xyz/aprildown/chromemenu/AppMenuPropertiesDelegate.java#L15) interface or use its abstract version [AbstractAppMenuPropertiesDelegate()](https://github.com/DeweyReed/ChromeMenu/blob/master/library/src/main/java/xyz/aprildown/chromemenu/AbstractAppMenuPropertiesDelegate.java#L12)

3. **Handle click events**

   All click events are handled in the listener's `onMenuItemClicked`

## More Usage

- Change menu width

  Add this line to `dimens.xml`:

  ```XML
  <!-- 256dp is the original width -->
  <dimen name="menu_width">258dp</dimen>
  ```



## Hmm...

If you wish to try to the original version from chromium, check out the `from_chrome` tag. After this tag, I've been adding extra code to make this library easier to use.
