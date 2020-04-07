package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.HashMap;

public class QTabViewLayout {

    @Expose
     HashMap<String, QCmdMessage[]> layouts;

    public void setLayouts(QCmdMessage[] cmdViews) {

        if (this.layouts == null) {
            this.layouts = new HashMap<String, QCmdMessage[]>();
        }

        this.layouts.put("layout", cmdViews);
    }

    public QCmdMessage[] getLayouts() {
        if (this.layouts == null)
            return null;
        return this.layouts.get("layout");
    }
}
