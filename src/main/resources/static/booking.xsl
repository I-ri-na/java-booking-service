<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" indent="yes"/>

<xsl:template match="/booking">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Бронирование №<xsl:value-of select="id"/></title>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <nav>
        <a href="/api/clients">Все клиенты</a>
        <a href="/api/bookings">Все бронирования</a>
    </nav>

    <h2>Бронирование №<xsl:value-of select="id"/></h2>
    <dl>
        <dt>Клиент</dt>
        <dd><a href="/api/clients/{client/id}"><xsl:value-of select="client/name"/></a></dd>
        <dt>Телефон клиента</dt>
        <dd><xsl:value-of select="client/phone"/></dd>
        <dt>Заезд</dt>
        <dd><xsl:value-of select="checkIn"/></dd>
        <dt>Выезд</dt>
        <dd><xsl:value-of select="checkOut"/></dd>
        <dt>Комната</dt>
        <dd><xsl:value-of select="roomNumber"/></dd>
        <dt>Статус</dt>
        <dd><xsl:value-of select="status"/></dd>
        <dt>Забронировано</dt>
        <dd><xsl:value-of select="translate(substring(bookedAt, 1, 16), 'T', ' ')"/></dd>
    </dl>

    <p><a href="/api/clients/{client/id}/bookings">Все бронирования этого клиента</a></p>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
