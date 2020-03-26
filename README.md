# ChromeMenu

[![Download](https://api.bintray.com/packages/kishannareshpal/maven/chromemenu/images/download.svg)](https://api.bintray.com/packages/kishannareshpal/maven/chromemenu/images/download.svg)
[![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=17)

------

#### Note: All credits goes to [**@DeweyReed**](https://github.com/DeweyReed) for his original work on [Chrome Menu](https://github.com/DeweyReed/ChromeMenu) port.*

### * This is just a customized fork for use in one of my project. If you want to use it, you may, but i won't document any further.

##### Changes i've made:

- Migrate it back from ***AndroidX Namespace***, because of some issues i was encountering while using it on my project.

- Added NightMode theming, which can be applied by using:

- ````java
  // add this line before any ChromeMenu setup.
  AppMenuThemeConfiguration.setTheme(ThemeMode.NIGHT);
  ````

- Changed the controlColorHighlight color to a lighter one, to match my project.

  ```xml
  <color name="cm_control_highlight_color">#bdbdbd</color>
  ```

- Added an alternate AppMenuHandler constructor which takes an instance of Menu as a third argument, as supposed as the original's that takes a Menu Resource Id. Enabling us to create a menu dynamically, and not by definition via XML.

  ```java
  // Using menu resource id (XML)
  public AppMenuHandler(Activity activity, AppMenuPropertiesDelegate delegate,
                            int menuResourceId)
      
  // Using OnPrepareMenuListener, you will get a PopupMenu, on which you may get it's menu by calling `PopupMenu.getMenu()` and then you must return it (but after adding your desred menuItems, of course).
  // You may find this interesting if you want to know how to create a menu programmatically: https://stackoverflow.com/a/24729407/7493547
  public AppMenuHandler(Activity activity, AppMenuPropertiesDelegate delegate,
                            OnPrepareMenuListener onPrepare)
  ```

- Added support for Long Click. If you want to open the menu on long click, you may use this Helper:

  ```java
  // view :: The view that the starts the menu;
  // menuHandler :: an instance of AppMenuHandler;
  public AppMenuButtonHelperLongClick(View handlerView, AppMenuHandler menuHandler);
  ```

------



The menu from [Chromium Android appmenu](https://github.com/chromium/chromium/tree/master/chrome/android/java/src/org/chromium/chrome/browser/appmenu). It can do this:

![Example GIF](https://github.com/DeweyReed/ChromeMenu/blob/master/images/example.gif?raw=true)

Notice **the press, move, selection, up are finished in one gesture**.

I love this design. It significantly improves the efficiency of using an app. So I dig into chromium, extract related code and pack them into this library.

## Usage

1. **Install dependency**

   `implementation 'com.kishannareshpal:chrome-menu:1.5'`

2. **Usage: Set up touch listener**

   a) **Kotlin**

   ```Kotlin
   val handler = AppMenuHandler(activity, listener, R.menu.menu)
   val helper = AppMenuButtonHelper(handler)
   btn.setOnTouchListener(helper)
   ```

   b) **Java**

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
