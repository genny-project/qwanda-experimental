package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

public class QCmdLayoutMessage extends QCmdMessage {
   static final String CMD_TYPE = "CMD_LAYOUT";
  @Expose
   String data;
  @Expose
   Boolean visible;



  public QCmdLayoutMessage(final String layoutCode, final String layout) {
    super(CMD_TYPE, layoutCode);
    setData(layout);
  }

  /**
   * 
   */
   static final long serialVersionUID = 1L;

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
   void setData(final String data) {
    this.data = data;
  }

  /**
   * @return the visible
   */
  public Boolean getVisible() {
    return visible;
  }

  /**
   * @param visible the visible to set
   */
  public void setVisible(final Boolean visible) {
    this.visible = visible;
  }



}
