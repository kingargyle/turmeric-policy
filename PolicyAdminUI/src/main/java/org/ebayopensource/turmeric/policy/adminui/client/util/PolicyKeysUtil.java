/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.util;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.ResourceType;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectType;

/**
 * The Class PolicyKeysUtil.
 */
public class PolicyKeysUtil {

	private static List<PolicyKey> poKeys;
	private static List<ResourceKey> rsKeys;
	private static List<OperationKey> opKeys;
	private static List<SubjectKey> sKeys;
	private static List<SubjectGroupKey> sgKeys;

	static {
		poKeys = new ArrayList<PolicyKey>();
		PolicyKey poKey = null;
		for (String poType : PolicyType.getValues()) {
			poKey = new PolicyKey();
			poKey.setType(poType);
			poKeys.add(poKey);
		}

		rsKeys = new ArrayList<ResourceKey>();
		ResourceKey rsKey = null;
		for (String rsType : ResourceType.getValues()) {
			rsKey = new ResourceKey();
			rsKey.setType(rsType);
			rsKeys.add(rsKey);
		}

		opKeys = new ArrayList<OperationKey>();
		OperationKey opKey = null;
		for (String rsType : ResourceType.getValues()) {
			opKey = new OperationKey();
			opKey.setResourceType(rsType);
			opKeys.add(opKey);
		}

		sKeys = new ArrayList<SubjectKey>();
		SubjectKey sKey = null;
		for (String sType : SubjectType.getValues()) {
			sKey = new SubjectKey();
			sKey.setType(sType);
			sKeys.add(sKey);
		}

		sgKeys = new ArrayList<SubjectGroupKey>();
		SubjectGroupKey sgKey = null;
		for (String sType : SubjectType.getValues()) {
			sgKey = new SubjectGroupKey();
			sgKey.setType(sType);
			sgKeys.add(sgKey);
		}

	}

	/**
	 * Gets the all policy key list.
	 * 
	 * @return the all policy key list
	 */
	public static List<PolicyKey> getAllPolicyKeyList() {
		return poKeys;
	}

	/**
	 * Gets the all resource key list.
	 * 
	 * @return the all resource key list
	 */
	public static List<ResourceKey> getAllResourceKeyList() {
		return rsKeys;
	}

	/**
	 * Gets the all operation key list.
	 * 
	 * @return the all operation key list
	 */
	public static List<OperationKey> getAllOperationKeyList() {
		return opKeys;
	}

	/**
	 * Gets the all subject key list.
	 * 
	 * @return the all subject key list
	 */
	public static List<SubjectKey> getAllSubjectKeyList() {
		return sKeys;
	}

	/**
	 * Gets the all subject group key list.
	 * 
	 * @return the all subject group key list
	 */
	public static List<SubjectGroupKey> getAllSubjectGroupKeyList() {
		return sgKeys;
	}

}
