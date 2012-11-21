package com.netflexitysolutions.software.bam.ui

class Flow {

    static constraints = {
		uuid()
		process()
		stage()
		stageTypeCode(nullable:false)
		storeMessagePayload()
    }
	
	public enum StageTypeCode {
		START, INTERMEDIATE,END, ALLINONE
	
	}
	
	String id;
	String uuid;
	StageTypeCode stageTypeCode;
	boolean storeMessagePayload;
	Process process;
	Stage stage;

}
