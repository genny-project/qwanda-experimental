package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.rule.Rule;

public class QDataRuleMessage extends QDataMessage {

   static final long serialVersionUID = 1L;
  @Expose
   Rule[] items;
   static final String DATATYPE_RULE = Rule.class.getSimpleName();


  public QDataRuleMessage(final Rule[] items) {
    super(DATATYPE_RULE);
    setItems(items);
  }

  public Rule[] getItems() {
    return items;
  }

  public void setItems(final Rule[] items) {
    this.items = items;
  }


  
}
