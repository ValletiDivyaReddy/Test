package com.cg.em.dao;

public interface IQueryMapper {

public static final String ADD_EVENT_QRY = "INSERT INTO event(id,  title, cost, datescheduled,location) VALUES(?,?,?,?,?)";
public static final String MODIFY_EVENT_QRY = "UPDATE books SET title=?,cost=?,datescheduled=?,location=? WHERE id=?";
public static final String DEL_EVENT_QRY = "DELETE FROM books WHERE id=?";
public static final String GET_ALL_EVENT_QRY = "SELECT * FROM event";
public static final String GET_EVENT_QRY = "SELECT * FROM books WHERE id=?";
}