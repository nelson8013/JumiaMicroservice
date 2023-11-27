package com.hacknode.orderservice.Exceptions;

public class ProductNoLongerInStockException extends RuntimeException {


        public ProductNoLongerInStockException(String message){
            super(message);
        }

        public ProductNoLongerInStockException(Throwable cause){
            super(cause);
        }

        public ProductNoLongerInStockException(String message, Throwable cause){
            super(message, cause);
        }




}
