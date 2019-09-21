package com.geektcp.alpha.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
class CustomerGroup {

    private List<Customer> customers = new ArrayList<>();

    void accept(Visitor visitor) {
        for (Customer customer : customers) {
            customer.accept(visitor);
        }
    }

    void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
