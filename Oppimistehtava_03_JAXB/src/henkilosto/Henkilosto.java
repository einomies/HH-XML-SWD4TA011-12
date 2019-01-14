// XML Schemasta generoitu luokka, luokasta poistettu generoinnin kommentit

package henkilosto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "henkilo"
})
@XmlRootElement(name = "henkilosto")
public class Henkilosto {

    @XmlElement(required = true)
    protected List<Henkilosto.Henkilo> henkilo;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar paivitetty;

    public List<Henkilosto.Henkilo> getHenkilo() {
        if (henkilo == null) {
            henkilo = new ArrayList<Henkilosto.Henkilo>();
        }
        return this.henkilo;
    }

    public XMLGregorianCalendar getPaivitetty() {
        return paivitetty;
    }

    public void setPaivitetty(XMLGregorianCalendar value) {
        this.paivitetty = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "hetu",
        "nimi",
        "osoite",
        "tiimi",
        "email",
        "puhelin",
        "nimike",
        "tyohuone"
    })
    public static class Henkilo {

        @XmlElement(required = true)
        protected String hetu;
        @XmlElement(required = true)
        protected Henkilosto.Henkilo.Nimi nimi;
        protected Henkilosto.Henkilo.Osoite osoite;
        protected Henkilosto.Henkilo.Tiimi tiimi;
        @XmlElement(required = true)
        protected String email;
        @XmlElement(required = true)
        protected List<Henkilosto.Henkilo.Puhelin> puhelin;
        @XmlElement(required = true)
        protected String nimike;
        protected Henkilosto.Henkilo.Tyohuone tyohuone;
        @XmlAttribute(required = true)
        protected String tunnus;

        public String getHetu() {
            return hetu;
        }

        public void setHetu(String value) {
            this.hetu = value;
        }

        public Henkilosto.Henkilo.Nimi getNimi() {
            return nimi;
        }

        public void setNimi(Henkilosto.Henkilo.Nimi value) {
            this.nimi = value;
        }

        public Henkilosto.Henkilo.Osoite getOsoite() {
            return osoite;
        }

        public void setOsoite(Henkilosto.Henkilo.Osoite value){
            this.osoite = value;
        }

        public Henkilosto.Henkilo.Tiimi getTiimi() {
            return tiimi;
        }
    
        public void setTiimi(Henkilosto.Henkilo.Tiimi value) {
            this.tiimi = value;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String value) {
            this.email = value;
        }

        public List<Henkilosto.Henkilo.Puhelin> getPuhelin() {
            if (puhelin == null) {
                puhelin = new ArrayList<Henkilosto.Henkilo.Puhelin>();
            }
            return this.puhelin;
        }

        public String getNimike() {
            return nimike;
        }

        public void setNimike(String value) {
            this.nimike = value;
        }

        public Henkilosto.Henkilo.Tyohuone getTyohuone() {
            return tyohuone;
        }

        public void setTyohuone(Henkilosto.Henkilo.Tyohuone value) {
            this.tyohuone = value;
        }

        public String getTunnus() {
            return tunnus;
        }

        public void setTunnus(String value) {
            this.tunnus = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "sukunimi",
            "etunimi"
        })
        public static class Nimi {

            @XmlElement(required = true)
            protected String sukunimi;
            @XmlElement(required = true)
            protected List<String> etunimi;

            public String getSukunimi() {
                return sukunimi;
            }

            public void setSukunimi(String value) {
                this.sukunimi = value;
            }

            public List<String> getEtunimi() {
                if (etunimi == null) {
                    etunimi = new ArrayList<String>();
                }
                return this.etunimi;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "lahiosoite",
            "postinumero",
            "postitoimipaikka"
        })
        public static class Osoite {

            protected String lahiosoite;
            @XmlElement(required = true)
            protected String postinumero;
            @XmlElement(required = true)
            protected String postitoimipaikka;

            public String getLahiosoite() {
                return lahiosoite;
            }

            public void setLahiosoite(String value) {
                this.lahiosoite = value;
            }

            public String getPostinumero() {
                return postinumero;
            }

            public void setPostinumero(String value) {
                this.postinumero = value;
            }

            public String getPostitoimipaikka() {
                return postitoimipaikka;
            }

            public void setPostitoimipaikka(String value) {
                this.postitoimipaikka = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Puhelin {

            @XmlValue
            protected String value;
            @XmlAttribute
            protected String type;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                if (type == null) {
                    return "ty\u00f6";
                } else {
                    return type;
                }
            }

            public void setType(String value) {
                this.type = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Tiimi {

            @XmlAttribute(required = true)
            protected String nimi;
            @XmlAttribute
            protected String asema;

            public String getNimi() {
                return nimi;
            }

            public void setNimi(String value) {
                this.nimi = value;
            }

            public String getAsema() {
                if (asema == null) {
                    return "j\u00e4sen";
                } else {
                    return asema;
                }
            }

            public void setAsema(String value) {
                this.asema = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Tyohuone {

            @XmlValue
            protected String value;
            @XmlAttribute
            protected String sijainti;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getSijainti() {
                if (sijainti == null) {
                    return "Helia1";
                } else {
                    return sijainti;
                }
            }

            public void setSijainti(String value) {
                this.sijainti = value;
            }

        }

    }

}
