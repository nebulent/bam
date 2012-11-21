package com.netflexitysolutions.software.bam.ui

class Stage {

	static mapping = { id generator:'assigned', params:[type:'string'] }
	
    static constraints = {
		name()
		description()
    }
	
    String name;
    String description;
	
	String toString() {
		return name
	}
}
