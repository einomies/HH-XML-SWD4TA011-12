package JAXBTestOma; 

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

class GregorianCalendarTest01 {

	public static void main(final String[] args) {

//		System.out.println("Anna huuhdon päättymispäivä (vvvv-kk-pp): ");
//		String pvmString = input01.nextLine();
		String pvmString = "2017-11-30";
		int pvmIntVvvv = Integer.parseInt(pvmString.substring(0, 4));
		int pvmIntKk = Integer.parseInt(pvmString.substring(5, 7));
		int pvmIntPp = Integer.parseInt(pvmString.substring(8, 10));
		GregorianCalendar tempPaivays = new GregorianCalendar();
//		tempPaivays.set(Calendar.YEAR, pvmIntVvvv);
//		tempPaivays.set(Calendar.MONTH, pvmIntKk);
//		tempPaivays.set(Calendar.DATE, pvmIntPp);
		tempPaivays.set(pvmIntVvvv, pvmIntKk-1, pvmIntPp);
		XMLGregorianCalendar paivays = null;
		try {
			paivays = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					tempPaivays);
			paivays.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
//			paivays.setTime(DatatypeConstants.FIELD_UNDEFINED,
//					DatatypeConstants.FIELD_UNDEFINED,
//					DatatypeConstants.FIELD_UNDEFINED,
//					DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {

		}

		System.out.println("pvmString: " +pvmString);

		System.out.println();
		System.out.println("tempPaivays: ");
		System.out.print(tempPaivays.getTime());
//		System.out.println(tempPaivays.getMonth());
//		System.out.println(tempPaivays.getDay());

		System.out.println("\n");
		System.out.println("paivays: ");
		System.out.println("year: " +paivays.getYear());
		System.out.println("month: " +paivays.getMonth());
		System.out.println("day: " +paivays.getDay());
	}

}
