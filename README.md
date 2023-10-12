# Rolster Capacitor AppReview

Use plugin for manage contact list of Device.

## Installation

Package only supports Capacitor 5

```
npm i @rolster/capacitor-app-review
```

### Android Config

And register the plugin by adding it to you MainActivity's onCreate:

```java
import com.rolster.capacitor.appreview.AppReview;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    registerPlugin(AppReview.class);
    // Others register plugins

    super.onCreate(savedInstanceState);
  }
}
```
