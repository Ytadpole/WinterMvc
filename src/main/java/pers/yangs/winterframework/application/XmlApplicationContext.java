package pers.yangs.winterframework.application;

public class XmlApplicationContext {

    private String[] configLocations;

    public XmlApplicationContext(String[] configLocations){
        this.configLocations = configLocations;
        loadConfig();
    }

    private void loadConfig(){

    }
}
