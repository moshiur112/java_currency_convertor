package com.springboot.Models;

import java.util.Objects;

/** This class is used to model errors to be show to the user
 * @author Moshiur Rahman
 */
public class ErrorPage  extends ReturnPage{
    public String Message;

    public ErrorPage(String message) {
        super();
        this.Message = message;
    }
    /**
     * This method check whether two ErrorPage objects are identical
     * @param o another ErrorPage object
     * @return boolean of whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorPage errorPage = (ErrorPage) o;
        return Message.equals(errorPage.Message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Message);
    }
    /**
     * String representation of the the ErrorPage object
     */
    @Override
    public String toString() {
        return "ErrorPage{" +
                "Message='" + Message + '\'' +
                '}';
    }
}
