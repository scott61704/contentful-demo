package com.scottw.contentfuldemo;

import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel;
import com.contentful.java.cda.TransformQuery.ContentfulField;
import com.contentful.java.cda.TransformQuery.ContentfulSystemField;

@ContentfulEntryModel("product")
public class Product {
  @ContentfulField
  public String productName;

  @ContentfulField
  public String slug;
  
  @ContentfulField
  public String productDescription;
  
  @ContentfulField
  public String sizetypecolor;
  
  @ContentfulField
  public ArrayList<String> tags;

  @ContentfulField
  public Double price;
  
  @ContentfulField
  public Double quantity;
  
  @ContentfulField
  public String sku;
  
  @ContentfulField
  public String website;
  
  @ContentfulField
  Brand brand;

  @ContentfulField("image") //renamed this to images below, because it is actually an array of images
  public ArrayList<CDAAsset> images;
	
  @ContentfulSystemField("id")
  public String id;
  
@Override
public String toString() {
	return "Product [<br>productName=" + productName + ",<br>slug=" + slug + ",<br>productDescription=" + productDescription
			+ ",<br>sizetypecolor=" + sizetypecolor + ",<br>tags=" + tags + ",<br>price=" + price + ",<br>quantity=" + quantity
			+ ",<br>sku=" + sku + ",<br>website=" + website + ",<br>brand=" + brand + ",<br>images=" + images + ",<br>id=" + id + "<br>]";
}
  
}