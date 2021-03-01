package com.springboot.Models;

import java.util.Objects;

public class ErrorPage  extends ReturnPage{
    public String Message;

    public ErrorPage(String message) {
        super();
        this.Message = message;
    }

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

    @Override
    public String toString() {
        return "ErrorPage{" +
                "Message='" + Message + '\'' +
                '}';
    }
}
