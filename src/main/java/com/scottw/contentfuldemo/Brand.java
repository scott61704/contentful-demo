package com.scottw.contentfuldemo;

import java.util.ArrayList;

import com.contentful.java.cda.TransformQuery.ContentfulField;
import com.contentful.java.cda.TransformQuery.ContentfulSystemField;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel;

@ContentfulEntryModel("brand")

public class Brand {

	@ContentfulField
	public String companyName;
	
	@ContentfulField
	public String companyDescription;
	
	@ContentfulField
	public String website;
	
	@ContentfulField
	public String twitter;
	
	@ContentfulField
	public String email;
	
	@ContentfulField
	public ArrayList<String> phone;
	
	@ContentfulField
	public CDAAsset logo;
	
	@ContentfulSystemField("id")
	public String id;

	@Override
	public String toString() {
		return "Brand [<br>companyName=" + companyName + ",<br>companyDescription=" + companyDescription + ",<br>website="
				+ website + ",<br>twitter=" + twitter + "<br>email=" + email + ",<br>phone=" + phone + ",<br>logo=" + logo
				+ ",<br>id=" + id + "<br>]";
	}



}
