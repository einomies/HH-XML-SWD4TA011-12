//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.02 at 03:19:19 ip. EEST 
//


package huuto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the JAXB package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: JAXB
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Entry.DeliveryMethods.DeliveryMethod }
     * 
     */
    public Entry.DeliveryMethods.DeliveryMethod createEntryDeliveryMethodsDeliveryMethod() {
        return new Entry.DeliveryMethods.DeliveryMethod();
    }

    /**
     * Create an instance of {@link Entry }
     * 
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link Entry.Price.StartingPrice }
     * 
     */
    public Entry.Price.StartingPrice createEntryPriceStartingPrice() {
        return new Entry.Price.StartingPrice();
    }

    /**
     * Create an instance of {@link Entry.DeliveryMethods }
     * 
     */
    public Entry.DeliveryMethods createEntryDeliveryMethods() {
        return new Entry.DeliveryMethods();
    }

    /**
     * Create an instance of {@link Entry.Category }
     * 
     */
    public Entry.Category createEntryCategory() {
        return new Entry.Category();
    }

    /**
     * Create an instance of {@link Entry.Price.BuyNowPrice }
     * 
     */
    public Entry.Price.BuyNowPrice createEntryPriceBuyNowPrice() {
        return new Entry.Price.BuyNowPrice();
    }

    /**
     * Create an instance of {@link Entry.Price }
     * 
     */
    public Entry.Price createEntryPrice() {
        return new Entry.Price();
    }

    /**
     * Create an instance of {@link Entry.Price.MinimumIncrease }
     * 
     */
    public Entry.Price.MinimumIncrease createEntryPriceMinimumIncrease() {
        return new Entry.Price.MinimumIncrease();
    }

}
