/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.dataimporttool.dmt.helper.SystemException;
import org.openmrs.module.dataimporttool.matchingschema.ReferenceSideType;
import org.openmrs.module.dataimporttool.matchingschema.ReferenceType;



/**
 * A builder that creates the {@link ReferenceType } instances
 *
 */
public class ReferenceBuilder {
	private Map<Integer, ReferenceType> references;
	private ReferenceType referenceType;
	public final static String REFERENCEE = "referencee";
	public final static String REFERENCED = "referenced";
	
	public ReferenceBuilder() {
		references = new HashMap<Integer, ReferenceType>();
	}
	
	/**
	 * Creates the reference object and add it to a collection
	 * @param id
	 * @param datatype
	 * @param size
	 * @param nameDesc
	 * @param predecessor
	 * @return
	 */
	public ReferenceBuilder createReference(final Integer id, 
			final String datatype, 
			final Integer size, 
			final String nameDesc,
			final Integer predecessor) {
		referenceType = new ReferenceType();
		referenceType.setId(id);
		referenceType.setDatatype(datatype);
		referenceType.setSize(size);
		referenceType.setNameDesc(nameDesc);
		referenceType.setPredecessor(predecessor);
		
		references.put(id, referenceType);
		
		return this;
	}
	
	/**
	 * Create the reference side (LEFT or RIGHT)
	 * @param table
	 * @param column
	 * @param side
	 * @return
	 * @throws SystemException
	 */
	public ReferenceBuilder createReferenceSide(final String table, 
			final String column, 
			final String side) 
			throws SystemException {
		if(referenceType == null) {
			throw new SystemException("Cannot create ReferenceSideType without create ReferenceType first");
		}
		
		ReferenceSideType referenceSide = new ReferenceSideType();
		referenceSide.setTable(table);
		referenceSide.setColumn(column);
		
		if(side.equalsIgnoreCase(ReferenceBuilder.REFERENCEE)) {
			referenceType.setReferencee(referenceSide);
		}
		else if(side.equalsIgnoreCase(ReferenceBuilder.REFERENCED)) {
			referenceType.setReferenced(referenceSide);
		} else {
			throw new SystemException("Invalid reference side parameter value");
		}
		return this;
	}
	
	/**
	 * Create the referenced value (Constant or FK)
	 * @param value
	 * @return
	 * @throws SystemException
	 */
	public ReferenceBuilder createReferencedValue(Object value) 
			throws SystemException {
		if(referenceType == null) {
			throw new SystemException("Cannot create ReferenceValueType without create ReferenceType first");
		}
		referenceType.setReferencedValue(value);
		return this;
	}
	
	public Map<Integer, ReferenceType> processMap() {
		return references;
	}
	
	public ReferenceType getReference() {
		return referenceType;
	}
	
}
