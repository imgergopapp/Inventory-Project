package com.codecool;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public abstract class Store {

    private final File xmlFile = new File("src/data/data.xml");
    private List<Product> storedProducts = new ArrayList<>();

    public List<Product> getStoredProducts() {
        return storedProducts;
    }

    public void addToStoredProducts(Product product) {
        this.storedProducts.add(product);
    }

    private void saveToXml(Product product) {
        Document document = createDocument();

        Element root = document.getDocumentElement();
        Element productElement = document.createElement("Product");

        root.appendChild(productElement);

        int size;
        String prodType;
        if (product instanceof CDProduct) {
            size = ((CDProduct) product).getNumOfTracks();
            prodType = "cd";
        } else {
            size = ((BookProduct) product).getNumOfPages();
            prodType = "book";
        }

        productElement.setAttribute("name", product.getName());
        productElement.setAttribute("price", String.valueOf(product.getPrice()));
        productElement.setAttribute("size", String.valueOf(size));
        productElement.setAttribute("type", prodType);

        save(document);
    }

    //Abstract, implemented in PersistentStore
    protected abstract void storeProduct(Product product);

    protected Product createProduct(ProductType type, String name, int price, int size) {
        Product product;
        if (type == ProductType.CD) {
            product = new CDProduct(name, price, type, size);
        } else {
            product = new BookProduct(name, price, type, size);
        }
        return product;
    }

    public List<Product> loadProducts() {
        Document document = createDocument();
        NodeList nList = document.getElementsByTagName("Product");
        List<Product> productList = new ArrayList<>();
        Product product;
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element element = (Element) node;
            String name = element.getAttribute("name");
            String price = element.getAttribute("price");
            String size = element.getAttribute("size");
            String type = element.getAttribute("type");

            if (type.equals("cd")) {
                product = new CDProduct(name, Integer.valueOf(price), ProductType.CD, Integer.valueOf(size));
            } else {
                product = new BookProduct(name, Integer.valueOf(price), ProductType.BOOK, Integer.valueOf(size));
            }
            productList.add(product);
        }
        return productList;
    }

    public void store(Product product) {
        saveToXml(product);//not sure about method call order atm.
        storeProduct(product);
    }

    // Creating Document type obj.
    private Document createDocument() {
        Document document = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (Exception e) {

        }
        return document;
    }

    // Write the document to the XMl
    private void save(Document document) {
        TransformerFactory factory = TransformerFactory.newInstance();

        Document documentFormatted = removeEmptyNodes(document);
        DOMSource domSource = new DOMSource(documentFormatted);
        StreamResult streamResult = new StreamResult(xmlFile);
        try {
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {

        }
    }

    public Document removeEmptyNodes(Document document) {
        XPath xp = XPathFactory.newInstance().newXPath();
        NodeList nl = null;
        try {
            nl = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", document, XPathConstants.NODESET);
        } catch (Exception e) {

        }

        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
        }
        return document;
    }
}
