package life.genny.qwanda;

import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class QwandaVersion {
	
	public QwandaVersion()
	{
		
	}

	static public String getVersion()
	{
		return getProperties().getProperty("git.build.version");
	}
	
	static public String getBuildDate()
	{
		return getProperties().getProperty("git.build.time");
	}
	
	static public String getCommitDate()
	{
		    return getProperties().getProperty("git.commit.time");
	}
	
	static public Properties getProperties()
	{
		   Properties properties = new Properties();
		    try {
		    	QwandaVersion qv = new QwandaVersion();
		    	properties.load(qv.getClass().getResourceAsStream("/qwanda-git.properties" ));
		     // properties.load(Thread.currentThread().getContextClassLoader().getResource("git.properties")
		      //    .openStream());
		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		    return properties;
	}
	
	static public String getJson()
	{
		Gson gsonObj = new Gson();
		String strJson =  gsonObj.toJson(getProperties());
		return strJson;
	}
}
