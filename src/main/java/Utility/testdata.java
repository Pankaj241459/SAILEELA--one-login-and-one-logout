
	
	package Utility;

	import java.util.concurrent.atomic.AtomicInteger;

	public class testdata {

	    private static AtomicInteger invoiceNumber = new AtomicInteger(1);

	    private static String currentInvoiceNumber;

	    public static String generateInvoiceNumber() {

	        int nextNumber = invoiceNumber.incrementAndGet();

	        currentInvoiceNumber = String.format("INV%03d", nextNumber);

	        System.out.println("Generated Invoice = " + currentInvoiceNumber);

	        return currentInvoiceNumber;
	    }

	    public static String getInvoiceNumber() {

	        System.out.println(
	            "Using Existing Invoice = " + currentInvoiceNumber
	        );

	        return currentInvoiceNumber;
	    }
	}

