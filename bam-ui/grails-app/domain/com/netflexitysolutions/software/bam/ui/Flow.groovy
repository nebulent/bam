package com.netflexitysolutions.software.bam.ui

class Flow {

    static constraints = {
		stageTypeCode(nullable:false)
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
