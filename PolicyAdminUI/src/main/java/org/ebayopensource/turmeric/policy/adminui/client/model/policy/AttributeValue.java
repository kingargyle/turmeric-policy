/*********************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.model.policy;

/**
 * The Interface AttributeValue.
 */
public interface AttributeValue {
    
    /**
     * Gets the data type.
     *
     * @return the data type
     */
    public String getDataType();
    
    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue();
}
