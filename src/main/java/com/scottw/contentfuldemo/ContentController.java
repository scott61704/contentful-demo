package com.scottw.contentfuldemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.java.cda.QueryOperation;

@RestController
//@RequestMapping("contentful-demo")
public class ContentController {

	
	private CDAClient getClient() {
		return CDAClient.builder()
		    .setSpace("uklhatfbpwlu")
		    .setToken("uHn2YXRKv8-QB3NEmkJzjrNh5VCzcjITAUs8OdYPWtY")
		    .build();
		
	}

	@RequestMapping(value = "/contentful-demo/products", method = RequestMethod.GET)
	public String getProducts() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("product")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(CDAAsset image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}

	@RequestMapping(value = {"/contentful-demo/products2", "/contentful-demo/productList"}, method = RequestMethod.GET)
	public String getProducts2() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("product")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		Set entryKeys = array.entries().keySet();
		Collection<CDAEntry> entryValues = array.entries().values();

		Set assetKeys = array.assets().keySet();
		Collection<CDAAsset> assetValues = array.assets().values();
		
		System.out.println("CDAEntry Collection keys = " + entryKeys.toString());
		System.out.println("CDAEntry Collection values = " + entryValues.toString());
		
		CDAEntry entry = (CDAEntry) entryValues.toArray()[0];
		System.out.println("rawFields = " + entry.rawFields().toString());
		
		output = "Hi from product2() - " + entryValues.size() + "entries<br><br>";
		
		
		for(CDAEntry item : entryValues) {
			String entryId = item.id();
			
			System.out.println("item attrs = " + item.attrs().toString());

			//System.out.println("rawFields = " + item.rawFields().toString());
			if(item.contentType().id().equals("product")) {
				output = output + item.getField("productName") + "<br>";
			}
			if(item.contentType().id().equals("brand")) {
				output = output + item.getField("companyName") + "<br>";
			}
			if(item.contentType().id().equals("category")) {
				output = output + item.getField("title") + "<br>";
			}
			output = output + "contentType id = " + item.contentType().id() + ", name = " + item.contentType().name() + "<br>";
			output = output + "id = " + item.id() + "<br><br>";
			//Object obj = (Object) item.rawFields().get("productName");
			// Get a null pointer exception if the commented out code is run.
			//output = output + obj.getClass() + "<br><br>";
			//output = output + item.rawFields().toString() + "<br><br>";

			for(String key: item.rawFields().keySet()) {
				output = output + key + " = " + item.getField(key);
				output = output + ", className = " + item.getField(key).getClass().toString() + "<br>";
			}
			
			output = output + "**************************************************************************<br><br>";
/*			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
*/		}
		

		for(CDAAsset item : assetValues) {
			String assetId = item.id();
			
			System.out.println("asset attrs = " + item.attrs().toString());

			//System.out.println("rawFields = " + item.rawFields().toString());
			output = output + item.type().toString() + "<br><br>";
/*			
			if(item.type(). .contentType().id().equals("product")) {
				output = output + item.getField("productName") + "<br>";
			}
			if(item.contentType().id().equals("brand")) {
				output = output + item.getField("companyName") + "<br>";
			}
			if(item.contentType().id().equals("category")) {
				output = output + item.getField("title") + "<br>";
			}
			output = output + "contentType id = " + item.contentType().id() + ", name = " + item.contentType().name() + "<br><br>";
*/			//Object obj = (Object) item.rawFields().get("productName");
			// Get a null pointer exception if the commented out code is run.
			//output = output + obj.getClass() + "<br><br>";
			//output = output + item.rawFields().toString() + "<br><br>";

			for(String key: item.rawFields().keySet()) {
				output = output + key + " = " + item.getField(key) + "<br>";
				output = output + " className = " + item.getField(key).getClass().toString() + "<br>";
			}
			
			output = output + "**************************************************************************<br><br>";
/*			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
*/		}

		return output;
	}
	@RequestMapping(value = {"contentful-demo/productDetails/{id}"}, method = RequestMethod.GET)
	public String getProductDetails(@PathVariable String id) {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAEntry item  = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("product")
		        .one(id);
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		output = output + "Product Name = " + item.getField("productName") + "<br><br>";

			for(String key: item.rawFields().keySet()) {
				output = output + key + " = " + item.getField(key);
				output = output + ", className = " + item.getField(key).getClass().toString() + "<br>";
			}
			
			output = output + "**************************************************************************<br><br>";
/*			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
*/		 
		
/*
		for(CDAAsset item : assetValues) {
			String assetId = item.id();
			
			System.out.println("asset attrs = " + item.attrs().toString());

			//System.out.println("rawFields = " + item.rawFields().toString());
			output = output + item.type().toString() + "<br><br>";
			
			if(item.type(). .contentType().id().equals("product")) {
				output = output + item.getField("productName") + "<br>";
			}
			if(item.contentType().id().equals("brand")) {
				output = output + item.getField("companyName") + "<br>";
			}
			if(item.contentType().id().equals("category")) {
				output = output + item.getField("title") + "<br>";
			}
			output = output + "contentType id = " + item.contentType().id() + ", name = " + item.contentType().name() + "<br><br>";
			//Object obj = (Object) item.rawFields().get("productName");
			// Get a null pointer exception if the commented out code is run.
			//output = output + obj.getClass() + "<br><br>";
			//output = output + item.rawFields().toString() + "<br><br>";

			for(String key: item.rawFields().keySet()) {
				output = output + key + " = " + item.getField(key) + "<br>";
				output = output + " className = " + item.getField(key).getClass().toString() + "<br>";
			}
			
			output = output + "**************************************************************************<br><br>";
			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
*/
		return output;
	}
	@RequestMapping(value = "/contentful-demo/categories", method = RequestMethod.GET)
	public String getCategories() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("category")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		
		String output = "";
		
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Category category = client
				    .observeAndTransform(Category.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(category.toString()+"\n\r");

			output = output + category.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			System.out.println("icon fields = " + category.icon.id() + ", " + category.icon.mimeType() + ", " + category.icon.title() + ", " + category.icon.url());

			output = output + "icon fields = " + category.icon.id() + ", " + category.icon.mimeType() + ", " + category.icon.title() + ", " + category.icon.url() + "<br><br>";

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}
	@RequestMapping(value = "/contentful-demo/brands", method = RequestMethod.GET)
	public String getBrands() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("brand")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Brand brand = client
				    .observeAndTransform(Brand.class)
				    .one(entryId)
				    .blockingSingle();
		
/*			ArrayList<Brand> brands = client
				    .observeAndTransform(Brand.class)
				    .all()
				    .;
//				    .blockingSingle();

			System.out.println("all brands \n\r");
			System.out.println(brands.toString());
*/			
			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(brand.toString()+"\n\r");

			output = output + brand.toString() + "<br><br>";
			

			//Doing this for now, as I don't know if you can unwrap an asset
			System.out.println("logo fields = " + brand.logo.id() + ", " + brand.logo.mimeType() + ", " + brand.logo.title() + ", " + brand.logo.url());

			output = output + "logo fields = " + brand.logo.id() + ", " + brand.logo.mimeType() + ", " + brand.logo.title() + ", " + brand.logo.url() +
"<br><br>";
			

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}
}
