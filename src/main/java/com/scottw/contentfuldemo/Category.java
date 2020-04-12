package com.scottw.contentfuldemo;

import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel;
import com.contentful.java.cda.TransformQuery.ContentfulField;
import com.contentful.java.cda.TransformQuery.ContentfulSystemField;

@ContentfulEntryModel("category")
public class Category {
  @ContentfulField
  public String title;
  
  @ContentfulField
  public String categoryDescription;
  
  @ContentfulField
  public CDAAsset icon;
  
  @ContentfulSystemField("id")
  public String id;

@Override
public String toString() {
	return "Category [<br>title=" + title + ",<br>categoryDescription=" + categoryDescription + ",<br>icon=" + icon + ",<br>id=" + id
			+ "<br>]";
}
  
}