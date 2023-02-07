package com.kshrd.hrdprojectcontrolapi.rest.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageProperties {
    private MessageSourceAccessor accessor;

    private MessageSource source;
    @Autowired
    public MessageProperties(@Qualifier("messageSource") MessageSource source) {
        this.source = source;
    }

    @PostConstruct
    private void init()
    {
        accessor=new MessageSourceAccessor(source, Locale.ENGLISH);
    }
    //for selected value
    public String selected(String resourceName)
    {
        return  accessor.getMessage("message.selected",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for selected one value
    public String selectedOne(String resourceName)
    {
        return  accessor.getMessage("message.selected-one",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for inserted value
    public String inserted(String resourceName)
    {
        return  accessor.getMessage("message.inserted",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for updated value
    public String updated(String resourceName)
    {
        return  accessor.getMessage("message.updated",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for deleted value
    public String deleted(String resourceName)
    {
        return  accessor.getMessage("message.deleted",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for inserted value have error
    public String insertError(String resourceName)
    {
        return  accessor.getMessage("message.insert-error",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for updated value have error
    public String updateError(String resourceName)
    {
        return  accessor.getMessage("message.update-error",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for deleted value have error
    public String deleteError(String resourceName)
    {
        return  accessor.getMessage("message.update-delete",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for data is empty
    public String dataEmpty(String resourceName)
    {
        return  accessor.getMessage("message.has-empty-value",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for not records in data base
    public String notRecords(String resourceName)
    {
        return  accessor.getMessage("message.has-no-records",new Object[] {resourceName},Locale.ENGLISH);
    }
    //for value is null
    public String nullValue(String resourceName)
    {
        return  accessor.getMessage("message.has-null-value",new Object[] {resourceName},Locale.ENGLISH);
    }
   //for value is null
    public String errorOperation(String resourceName)
    {
        return  accessor.getMessage("message.has-error-operation",new Object[] {resourceName},Locale.ENGLISH);
    }

    //for id not found
    public String idNotFound(String resourceName)
    {
        return  accessor.getMessage("message.id-not-found",new Object[] {resourceName},Locale.ENGLISH);
    }
}