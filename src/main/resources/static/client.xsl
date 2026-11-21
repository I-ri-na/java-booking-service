<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" indent="yes"/>

<xsl:template match="/client">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Клиент: <xsl:value-of select="name"/></title>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <nav>
        <a href="/api/clients">Все клиенты</a>
        <a href="/api/bookings">Все бронирования</a>
    </nav>

    <h2>Клиент: <xsl:value-of select="name"/></h2>
    <dl>
        <dt>ID</dt>
        <dd><xsl:value-of select="id"/></dd>
        <dt>Телефон</dt>
        <dd><xsl:value-of select="phone"/></dd>
        <dt>Email</dt>
        <dd><xsl:value-of select="email"/></dd>
    </dl>

    <p><a href="/api/clients/{id}/bookings">Бронирования клиента</a></p>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
