package life.genny.qwanda.message;

/**
 * 
 * This test will simulate the frontend sent a menu toggle event signal to the
 * backend, then backend send back a message back to frontend to control
 * instruct the panel toggling.
 * 
 * 
 * { "cmd_type": "PANEL_TOGGLE", "codes": [ "FRM_APP_CONTENT:WEST" ],
 * "msg_type": "CMD_MSG", "cache":
 * "QUE_SIDEBAR_MENU_GRP:QUE_SIDEBAR_MENU_GRP:QUE_SIDEBAR_TOGGLE", "exec":
 * false, "send": false }
 * 
 * To make Chrome to show logs from Vertx
 * localStorage.setItem('DISPLAY_LOGS',true);
 */

public class QCmdMessageToggleFrame extends QCmdMessage {

     static final long serialVersionUID = 1L;

     static final String CMD_TYPE = "PANEL_TOGGLE";
     static final String MESSAGE_CODE = "FRM_APP_CONTENT:WEST";

 
    public QCmdMessageToggleFrame() {
	super(CMD_TYPE, MESSAGE_CODE);
    }

    public void returnCache(String cache) {
	setCache(cache);
    }
}
