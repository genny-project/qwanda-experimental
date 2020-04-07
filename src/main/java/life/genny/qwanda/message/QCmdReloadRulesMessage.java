package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdReloadRulesMessage extends QCmdMessage{
	 static final String CMD_TYPE = "CMD_RELOAD_RULES";
	 static final String CODE = "RELOAD_RULES_FROM_FILES";
	@Expose
	 String rulesDir;
	
	public QCmdReloadRulesMessage() {
		super(CMD_TYPE, CODE);
		rulesDir = "rules"; // save rules in a rules dir in src/main/resources
	}

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	/**
	 * @return the rulesDir
	 */
	public String getRulesDir() {
		return rulesDir;
	}

	/**
	 * @param rulesDir the rulesDir to set
	 */
	public void setRulesDir(String rulesDir) {
		this.rulesDir = rulesDir;
	}


	
}
