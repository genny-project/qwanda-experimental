package life.genny.qwanda.message;


import com.google.gson.annotations.Expose;

import life.genny.qwanda.entity.EntityEntity;

public class QDataEntityEntityMessage extends QDataMessage {
   static final long serialVersionUID = 1L;
  @Expose
   EntityEntity[] items;
   static final String DATATYPE_ENTITYENTITY = EntityEntity.class.getSimpleName();
  @Expose
   boolean clearLinks = true;

  public QDataEntityEntityMessage(final EntityEntity[] items, boolean clearLinks) {
    super(DATATYPE_ENTITYENTITY);
    setItems(items);
  }

  public QDataEntityEntityMessage(final EntityEntity[] items) {
    this(items, true);
  }



  public EntityEntity[] getItems() {
    return items;
  }

  public void setItems(final EntityEntity[] items) {
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
