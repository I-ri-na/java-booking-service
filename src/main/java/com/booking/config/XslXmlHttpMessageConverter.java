package com.booking.config;

import com.booking.entity.Booking;
import com.booking.entity.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

public class XslXmlHttpMessageConverter extends MappingJackson2XmlHttpMessageConverter {

    public XslXmlHttpMessageConverter(ObjectMapper xmlMapper) {
        super(xmlMapper);
        setSupportedMediaTypes(List.of(
                new MediaType("application", "xml", StandardCharsets.UTF_8),
                new MediaType("text", "xml", StandardCharsets.UTF_8)));
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException {
        String root = null;
        String xsl = null;

        if (object instanceof Booking) {
            root = "booking";
            xsl = "/booking.xsl";
        } else if (object instanceof Client) {
            root = "client";
            xsl = "/client.xsl";
        } else if (object instanceof Collection<?> collection) {
            Class<?> element = elementType(collection, type);
            if (element != null && Booking.class.isAssignableFrom(element)) {
                root = "bookings";
                xsl = "/bookings.xsl";
            } else if (element != null && Client.class.isAssignableFrom(element)) {
                root = "clients";
                xsl = "/clients.xsl";
            }
        }

        ObjectWriter writer = getObjectMapper().writer();
        if (root != null) {
            writer = writer.withRootName(root);
        }

        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        if (xsl != null) {
            xml.append("<?xml-stylesheet type=\"text/xsl\" href=\"").append(xsl).append("\"?>\n");
        }
        xml.append(writer.writeValueAsString(object));

        outputMessage.getBody().write(xml.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static Class<?> elementType(Collection<?> collection, Type type) {
        if (type != null) {
            Class<?> fromType = ResolvableType.forType(type).asCollection().resolveGeneric(0);
            if (fromType != null && fromType != Object.class) {
                return fromType;
            }
        }
        return collection.isEmpty() ? null : collection.iterator().next().getClass();
    }
}
