package com.codecool;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class Store {

    private final File xmlFile = new File("src/data/data.xml");
    private List<Product> productList;

    private void saveToXml (Product product){
        Document document = createDocument();

        Element root = document.getDocumentElement();
        String node = "Product";
        Element productElement = document.createElement(node);

        root.appendChild(productElement);

        int size;
        String prodType;
        if(product instanceof CDProduct){
            size = ((CDProduct) product).getNumOfTracks();
            prodType = "cd";
        }
        else {
            size = ((BookProduct) product).getNumOfPages();
            prodType = "book";
        }

        productElement.setAttribute("name", product.getName());
        productElement.setAttribute("price",String.valueOf(product.getPrice()) );
        productElement.setAttribute("size",String.valueOf(size) );
        productElement.setAttribute("type", prodType );

        save(document);
    }

    //Abstract, implemented in PersistentStore
    protected abstract void storeProduct(Product product);

    protected  Product createProduct(ProductType type, String name, int price, int size){
        if (type == ProductType.CD){
            return new CDProduct(name,price,size);
        }
        else {
            return new BookProduct(name,price,size);

        }
        
    }

    public List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();
        return null;
    }

    public void store(Product product){
        saveToXml(product);//not sure about method call order atm.
        storeProduct(product);

    }

    // Creating Document type obj.
    private Document createDocument(){
        Document document = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        }
        catch (Exception e){

        }
        return document;
    }

    // Write the document to the XMl
    private void save(Document document ){
        TransformerFactory factory = TransformerFactory.newInstance();

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(xmlFile);
        try{
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);
        }
        catch (Exception e){

        }
    }
}
