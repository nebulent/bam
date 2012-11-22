package com.netflexitysolutions.software.bam.ui

class Process {

    static constraints = {
		name()
		description()
    }
	
	String name;
	String description;
	List<Flow> flows;

	String toString() {
		return name
	}
}
