package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.FeeCategoryJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeeCategoryJsonResponse 
{
	private List<FeeCategoryJsonModel> feeCategories;

	

	@JsonProperty(value="feeCategoryList")
	public List<FeeCategoryJsonModel> getFeeCategories() {
		return feeCategories;
	}

	public void setFeeCategories(List<FeeCategoryJsonModel> feeCategories) {
		this.feeCategories = feeCategories;
	}


	
	
	
	
}
