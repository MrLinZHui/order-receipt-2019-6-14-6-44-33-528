package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double SALES_TAX_RATE = .10;
    private static final String SALES_TAX = "Sales Tax";
    private static final String TOTAL_AMOUNT = "Total Amount";
    private Order order;
    private StringBuilder output = new StringBuilder();
    public OrderReceipt(Order o) {
        this.order = o;
    }

    public String printReceipt() {
        printHeaders();
        printCustomerNameAndAddress();
        getItems();

        return output.toString();
    }

    private void getItems() {
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            printItems(lineItem);
            double salesTax = lineItem.totalAmount() * SALES_TAX_RATE;
            totSalesTx += salesTax;
            tot += lineItem.totalAmount() + salesTax;
        }
        output.append(SALES_TAX).append('\t').append(totSalesTx);
        output.append(TOTAL_AMOUNT).append('\t').append(tot);
    }

    private void printItems(LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }

    private void printCustomerNameAndAddress() {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printHeaders() {
        output.append("======Printing Orders======\n");
    }
}