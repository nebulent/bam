package com.netflexitysolutions.software.bam.ui

class Stage {

    static constraints = {
		name()
		description()
    }
	
    String id;
    String name;
    String description;
	
	String toString() {
		return name
	}
}
