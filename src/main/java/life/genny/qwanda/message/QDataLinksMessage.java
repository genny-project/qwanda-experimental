package life.genny.qwanda.message;


import com.google.gson.annotations.Expose;

import life.genny.qwanda.Link;
import life.genny.qwanda.entity.EntityEntity;

public class QDataLinksMessage extends QDataMessage {
   static final long serialVersionUID = 1L;
  @Expose
   Link[] items;
   static final String DATATYPE_LINK_CHANGE = "LINK_CHANGE";
  @Expose
   boolean clearLinks = true;

  public QDataLinksMessage(final Link[] items, boolean clearLinks) {
    super(DATATYPE_LINK_CHANGE);
    setItems(items);
  }

  public QDataLinksMessage(final Link[] items) {
    this(items, true);
  }



  public Link[] getItems() {
    return items;
  }

  public void setItems(final Link[] items) {
    this.items = items;
  }

/**
 * @return the clearLinks
 */
public boolean isClearLinks() {
	return clearLinks;
}

/**
 * @param clearLinks the clearLinks to set
 */
public void setClearLinks(boolean clearLinks) {
	this.clearLinks = clearLinks;
}

  


}
