/********************************************************************
 * Copyright (c) 2010 eBay Inc., and others. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdatePolicyResponse;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * UpdatePolicyResponseJS.
 */
public class UpdatePolicyResponseJS extends JavaScriptObject implements
        UpdatePolicyResponse {
    
    /** The Constant NAME. */
    public static final String NAME = "ns1.updatePolicyResponse";
    
    /**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the update policy response js
	 */
    public static final native UpdatePolicyResponseJS fromJSON(String json) /*-{
        try {
            return eval('(' + json + ')');
        } catch (err) {
        return null;
        }
    }-*/;
    
    /**
	 * Instantiates a new update policy response js.
	 */
    protected UpdatePolicyResponseJS() {}
    
    /**
	 * Gets the error message.
	 * 
	 * @return the error message
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdatePolicyResponse#getErrorMessage()
	 */
    @Override
    public final native String getErrorMessage() /*-{
      return this["ns1.updatePolicyResponse"]["ms.errorMessage"];
    }-*/;

    /**
	 * Checks if is errored.
	 * 
	 * @return true, if is errored
	 * @see org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.UpdatePolicyResponse#isErrored()
	 */
    @Override
    public final native boolean isErrored() /*-{
         if (this["ns1.updatePolicyResponse"]["ms.ack"] === "Success")
            return false;
        else
            return true;
    }-*/;
}
