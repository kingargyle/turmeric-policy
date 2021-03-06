/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import java.util.Date;
import java.util.List;



/**
 * The Interface GenericPolicy.
 */
public interface GenericPolicy {
    
    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    Long getId();
    
    /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
    String getType();
    
    /**
	 * Gets the name.
	 * 
	 * @return the name
	 */
    String getName();
    
    /**
	 * Gets the description.
	 * 
	 * @return the description
	 */
    String getDescription();
    
    /**
	 * Gets the subject groups.
	 * 
	 * @return the subject groups
	 */
    List<SubjectGroup> getSubjectGroups();
    
    /**
	 * Gets the subjects.
	 * 
	 * @return the subjects
	 */
    List<Subject> getSubjects();
    
    /**
	 * Gets the exclusion subjects.
	 * 
	 * @return the exclusion subjects
	 */
    List<Subject> getExclusionSubjects();
    
    /**
	 * Gets the resources.
	 * 
	 * @return the resources
	 */
    List<Resource> getResources();
    
    /**
	 * Gets the exclusion sg.
	 * 
	 * @return the exclusion sg
	 */
    List<SubjectGroup> getExclusionSG();
    
    /**
	 * Gets the last modified.
	 * 
	 * @return the last modified
	 */
    Date getLastModified();
	
	/**
	 * Gets the created by.
	 * 
	 * @return the created by
	 */
	String getCreatedBy();
	
	/**
	 * Gets the last modified by.
	 * 
	 * @return the last modified by
	 */
	String getLastModifiedBy() ;
	
	/**
	 * Gets the creation date.
	 * 
	 * @return the creation date
	 */
	Date getCreationDate();
	
	/**
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
	boolean getEnabled();
	
	/**
	 * Gets the rules.
	 * 
	 * @return the rules
	 */
	List<Rule> getRules();

}